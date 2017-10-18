package chenxiu.sh.com.ka.view;

import android.content.Intent;

import org.xutils.view.annotation.ContentView;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseActivity;

/**
 * Created by Chenxiu on 2016/11/16.
 */
@ContentView(R.layout.activity_ka_main)
public class TTActivity extends BaseActivity {
    @Override
    public void initView() {
        int t = getIntent().getIntExtra("tiwen",0);
        Intent i = new Intent(mContext,KaMainActivity.class);
        i.putExtra("tiwen",t);
        startActivity(i);
        finish();
    }
}
