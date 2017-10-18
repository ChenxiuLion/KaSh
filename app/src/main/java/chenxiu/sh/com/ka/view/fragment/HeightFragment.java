package chenxiu.sh.com.ka.view.fragment;

import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;

/**
 * Created by Chenxiu on 2016/6/29.
 */
public class HeightFragment extends BaseFragment {


    @ViewInject(R.id.temperature_tv)
    private TextView mInfoTv;
    @ViewInject(R.id.temperature_title)
    private TextView mTitleTv;
    @Override
    protected int initView() {
        return R.layout.activity_temperature;
    }

    @Override
    protected void initData() {
        mTitleTv.setText("请提交宝宝身高数据");
        getMain().showVideo("请输入学生身高");
        mInfoTv.setText("请输入身高数据");
    }

    public void setShengao(String s){
        mInfoTv.setText(s.trim()+"CM");
    }
    public void setTemperature(float value){

    }

}
