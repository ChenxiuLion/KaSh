package chenxiu.sh.com.ka.view;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cosfund.library.util.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Set;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.modle.SubmitData;

/**
 * 温度测量界面
 * Created by Chenxiu on 2016/6/6.
 */
@ContentView(R.layout.activity_temperature)
public class TemperatureActivity extends BaseActivity {

    @ViewInject(R.id.temperature_tv)
    private TextView mInfoTv;

    private String mConnectedDeviceName = null;
    //本地蓝牙适配器
    private BluetoothAdapter bluetooth;
    //创建一个蓝牙串口服务对象
    private BluetoothCommService mCommService = null;

    // The Handler that gets information back from the BluetoothChatService

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothCommService.STATE_CONNECTED:
                            LogUtils.e("已连接设备");
                            showVideo("请找老师测量体温");
                            //    mConversationArrayAdapter.clear();
                            break;
                        case BluetoothCommService.STATE_CONNECTING:
                            LogUtils.e("连接中..");
                            break;
                        case BluetoothCommService.STATE_LISTEN:
                        case BluetoothCommService.STATE_NONE:
                            LogUtils.e("未连接设备");
                            SubmitData.getInstance().setTemperature((0*0.1));
                            Intent intent = new Intent(mContext,WeightActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }
                    break;
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String data = HexStr(readBuf);
                    String result = "";
                    for (int i = 0; i < data.length(); i++) {
                        char  item =  data.charAt(i);
                        if(i>3&&i<8) {
                            result += item;
                        }
                    }
                    mInfoTv.setText(((float)(Integer.parseInt(result,16)*0.1))+"度");
                    SubmitData.getInstance().setTemperature((Integer.parseInt(result,16)*0.1));
                    Intent intent = new Intent(mContext,WeightActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    break;
                case MESSAGE_TOAST:
                    break;
            }
        }
    };
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    //蓝牙设备
    private BluetoothDevice device = null;
    private int noDeviceFlag = 0;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //�������豸
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                noDeviceFlag = 0;
                //����豸�Ѿ���ԣ������
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    //  newDevicesAdapter.add(device.getName() + "\n" + device.getAddress());
                    connect(device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                if(D) Log.d(TAG, "�������noDeviceFlag="+noDeviceFlag);
            }
        }

    };
    /**
     * 字节数据转16进制
     * @param p
     * @return
     */
    public static String HexStr(byte[] p) {
        char[] c=new char[p.length*2 + 2];
        byte b;
        c[0]='0'; c[1]='x';
        for(int y=0, x=2; y<p.length; ++y, ++x) {
            b=((byte)(p[y ]>>4));
            c[x]=(char)(b>9 ? b+0x37 : b+0x30);
            b=((byte)(p[y ]&0xF));
            c[++x]=(char)(b>9 ? b+0x37 : b+0x30);
        }
        return new String(c);
    }
    @Override
    public void initView() {
        mCommService = new BluetoothCommService(this, mHandler);
        bluetooth = BluetoothAdapter.getDefaultAdapter();

        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);


        Set<BluetoothDevice> pairedDevices = bluetooth.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            //   findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                LogUtils.e(device.getName() + "\n" + device.getAddress());
                connect(device.getAddress());
            }
        }
    }



    public void connect(String address){
        // Get the BLuetoothDevice object
        device = bluetooth.getRemoteDevice(address);
        //尝试连接设备
        mCommService.connect(device);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!bluetooth.isEnabled()) {
            //请求打开蓝牙设备
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, BaseActivity.REQUEST_ENABLE_BT);
        } else {
            if (mCommService == null) {
                mCommService = new BluetoothCommService(this, mHandler);
            }
        }
    }

    /**
     * onActivityResult方法，当启动startActivityForResult返回之后调用，
     * 根据用户的操作来执行相应的操作
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    if (D) Log.d(TAG, "打开蓝牙设备");
                    Toast.makeText(this, "成功打开蓝牙", Toast.LENGTH_SHORT).show();
                } else {
                    if (D) Log.d(TAG, "不允许打开蓝牙设备");
                    Toast.makeText(this, "不能打开蓝牙,程序即将关闭", Toast.LENGTH_SHORT).show();
                    finish();//用户不打开设备，程序结束
                }
                break;
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {//用户选择连接的设备
                    // Get the device MAC address
                    String address = data.getExtras()
                            .getString(EXTRA_DEVICE_ADDRESS);
                    // Get the BLuetoothDevice object
                    device = bluetooth.getRemoteDevice(address);
                    //尝试连接设备
                    mCommService.connect(device);
                }
                break;
        }
        return;
    }
}
