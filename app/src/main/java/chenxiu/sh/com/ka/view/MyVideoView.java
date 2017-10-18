package chenxiu.sh.com.ka.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.VideoView;

/**
 * Created by Chenxiu on 2016/6/29.
 */
public class MyVideoView extends VideoView {

    private  int mWidth=1000;
    public MyVideoView(Context context) {
        super(context);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mWidth, widthMeasureSpec);
        int height = getDefaultSize(mWidth, heightMeasureSpec);
        setMeasuredDimension(width,height);
    }
}
