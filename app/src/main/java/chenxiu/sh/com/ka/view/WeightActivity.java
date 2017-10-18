package chenxiu.sh.com.ka.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cosfund.library.util.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.modle.SubmitData;
import chenxiu.sh.com.ka.util.weight.BluetoothLeService;
import chenxiu.sh.com.ka.util.weight.SampleGattAttributes;
import chenxiu.sh.com.ka.util.weight.UserInfo;
import chenxiu.sh.com.ka.util.weight.Utils;

/**
 * 温度测量界面
 * Created by Chenxiu on 2016/6/6.
 */
@ContentView(R.layout.activity_temperature)
public class WeightActivity extends BaseActivity {

    private static  String ADDRESS = "88:C2:55:17:BB:0D";
    private static final String CharaUUID = "1a2ea400-75b9-11e2-be05-0002a5d5c51b";
    private static final String NAME = "VScale";  //设备名

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";


    @ViewInject(R.id.temperature_tv)
    private TextView mInfoTv;

    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up
            // initialization.
            ADDRESS = getDevice().getResultData().getWeightMac().trim();
            Log.e(TAG, "connect"+getDevice().getResultData().getWeightMac());
            if(mBluetoothLeService.connect(ADDRESS)){

            }else{
                SubmitData.getInstance().setWeight(0.0d);
                Intent intent = new Intent(mContext,InfoActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "sss");
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive, action: " + action);
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mInfoTv.setText("已连接设备");
            }else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                // displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
                byte[] data = intent.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                onBleDataAvailable(data);
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the
                // user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());

                mBluetoothLeService.setCharacteristicNotification(
                        Chara, true);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (Chara != null) {
                            Log.d(TAG, "readCharacteristic");
                            mBluetoothLeService.readCharacteristic(Chara);
                        }
                    }
                }, 500);
            }

        }
    };
    private int mScaleType = Utils.SCALE_TYPE_FAT;
    @Override
    public void initView() {
        mInfoTv.setText("开始测量体重");

        showVideo("请站到体重秤上");

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        registerReceiver(mBondingBroadcastReceiver, makeBondUpdateIntentFilter());
    }

    private void displayData(String data) {
        if (data != null) {
            mInfoTv.setText(data);

            SubmitData.getInstance().setWeight(Double.parseDouble(data));
            Intent intent = new Intent(mContext,InfoActivity.class);
            startActivity(intent);
            finish();
        }
    }
    protected BluetoothGattCharacteristic mScaleConfigCharacteristic;
    private void setCharacteristic(BluetoothGattCharacteristic characteristic, byte[] value) {

        characteristic.setValue(value);
        mBluetoothLeService.writeCharacteristic(characteristic);
    }

    private void setUserInfoToScale(UserInfo userInfo) {
        Log.d(TAG, "setUserInfoToScale");
        if (mScaleConfigCharacteristic == null) {
            Log.d(TAG, "mScaleConfigCharacteristic is null, return.");
            return;
        }

        byte[] data = Utils.packageDownData(userInfo);
        for (byte value : data) {
            Log.d(TAG, String.format("%1$#9x", value));
        }

        setCharacteristic(mScaleConfigCharacteristic, data);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
        unregisterReceiver(mBondingBroadcastReceiver);
    }

    private UserInfo info;
    private BroadcastReceiver mBondingBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            final int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
            final int previousBondState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1);

            Log.d(TAG, "Bond state changed for: " + device.getAddress() + " new state: " + bondState + " previous: "
                    + previousBondState);

            switch (bondState) {
                case BluetoothDevice.BOND_BONDED:
                    Log.d(TAG, "Boned");
                    // mBluetoothLeService.discoveryService();
                    break;

                case BluetoothDevice.BOND_BONDING:
                    Log.v(TAG, "Bonding");

                    break;
                case BluetoothDevice.BOND_NONE:
                    Log.v(TAG, "Bond None");

                    break;
                default:
                    Log.v(TAG, "bondState = " + bondState);
            }

        }
    };

    private void onBleDataAvailable(byte[] srcData) {
        if (srcData != null) {

            for (byte value : srcData) {
                Log.d(TAG, String.format("%1$#9x", value));
            }
            int type = srcData[0] & 0xf0;
            if (type == 0x10) {
                // weight scale.
                mScaleType = Utils.SCALE_TYPE_WEIGHT;
                float weight = Utils.paraseWeight(srcData);
                Log.d(TAG, "*********weight is: " + weight);
                if (weight == 0) {
                    Log.d(TAG, "*********weight is 0, so return.");
                    return;
                }
                displayData(String.valueOf(weight));
                // selectUser(weight);
            } else {
                // fat scale
                mScaleType = Utils.SCALE_TYPE_FAT;

                if (srcData.length > 4 && srcData[2] == 0 && srcData[3] == 0) {
                    if (srcData.length > 5) {
                        int h = srcData[4] & 0xff;
                        int l = srcData[5] & 0xff;
                        float weight = (float) ((h * 256 + l) / 10.0);
                        Log.d(TAG, "~~~~~~~~~~~~~~weight:" + weight);
                        if (weight == 0) {
                            Log.d(TAG, "~~~~~~~~~~~~~~weight is 0, so return.");
                            return;
                        }
                        displayData(String.valueOf(weight));

                        info = Utils.getCurrentUserInfo();
                        info.mBodyInfo.mWeight = String.valueOf(weight);

                        setUserInfoToScale(info);
                    }

                } else {
                    UserInfo userInfo = Utils.decodeUpdata(srcData, info);
                    Log.d(TAG, "userInfo: " + userInfo.toString());
                    displayData(userInfo.toString());
                }

            }

        }
    }


    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    private static IntentFilter makeBondUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        return intentFilter;
    }

    private BluetoothGattCharacteristic Chara = null;

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null)
            return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                if (SampleGattAttributes.VTBLE_SCALE_SET_USER_CHARACTERISTIC_UUID
                        .equalsIgnoreCase(gattCharacteristic.getUuid().toString())) {
                    Log.d(TAG, "BLE_SCALE_SET_USER_CHARACTERISTIC_UUID");
                    mScaleConfigCharacteristic = gattCharacteristic;
                }

                if(gattCharacteristic.getUuid().equals(UUID.fromString(CharaUUID))){
                    Chara = gattCharacteristic;
                }

                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
    }
}
