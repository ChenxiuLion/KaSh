package chenxiu.sh.com.ka.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import org.xutils.x;

import chenxiu.sh.com.ka.view.KaMainActivity;

public abstract class BaseFragment<T> extends Fragment {
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
    protected T mPresenter;

    public View mView;


    public boolean isVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(initView(), container, false);
        } else {
            ViewGroup group = (ViewGroup) mView.getParent();
            if (group != null) {
                group.removeView(mView);
            }
        }
        return mView;
    }


    /**
     * 设置fragment布局
     *
     * @return
     */
    protected abstract int initView();

    /**
     * 初始化数据，所有初始化数据的方法写在这里面
     *
     * @return
     */
    protected abstract void initData();


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        x.view().inject(this, LayoutInflater.from(getActivity()), null);
        initData();

        super.onViewCreated(view, savedInstanceState);
    }

    public void setTitle(CharSequence title) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setTitle(title);
    }


    /**
     * 快速 Intent
     *
     * @param cla 目标class
     */
    public void goIntent(Class cla) {
        Intent intent = new Intent(getActivity(), cla);
        startActivity(intent);
    }

    /**
     * 快速 Intent
     *
     * @param cla    目标class
     * @param bundle 传递参数
     */
    public void goIntent(Class cla, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cla);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 快速 Intent
     *
     * @param cla 目标class
     */
    public void goIntent(Class cla, String title) {
        Intent intent = new Intent(getActivity(), cla);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    /**
     * 提示
     */
    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        //	new HintViewUtils(getActivity(),msg,null,null);
    }

    public void showVideo(SynthesizerListener mSynListener,String content ){
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(getActivity(), null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vinn");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "80");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
//3.开始合成
        mTts.startSpeaking(content, mSynListener);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public KaMainActivity getMain(){
        return (KaMainActivity) getActivity();
    }
}
