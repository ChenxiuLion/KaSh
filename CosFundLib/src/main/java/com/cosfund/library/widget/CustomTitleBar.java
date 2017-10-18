package com.cosfund.library.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cosfund.library.R;

/**
 * 自定义TitleBar
 */
public class CustomTitleBar extends LinearLayout {

    /**
     * back返回
     */
    public TextView mBackTv;
    /**
     * 返回Button
     */
    private LinearLayout backButton;
    /**
     * title标题
     */
    private TextView mTitleTv;
    /**
     * 设置Button
     */
    private LinearLayout settingButton;
    /**
     * setting设置
     */
    private TextView mSettingTv;

    /**
     * 设置icon
     */
    private Typeface typeface;

    public CustomTitleBar(final Activity context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.com_title_bar, this);
        backButton = (LinearLayout) findViewById(R.id.com_title_back_view);
        mBackTv = (TextView) findViewById(R.id.com_title_back);
        mTitleTv = (TextView) findViewById(R.id.com_title_content);
        settingButton = (LinearLayout) findViewById(R.id.com_title_setting_view);
        mSettingTv = (TextView) findViewById(R.id.com_title_setting);
        // 设置Icon
        typeface = Typeface.createFromAsset(context.getAssets(), "BackAndSetting.ttf");
        mBackTv.setText(R.string.com_back);
        mBackTv.setTypeface(typeface);
        mSettingTv.setText(R.string.com_setting);
        mSettingTv.setTypeface(typeface);
        // 返回按钮事件处理
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });
    }


    /**
     * 点击标题栏返回
     *
     * @param onClickListener
     */
    public void setOnClickBackLayout(OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }

    /**
     * 点击标题栏返回
     *
     * @param onClickListener
     */
    public void setOnClickLeftMenu(OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }

    /**
     * 设置左侧Button样式
     *
     * @param typeface
     */
    public void setBackButtonTypeface(Typeface typeface) {
        mBackTv.setTypeface(typeface);
    }

    /**
     * 点击标题栏setting
     *
     * @param onClickListener
     */
    public void setOnClickRightMenu(OnClickListener onClickListener) {
        settingButton.setOnClickListener(onClickListener);
    }

    /**
     * 设置右侧Button样式
     *
     * @param typeface
     */
    public void setSettingButtonTypeface(Typeface typeface) {
        mSettingTv.setTypeface(typeface);
    }

    /**
     * 设置左侧按钮文字
     *
     * @param back
     */
    public void setBackButtonText(CharSequence back) {
        mBackTv.setText(back);
    }

    /**
     * 设置左侧按钮文字
     *
     * @param back
     */
    public void setBackButtonText(int back) {
        mBackTv.setText(back);
    }

    /**
     * 返回按钮大小
     */
    public void setBackButtonSize(float size) {
        mBackTv.setTextSize(size);
    }

    /**
     * 设置左侧Icon是否显示
     *
     * @param visibility
     */
    public void setBackButtonVisibility(int visibility) {
        mBackTv.setVisibility(visibility);
        backButton.setClickable(false);
    }

    /**
     * 返回按钮颜色
     *
     * @param color 此处将颜色按照字符串传递
     */
    public void setBackButtonColor(int color) {
        mBackTv.setTextColor(color);
    }

    /**
     * 设置标题文字
     */
    public void setTitleContent(String title) {
        mTitleTv.setText(title);
    }

    /**
     * 设置标题文字大小
     */
    public void setTitleSize(float size) {
        mTitleTv.setTextSize(size);
    }

    /**
     * 设置标题字体颜色
     *
     * @param color 此处将颜色按照字符串传递
     */
    public void setTitleColor(int color) {
        mTitleTv.setTextColor(color);
    }

    /**
     * 点击标题栏，右边文字事件，回调
     */
    public void setOnClickTitleRight(final Activity activity, final Class<?> clazz) {
        settingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, clazz));
            }
        });
    }

    /**
     * 设置右侧按钮大小
     *
     * @param size
     */
    public void setSettingButtonSize(float size) {
        mSettingTv.setTextSize(size);
    }

    /**
     * 设置标题字体颜色
     *
     * @param color 此处将颜色按照字符串传递
     */
    public void setSettingButtonColor(int color) {
        mSettingTv.setTextColor(color);
    }

    /**
     * 设置右侧菜单
     * @param content
     */
    public void setSettingButtonText(int content){
        mSettingTv.setText(content);
    }

    /**
     * 设置右侧Icon是否显示
     *
     * @param visibility
     */
    public void setSettingButtonVisibility(int visibility) {
        mSettingTv.setVisibility(visibility);
        settingButton.setClickable(false);
    }
    /**
     * 设置右侧菜单
     * @param content
     */
    public void setSettingButtonText(CharSequence content){
        mSettingTv.setText(content);
    }

    /**
     * 获得左侧按钮
     */
    public TextView getBrakBotton()
    {
        return  mBackTv;
    }
}
