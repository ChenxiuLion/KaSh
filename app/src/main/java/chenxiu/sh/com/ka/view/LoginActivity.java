package chenxiu.sh.com.ka.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;
import chenxiu.sh.com.ka.http.HttpTool;
import chenxiu.sh.com.ka.http.IKaCallBack;
import chenxiu.sh.com.ka.http.KaCallBack;
import chenxiu.sh.com.ka.modle.LoginBean;

/**
 * Created by Chenxiu on 2016/5/22.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.login_btn)
    private Button mButton;

    @ViewInject(R.id.login_name)
    private EditText mEdit;

    @Override
    public void initView() {

        if(getDevice()!=null){
            Intent intent = new Intent(mContext,KaMainActivity.class);
            startActivity(intent);
            finish();
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str = mEdit.getText().toString().trim();
                if(TextUtils.isEmpty(str)){
                    Toast.makeText(mContext,"设备号未输入",Toast.LENGTH_SHORT).show();
                }else{
                    mShared.edit().putString("idname",str).commit();
                    HttpTool.getInstance().getDeviceInfo(str,new KaCallBack(new IKaCallBack() {
                        @Override
                        public void onResult(String json) {
                            LoginBean loginBean = new Gson().fromJson(json,LoginBean.class);
                            LogUtils.e(json);
                            if(loginBean.getResultData()==null){
                                Toast.makeText(mContext,"设备号错误",Toast.LENGTH_SHORT).show();
                            }else {
                                saveDevice(loginBean);
                                Intent intent = new Intent(mContext,KaMainActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onErro() {

                        }
                    }));
                }
            }
        });
    }
}
