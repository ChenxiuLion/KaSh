package chenxiu.sh.com.ka.view;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import com.cosfund.library.util.JSONUtils;
import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.litesuits.bluetooth.LiteBleGattCallback;
import com.litesuits.bluetooth.LiteBluetooth;
import com.litesuits.bluetooth.exception.BleException;
import com.litesuits.bluetooth.exception.hanlder.DefaultBleExceptionHandler;
import com.litesuits.bluetooth.log.BleLog;
import com.litesuits.bluetooth.scan.PeriodMacScanCallback;
import com.litesuits.bluetooth.scan.PeriodScanCallback;
import com.litesuits.bluetooth.utils.BluetoothUtil;

import org.xutils.view.annotation.ContentView;

import java.util.Arrays;
import java.util.UUID;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;

/**
 * Created by Chenxiu on 2016/5/22.
 */
@ContentView(R.layout.activity_device)
public class DeviceActivity extends BaseActivity {
    private BluetoothAdapter mBluetoothAdapter;
    /**
     * 默认异常处理器
     */
    private DefaultBleExceptionHandler bleExceptionHandler;
    private int index = 0;
    @Override
    public void initView() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter.isEnabled()){
        }else{
            mBluetoothAdapter.enable();
        }
        mBluetoothAdapter.startDiscovery();
    }


    public void dialogShow(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lite BLE");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
