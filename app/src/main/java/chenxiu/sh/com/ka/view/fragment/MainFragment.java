package chenxiu.sh.com.ka.view.fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;
import chenxiu.sh.com.ka.http.HttpTool;
import chenxiu.sh.com.ka.http.IKaCallBack;
import chenxiu.sh.com.ka.http.KaCallBack;
import chenxiu.sh.com.ka.modle.FoodliST;

/**
 * Created by Chenxiu on 2016/6/29.
 */
public class MainFragment extends BaseFragment {
    private Handler mTimeHandler = new Handler(Looper.getMainLooper());


    private String mStr1 = "";  //早饭

    private String mStr2 = "";  //早加餐

    private String mStr3 = "";  //午饭

    private String mStr4 = "";  //午加餐

    private String mStr5 = "";  //晚餐

    @ViewInject(R.id.food_1)
    private TextView mText1 ;

    @ViewInject(R.id.food_2)
    private TextView mText2 ;

    @ViewInject(R.id.food_3)
    private TextView mText3 ;

    @ViewInject(R.id.food_4)
    private TextView mText4 ;

    @ViewInject(R.id.food_5)
    private TextView mText5 ;
    @Override
    protected int initView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        getData();
    }


    public void getData(){
        HttpTool.getInstance().getFoodList(getMain().getDevice(),new KaCallBack(new IKaCallBack() {
            @Override
            public void onResult(String json) {
                FoodliST foodliST = new Gson().fromJson(json,FoodliST.class);

                for(FoodliST.ResultDataBean data : foodliST.getResultData()){
                    if(data.getPeriodsType()==0){
                        mStr1 = mStr1+"  "+data.getRecipesName();
                    }
                    if(data.getPeriodsType()==1){
                        mStr2 = mStr2+"  "+data.getRecipesName();
                    }
                    if(data.getPeriodsType()==2){
                        mStr3 = mStr3+"  "+data.getRecipesName();
                    }
                    if(data.getPeriodsType()==3){
                        mStr4 = mStr4+"  "+data.getRecipesName();
                    }
                    if(data.getPeriodsType()==4){
                        mStr5 = mStr5+"  "+data.getRecipesName();
                    }
                }
                mText1.setText(mStr1);
                mText2.setText(mStr2);
                mText3.setText(mStr3);
                mText4.setText(mStr4);
                mText5.setText(mStr5);
            }

            @Override
            public void onErro() {
            getData();
            }
        }));
    }

}
