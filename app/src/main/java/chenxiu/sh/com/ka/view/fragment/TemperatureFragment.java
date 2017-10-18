package chenxiu.sh.com.ka.view.fragment;

import android.graphics.Color;
import android.widget.TextView;
import org.xutils.view.annotation.ViewInject;
import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;

/**
 * Created by Chenxiu on 2016/6/29.
 */
public class TemperatureFragment extends BaseFragment {

    public static TemperatureFragment temperatureFragment;

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
        temperatureFragment = this;
    }

    public void setTv(String value){
        mInfoTv.setText(value);
    }

    public void showErro(){
        mInfoTv.setTextColor(Color.RED);
    }

}
