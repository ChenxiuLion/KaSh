package chenxiu.sh.com.ka.view;

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

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.modle.SubmitData;

/**
 * 打卡结果界面
 * Created by Chenxiu on 2016/6/28.
 */
@ContentView(R.layout.activity_info)
public class InfoActivity extends BaseActivity {


    @ViewInject(R.id.base_edit)
    private EditText mStyleEdit;

    private int index = 20;
    @Override
    public void initView() {
        showVideo("请二十秒内按键选择学生健康状态");
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
                        go(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void go(int i){
        SubmitData.getInstance().setHealthStatus(i);
        Intent intent = new Intent(mContext,KaMainActivity.class);
        startActivity(intent);
        finish();
    }
}
