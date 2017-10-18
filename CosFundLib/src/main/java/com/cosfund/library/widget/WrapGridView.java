package com.cosfund.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 自适应高度包裹内容的GridView
 */
public class WrapGridView extends GridView {

    public WrapGridView(Context context) {
        super(context);
    }

    public WrapGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
