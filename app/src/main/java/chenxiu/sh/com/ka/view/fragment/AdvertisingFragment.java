package chenxiu.sh.com.ka.view.fragment;

import java.util.ArrayList;
import java.util.List;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;
import chenxiu.sh.com.ka.http.HttpTool;
import lester.scolltextview.scolltextview.ScollTextView;

/**
 * Created by chenxiu on 2017/8/19.
 */

public class AdvertisingFragment extends BaseFragment {


    @Override
    protected int initView() {
        return R.layout.fragment_ad;
    }

    @Override
    protected void initData() {

        //HttpTool.getInstance().getKindInfo();
        ScollTextView scollTextView= (ScollTextView) mView.findViewById(R.id.view);
        List<String> data=new ArrayList<String>();
        for (int i=0;i<20;i++){
            data.add("第"+i+"条测试数据,左边出来");
        }
        scollTextView.setData(data);
    }
}
