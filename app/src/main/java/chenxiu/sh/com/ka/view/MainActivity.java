package chenxiu.sh.com.ka.view;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cosfund.library.util.LogUtils;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.util.ClsUtils;

@ContentView(R.layout.content_main)
public class MainActivity extends BaseActivity {

    private BluetoothAdapter mBlueAdapter;
    private static final String DYNAMICACTION = "com.bn.pp2.dynamicreceiver";
    @ViewInject(R.id.main_edit)
    private EditText mMainEdit;

    private Set<BluetoothDevice> pairedDevices;

    @Override
    public void initView() {
        // 设置广播信息过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBlueReceiver, intentFilter);
         // 检查设备是否支持蓝牙
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBlueAdapter == null) {
            // 设备不支持蓝牙
        }
        // 打开蓝牙
        if (!mBlueAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // 设置蓝牙可见性，最多300秒
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(intent);
        }
        mBlueAdapter.startDiscovery();
        mMainEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains("\n")) {
                    Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
                    initEdit();
                }
            }
        });
    }


    private BroadcastReceiver mBlueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // TODO Auto-generated method stub
            if (intent.getAction().equals(
                    "android.bluetooth.device.action.PAIRING_REQUEST"))
            {
                BluetoothDevice btDevice = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // byte[] pinBytes = BluetoothDevice.convertPinToBytes("1234");
                // device.setPin(pinBytes);
                Log.i("tag11111", "ddd");
                try
                {
                    ClsUtils.setPin(btDevice.getClass(), btDevice, "1234"); // 手机和蓝牙采集器配对
                    ClsUtils.createBond(btDevice.getClass(), btDevice);
                    ClsUtils.cancelPairingUserInput(btDevice.getClass(), btDevice);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 获取蓝牙设备的连接状态
                int connectState = device.getBondState();
                switch (connectState) {
                    // 未配对
                    case BluetoothDevice.BOND_NONE:
                        // 配对
                        try {
                            Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                            createBondMethod.invoke(device);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        try {
                            // 连接
                            connect(device);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
            //搜索完成
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                LogUtil.e("搜索完成");
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED
                    .equals(action)) {
                LogUtil.e("蓝牙开启");
            }
        }
    };
    private void connect(final BluetoothDevice device) throws IOException {
        // 固定的UUID
        new Thread(){
            @Override
            public void run() {
                try {
                final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
                UUID uuid = UUID.fromString(SPP_UUID);
                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
                    String content = readInputStream(socket.getInputStream()).toString();
                    LogUtils.e(content);
                    socket.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    protected void onPause() {
        unregisterReceiver(mBlueReceiver);
        super.onPause();

    }
    /**
     * 从输入流获取数据
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inputStream) throws Exception{
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
    public void initEdit() {
        mMainEdit.setText("");
    }
}
