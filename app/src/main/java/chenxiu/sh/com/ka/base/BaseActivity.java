package chenxiu.sh.com.ka.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import org.xutils.x;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.modle.LoginBean;

/**
 * activity基类
 * Created by Chenxiu on 2016/5/19.
 */
public abstract class BaseActivity extends FragmentActivity {
    // Debugging
    public static final String TAG = "BluetoothComm";
    public static final boolean D = true;
    //请求开启蓝牙的requestCode
    public static final int REQUEST_ENABLE_BT = 1;
    //请求连接的requestCode
    public static final int REQUEST_CONNECT_DEVICE = 2;
    //bluetoothCommService 传来的消息状态
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    protected FrameLayout mRootView;

    protected View mView;

    protected Context mContext;

    protected EditText mBaseEdit;

    protected SharedPreferences mShared;

    protected SynthesizerListener mSynListener = new SynthesizerListener() {
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

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    protected void onVoiceEnd(int i){

    }

    protected SpeechSynthesizer mTts;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        window.setAttributes(params);
        mView = LayoutInflater.from(this).inflate(R.layout.activity_base,null);
        mShared = getSharedPreferences("DATA_Test", Activity.MODE_PRIVATE);
        setContentView(mView);
        getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         mTts = SpeechSynthesizer.createSynthesizer(this, null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vinn");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "80");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

        mContext = this;
        mRootView = (FrameLayout) findViewById(R.id.base_content);

        x.view().inject(this);
        initView();
    }

    @Override
    public void setContentView(int layoutResID) {
        mRootView.addView(LayoutInflater.from(mContext).inflate(layoutResID,null));
    }

    public abstract void initView();
    public void initEdit() {
    }

    public void onKa(String ka){

    }

    public void showVideo(String content){
        mTts.startSpeaking(content, mSynListener);
    }
    public void showVideo(SynthesizerListener s,String content){
        mTts.startSpeaking(content, s);
    }
    public void saveDevice(LoginBean bean){
        mShared.edit().putString("Device",new Gson().toJson(bean)).commit();
    }

    public LoginBean getDevice(){

        String json = mShared.getString("Device","");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,LoginBean.class);
    }
}
