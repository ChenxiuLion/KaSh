package com.cosfund.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cosfund.library.R;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 自定义Toast
 */
public class HintToastUtils {

    /**
     * 纯文本提示
     *
     * @param context 上下文
     * @param text    提示语
     */
    public static void showToast(Context context, String text) {
        LinearLayout hintGroupView = new LinearLayout(context);
        TextView hintText = new TextView(context);
        hintGroupView.setAlpha(0.8f);
        hintGroupView.setBackgroundColor(Color.BLACK);
        hintGroupView.setGravity(Gravity.CENTER);
        hintGroupView.setPadding(50, 20, 50, 20);
        hintGroupView.addView(hintText);
        hintText.setText(text);
        hintText.setTextColor(Color.WHITE);
        Toast hintToast = new Toast(context);
        hintToast.setView(hintGroupView);
        hintToast.setDuration(Toast.LENGTH_SHORT);
        hintToast.setGravity(Gravity.CENTER, 0, 0);
        hintToast.show();
    }

    /**
     * 带图片的提示框
     *
     * @param context 上下文
     * @param text    提示语
     * @param isLong  是否类型
     */
    public static void showBitmapToast(Context context, String text, boolean isLong) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.layout_toast_view, null);
        LinearLayout hintGroupView = (LinearLayout) layout.findViewById(R.id.hintToast_layout_group);
        ImageView hintImage = (ImageView) layout.findViewById(R.id.hintToast_layout_image);
        TextView hintContent = (TextView) layout.findViewById(R.id.hintToast_layout_text);
        hintContent.setText(text);
        Toast hintToast = new Toast(context);
        hintToast.setGravity(Gravity.CENTER, 0, 0);
        if (isLong == true) {
            hintToast.setDuration(Toast.LENGTH_SHORT);
        } else if (isLong == false) {
            hintToast.setDuration(Toast.LENGTH_LONG);
        }
        hintToast.setView(layout);
        hintToast.show();
    }
}
