package chenxiu.sh.com.ka.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 网络接口回调类
 * Created by Chenxiu on 2016/6/12.
 */
public class KaCallBack implements Callback {

    public Handler mHandler = new Handler(Looper.getMainLooper());


    public IKaCallBack mCallBack;

    public KaCallBack(IKaCallBack callBack){
        mCallBack = callBack;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onErro();
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        final String str = response.body().string();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onResult(str);
            }
        });

    }

}
