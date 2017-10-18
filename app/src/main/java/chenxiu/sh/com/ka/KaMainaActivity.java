package chenxiu.sh.com.ka;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SynthesizerListener;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.constant.Constant;
import chenxiu.sh.com.ka.http.HttpTool;
import chenxiu.sh.com.ka.http.IKaCallBack;
import chenxiu.sh.com.ka.http.KaCallBack;
import chenxiu.sh.com.ka.modle.ClassBaen;
import chenxiu.sh.com.ka.modle.Kind;
import chenxiu.sh.com.ka.modle.LoginBean;
import chenxiu.sh.com.ka.modle.Student;
import chenxiu.sh.com.ka.modle.SubmitData;
import chenxiu.sh.com.ka.view.BluetoothCommService;
import chenxiu.sh.com.ka.view.KaMainActivity;
import chenxiu.sh.com.ka.view.fragment.CameraFragment;
import chenxiu.sh.com.ka.view.fragment.HeightFragment;
import chenxiu.sh.com.ka.view.fragment.InfoFragment;
import chenxiu.sh.com.ka.view.fragment.MainFragment;
import chenxiu.sh.com.ka.view.fragment.TemperatureFragment;

/**
 * 主界面
 * Created by Chenxiu on 2016/5/22.
 */
@ContentView(R.layout.activity_ka_main)
public class KaMainaActivity extends BaseActivity {
    //本地蓝牙适配器
    private BluetoothAdapter bluetooth;


    // private static String path = "rtsp://122.114.135.57:5561/100102001";
    private static String path = "rtsp://122.114.135.57:5561/100102001";
    @ViewInject(R.id.buffer)
    private VideoView mVideoView;
    @ViewInject(R.id.time_sta)
    private TextView mTimeSta;

    @ViewInject(R.id.min_tv)
    private TextView mTimeMin;

    @ViewInject(R.id.ss_tv)
    private TextView mTimeS;

    @ViewInject(R.id.main_user_info_bj)
    private TextView mUserBJ;

    @ViewInject(R.id.main_user_imge)
    private ImageView mImage;

    @ViewInject(R.id.main_user_info_name)
    private TextView mUserName;

    @ViewInject(R.id.main_content)
    private FrameLayout mContent;

    @ViewInject(R.id.main_user_info)
    private LinearLayout mUserInfoLin;

    @ViewInject(R.id.main_qr)
    private LinearLayout mQrLin;

    private Class[] mClass = {
            MainFragment.class,
            CameraFragment.class,
            TemperatureFragment.class,
            InfoFragment.class
    };

    private int mIndex = 0;

    private int isWeekOne = 0;

    @ViewInject(R.id.main_date)
    private TextView mDateTv;

    @ViewInject(R.id.qr_image)
    private ImageView mQRTv;

    @ViewInject(R.id.title)
    private TextView mTitleTv;


    //创建一个蓝牙串口服务对象
    private BluetoothCommService mCommService = null;
    /**
     * 时间Handler
     */
    private Handler mTimeHandler = new Handler(Looper.getMainLooper());

