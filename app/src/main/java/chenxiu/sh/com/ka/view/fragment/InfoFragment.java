package chenxiu.sh.com.ka.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.xutils.view.annotation.ViewInject;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;
import chenxiu.sh.com.ka.modle.SubmitData;
import chenxiu.sh.com.ka.view.KaMainActivity;

/**
 * Created by Chenxiu on 2016/6/30.
 */
public class InfoFragment extends BaseFragment {



    @Override
    protected int initView() {
        return R.layout.activity_info;
    }

    public int index = 20;
    @Override
    protected void initData() {
        getMain().showVideo("请按键选择学生健康状态");
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    while (index>0) {
                        Thread.sleep(1000);
                        index--;
                    }
                    if(index == 0){
                       getActivity().runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               go(1);
                           }
                       });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onStop() {
        index = -1;
        super.onStop();
    }

    public void go(int i){
        SubmitData.getInstance().setHealthStatus(i);
        getMain().onNextFragment(0);
    }
}