    private int mTemperType = 0;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, KaMainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
    };
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothCommService.STATE_CONNECTED:
                            LogUtils.e("已连接设备");
                            //showVideo("请找老师测量体温");
                            mTemperatureSyter = 1;
                            //    mConversationArrayAdapter.clear();
                            break;
                        case BluetoothCommService.STATE_CONNECTING:
                            LogUtils.e("连接中..");
                            break;
                        case BluetoothCommService.STATE_LISTEN:
                        case BluetoothCommService.STATE_NONE:
                            LogUtils.e("未连接设备");
                            if (mIndex == 2) {
                                if (mTemperType == 0) {
                                    SubmitData.getInstance().setTemperature((0 * 0.1));
                                    mTemperType++;
                                    onNextFragment();
                                }

                            }
                            mTemperatureSyter = 2;
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
                        char item = data.charAt(i);
                        if (i > 3 && i < 8) {
                            result += item;
                        }
                    }
                    final String temp = result;

                    // mInfoTv.setText(((float)(Integer.parseInt(result,16)*0.1))+"度");
                    if (mIndex == 2) {
                        try {
                            DecimalFormat df = new DecimalFormat("#.##");
                            if (null != TemperatureFragment.temperatureFragment) {
                                TemperatureFragment.temperatureFragment.setTv(df.format(Integer.parseInt(result, 16) * 0.1) + "摄氏度");
                            }
                            if ((Integer.parseInt(result, 16) * 0.1) > 37) {
                                showVideo("请注意：体温偏高");
                                if (null != TemperatureFragment.temperatureFragment) {
                                    TemperatureFragment.temperatureFragment.showErro();
                                }
                            } else if ((Integer.parseInt(result, 16) * 0.1) < 34) {
                                showVideo("请注意：体温偏低");
                            } else {

                                showVideo(new SynthesizerListener() {
                                    @Override
                                    public void onSpeakBegin() {

                                    }

                                    @Override
                                    public void onBufferProgress(int i, int i1, int i2, String s) {

                                    }

                                    @Override
                                    public void onSpeakPaused() {

                                    }

                                    @Override
                                    public void onSpeakResumed() {

                                    }

                                    @Override
                                    public void onSpeakProgress(int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onCompleted(SpeechError speechError) {
                                        SubmitData.getInstance().setTemperature((Integer.parseInt(temp, 16) * 0.1));
                                        onNextFragment();
                                    }

                                    @Override
                                    public void onEvent(int i, int i1, int i2, Bundle bundle) {

                                    }
                                }, "体温" + df.format(Integer.parseInt(result, 16) * 0.1) + "摄氏度");
                            }

                        } catch (Exception e) {
                            showVideo("体温测量失败");
                        }
                    }
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
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
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

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
                if (D) Log.d(TAG, "�������noDeviceFlag=" + noDeviceFlag);
            }
        }

    };

    public void onNextFragment() {
        mIndex++;
        if (mIndex == 2) {
            mTemperType = 0;
            if (mTemperatureSyter == 0) {
                mCommService = new BluetoothCommService(mContext, mHandler);

                // Register for broadcasts when a device is discovered
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter);
                filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                registerReceiver(mReceiver, filter);
                Set<BluetoothDevice> pairedDevices = bluetooth.getBondedDevices();

                // If there are paired devices, add each one to the ArrayAdapter
                if (pairedDevices.size() > 0) {
                    //   findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
                    for (BluetoothDevice device : pairedDevices) {
                        connect(device.getAddress());
                    }
                }
            }
        }
            initFragment(mClass[mIndex]);
    }

    public void onNextFragment(int i) {
        if (i == 0) {
            mQrLin.setVisibility(View.VISIBLE);
            onQiandao();
        }
        mIndex = i;
        initFragment(mClass[mIndex]);
    }

    @Override
    public void initView() {
        new TimeThread().start();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("2".equals(mWay)) {
            isWeekOne = 1;
        }
        handler.postDelayed(runnable, 1000*5);//每两秒执行一次runnable.
        ImageLoader.getInstance().displayImage(Constant.BASE_URL + "/img/weixin.png", mQRTv);
        mBaseEdit = (EditText) findViewById(R.id.base_edit);
        mBaseEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIndex == 3) {
                    if (s.toString().contains("+")) {
                        showVideo(new SynthesizerListener() {
                            @Override
                            public void onSpeakBegin() {

                            }

                            @Override
                            public void onBufferProgress(int i, int i1, int i2, String s) {

                            }

                            @Override
                            public void onSpeakPaused() {

                            }

                            @Override
                            public void onSpeakResumed() {

                            }

                            @Override
                            public void onSpeakProgress(int i, int i1, int i2) {

                            }

                            @Override
                            public void onCompleted(SpeechError speechError) {
                                SubmitData.getInstance().setHealthStatus(1);
                                onNextFragment(0);
                                mBaseEdit.setText("");
                            }

                            @Override
                            public void onEvent(int i, int i1, int i2, Bundle bundle) {

                            }
                        }, "健康");

                    } else if (s.toString().contains("*")) {
                        showVideo(new SynthesizerListener() {
                            @Override
                            public void onSpeakBegin() {

                            }

                            @Override
                            public void onBufferProgress(int i, int i1, int i2, String s) {

                            }

                            @Override
                            public void onSpeakPaused() {

                            }

                            @Override
                            public void onSpeakResumed() {

                            }

                            @Override
                            public void onSpeakProgress(int i, int i1, int i2) {

                            }

                            @Override
                            public void onCompleted(SpeechError speechError) {
                                SubmitData.getInstance().setHealthStatus(2);
                                onNextFragment(0);
                                mBaseEdit.setText("");
                            }

                            @Override
                            public void onEvent(int i, int i1, int i2, Bundle bundle) {

                            }
                        }, "稍有不适");
                    } else if (s.toString().contains("-")) {
                        showVideo(new SynthesizerListener() {
                            @Override
                            public void onSpeakBegin() {

                            }

                            @Override
                            public void onBufferProgress(int i, int i1, int i2, String s) {

                            }

                            @Override
                            public void onSpeakPaused() {

                            }

                            @Override
                            public void onSpeakResumed() {

                            }

                            @Override
                            public void onSpeakProgress(int i, int i1, int i2) {

                            }

                            @Override
                            public void onCompleted(SpeechError speechError) {
                                SubmitData.getInstance().setHealthStatus(3);
                                onNextFragment(0);
                                mBaseEdit.setText("");
                            }

                            @Override
                            public void onEvent(int i, int i1, int i2, Bundle bundle) {

                            }
                        }, "需要特别关注");
                    }
                } else if (mIndex == 4) {

                    if (mIndex == 4 && mInfoFragment != null) {
                        mInfoFragment.setShengao(s.toString());
                    }
                    if (s.toString().contains("\n")) {
                        try {
                            DecimalFormat df = new DecimalFormat("#.##");
                            SubmitData.getInstance().setHeight(Double.parseDouble(s.toString().trim()));
                            if (SubmitData.getInstance().getHeight() >= 40 && SubmitData.getInstance().getHeight() <= 170) {
                                showVideo(new SynthesizerListener() {
                                    @Override
                                    public void onSpeakBegin() {

                                    }

                                    @Override
                                    public void onBufferProgress(int i, int i1, int i2, String s) {

                                    }

                                    @Override
                                    public void onSpeakPaused() {

                                    }

                                    @Override
                                    public void onSpeakResumed() {

                                    }

                                    @Override
                                    public void onSpeakProgress(int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onCompleted(SpeechError speechError) {
                                        onNextFragment();
                                    }

                                    @Override
                                    public void onEvent(int i, int i1, int i2, Bundle bundle) {

                                    }
                                }, "身高" + df.format(SubmitData.getInstance().getHeight()) + "厘米");
                            } else {
                                showVideo("身高输入错误");
                            }
                        } catch (Exception e) {
                            showVideo("身高输入错误");
                            mBaseEdit.setText("");
                        }


                    }
                } else if (mIndex == 0) {

                    if (s.toString().contains("\n")) {
                        onKa(s.toString().trim());
                        mBaseEdit.setText("");
                    }
                } else {

                }
            }
        });
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ka/";
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        playVideo();
        bluetooth = BluetoothAdapter.getDefaultAdapter();
        onNextFragment(0);

        showVideo("请保证打开体温计，等待宝宝刷卡签到");
        HttpTool.getInstance().getKindInfo(getDevice().getResultData().getKindergartenID() + "", new KaCallBack(new IKaCallBack() {
            @Override
            public void onResult(String json) {
                Kind kind = new Gson().fromJson(json, Kind.class);
                mTitleTv.setText(kind.getResultData().getName());
            }

            @Override
            public void onErro() {

            }
        }));
      /*
       * Alternatively,for streaming media you can use
       * mVideoView.setVideoURI(Uri.parse(URLstring));
       */
        //String path = getDevice().getResultData().getADLink();
    }

    public void connect(String address) {
        // Get the BLuetoothDevice object
        device = bluetooth.getRemoteDevice(address);
        //尝试连接设备
        mCommService.connect(device);
    }

    public void onQiandao() {

        mQrLin.setVisibility(View.VISIBLE);
        HttpTool.getInstance().addKindergartenRecord(new KaCallBack(new IKaCallBack() {
            @Override
            public void onResult(String json) {
                LoginBean bean = new Gson().fromJson(json, LoginBean.class);
                if (mTemperatureSyter != 0) {
                    if (bean.getResultCode() == 0) {
                        showVideo("上位小朋友签到失败，请重新签到");
                    } else {
                        showVideo("请下一位小朋友刷卡");
                    }
                }
                LogUtils.e(json);
            }

            @Override
            public void onErro() {

            }
        }));

    }

    public void playVideo() {
        HttpTool.getInstance().getFile(getDevice().getResultData().getADLink(), new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                LogUtils.e(task.getPath());
                mVideoView.setVideoPath(task.getPath());
                mVideoView.requestFocus();
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        });
    }

    /**
     * 字节数据转16进制
     *
     * @param p
     * @return
     */
    public static String HexStr(byte[] p) {
        char[] c = new char[p.length * 2 + 2];
        byte b;
        c[0] = '0';
        c[1] = 'x';
        for (int y = 0, x = 2; y < p.length; ++y, ++x) {
            b = ((byte) (p[y] >> 4));
            c[x] = (char) (b > 9 ? b + 0x37 : b + 0x30);
            b = ((byte) (p[y] & 0xF));
            c[++x] = (char) (b > 9 ? b + 0x37 : b + 0x30);
        }
        return new String(c);
    }

    private int mTemperatureSyter = 0;

    @Override
    public void onKa(String ka) {
        final long time = System.currentTimeMillis();
        long kaTime = System.currentTimeMillis() - mShared.getLong(ka, 0);
        if (false/*kaTime<1000*60*10*/) {
            showVideo("不能重复签到");
        } else {
            HttpTool.getInstance().getBabyInfo(ka, new KaCallBack(new IKaCallBack() {
                @Override
                public void onResult(String json) {
                    Student student = new Gson().fromJson(json, Student.class);
                    Logger.e("zx", json);
                    if (student.getResultData() == null) {
                        showVideo("该卡号不存在，签到失败");
                    } else {
                        mShared.edit().putLong(student.getResultData().getCardNO(), time).commit();
                        HttpTool.getInstance().getClassInfo(student.getResultData().getClassID() + "", new KaCallBack(new IKaCallBack() {
                            @Override
                            public void onResult(String json) {
                                ClassBaen classBaen = new Gson().fromJson(json, ClassBaen.class);

                                if (classBaen.getResultData() != null) {
                                    mUserBJ.setText("班级：" + classBaen.getResultData().getName());
                                } else {
                                    mUserBJ.setText("班级获取失败");
                                }

                            }

                            @Override
                            public void onErro() {

                            }
                        }));
                        mQrLin.setVisibility(View.GONE);
                        mUserBJ = (TextView) findViewById(R.id.main_user_info_bj);
                        mUserName = (TextView) findViewById(R.id.main_user_info_name);
                        mImage = (ImageView) findViewById(R.id.main_user_imge);


                        mUserName.setText("姓名：" + student.getResultData().getUserName());
                        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + student.getResultData().getPhoto(), mImage);

                        showVideo(student.getResultData().getUserName() + "，请站好准备拍照咯");
                        SubmitData.getNewInstance();
                        SubmitData.getInstance().setCardNO(student.getResultData().getCardNO());
                        SubmitData.getInstance().setID(student.getResultData().getID());
                        SubmitData.getInstance().setCheckTime(System.currentTimeMillis());

                        onNextFragment(1);
                    }
                }

                @Override
                public void onErro() {
                    LogUtils.e("123");
                }
            }));
        }
    }

    @Override
    public void finish() {
        super.finish();
    }


    private static boolean checkCameraFacing(final int facing) {
        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
            return false;
        }
        final int cameraCount = Camera.getNumberOfCameras();
        Logger.e("摄像头数量=" + cameraCount);
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }


    private long mTimeOut = 0;

    private class TimeThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(500);
                    mTimeOut += 500;
                    mTimeHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (!mVideoView.isPlaying()) {
                                mVideoView.start();
                            }
                            if (mTimeOut > 1000 * 60 * 60) {
                                HttpTool.getInstance().getDeviceInfo(mShared.getString("idname", ""), new KaCallBack(new IKaCallBack() {
                                    @Override
                                    public void onResult(String json) {
                                        LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                                        LogUtils.e(json);
                                        if (loginBean.getResultData() == null) {
                                        } else {
                                            saveDevice(loginBean);
                                            playVideo();
                                        }

                                    }

                                    @Override
                                    public void onErro() {

                                    }
                                }));
                            }
                            mBaseEdit.requestFocus();
                            mVideoView.setFocusable(false);
                            mTimeMin.setFocusable(false);
                            mTimeSta.setFocusable(false);
                            mTimeS.setFocusable(false);
                            if (mTimeSta.getVisibility() == View.VISIBLE) {
                                mTimeSta.setVisibility(View.INVISIBLE);
                            } else {
                                mTimeSta.setVisibility(View.VISIBLE);
                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("HH-mm", Locale.getDefault());
                            SimpleDateFormat de = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());

                            String date = StringData();
                            mDateTv.setText(date);
                            mTimeMin.setText(sdf.format(new Date(System.currentTimeMillis())).split("-")[0]);
                            mTimeS.setText(sdf.format(new Date(System.currentTimeMillis())).split("-")[1]);
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mYear + "年" + mMonth + "月" + mDay + "日" + "    星期" + mWay;
    }

    private HeightFragment mInfoFragment;

    public void initFragment(Class clas) {
        try {

            Fragment fragment = (Fragment) Class.forName(clas.getName()).newInstance();
            if (fragment instanceof HeightFragment) {
                mInfoFragment = (HeightFragment) fragment;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!bluetooth.isEnabled()) {
            //请求打开蓝牙设备
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, BaseActivity.REQUEST_ENABLE_BT);
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
