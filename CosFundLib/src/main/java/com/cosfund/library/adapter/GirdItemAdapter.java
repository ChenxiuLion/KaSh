package com.cosfund.library.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cosfund.library.R;
import com.cosfund.library.libdao.SystemImageLoader;
import com.cosfund.library.util.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 系统相册适配器
 */
public class GirdItemAdapter extends BaseAdapter {

    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public static List<String> mSelectedImage = new ArrayList<String>();
    private final int VIEW_TYPE = 2;
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;
    public OnPhotoSelectedListener onPhotoSelectedListener;
    /**
     * 文件夹路径
     */
    private String mDirPath;
    private Context context;
    private List<String> mDatas = new ArrayList<String>();//所有的图片

    public GirdItemAdapter(Context context, List<String> mDatas, String dirPath) {
        super();
        this.context = context;
        this.mDatas = mDatas;
        this.mDirPath = dirPath;
    }

    public void changeData(List<String> mDatas, String dirPath) {
        this.mDatas = mDatas;
        this.mDirPath = dirPath;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size() + 1;
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.photo_grid_item2, null);
                    holder2 = new ViewHolder2();
                    holder2.id_item_image2 = (LinearLayout) convertView.findViewById(R.id.id_item_image2);
                    convertView.setTag(holder2);
                    break;
                case TYPE_2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.photo_grid_item, null);
                    holder = new ViewHolder();
                    holder.id_item_image = (ImageView) convertView.findViewById(R.id.id_item_image);
                    convertView.setTag(holder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE_2:
                    holder = (ViewHolder) convertView.getTag();
                    break;

            }
        }
        switch (type) {
            case TYPE_1:
                holder2.id_item_image2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onPhotoSelectedListener.takePhoto();
                    }
                });
                break;
            case TYPE_2:
                ImageLoaderUtils.displayImage(ImageDownloader.Scheme.FILE.wrap((mDirPath + "/" + mDatas.get(position - 1))),holder.id_item_image,getOption());
              //  SystemImageLoader.getInstance(3, SystemImageLoader.Type.LIFO).loadImage(mDirPath + "/" + mDatas.get(position - 1), holder.id_item_image);
                holder.id_item_image.setColorFilter(null);
                //设置ImageView的点击事件
                holder.id_item_image.setOnClickListener(new MyOnClickListener(holder, position));
                break;
        }
        return convertView;
    }

    public void setOnPhotoSelectedListener(OnPhotoSelectedListener onPhotoSelectedListener) {
        this.onPhotoSelectedListener = onPhotoSelectedListener;
    }

    public interface OnPhotoSelectedListener {
        void photoClick(List<String> number);

        void takePhoto();

        void photoPath(String path);
    }

    private class MyOnClickListener implements OnClickListener {

        ViewHolder holder;

        int position;

        MyOnClickListener(ViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 已经选择过该图片
            mSelectedImage.clear();
            mSelectedImage.add(mDirPath + "/" + mDatas.get(position - 1));
            onPhotoSelectedListener.photoClick(mSelectedImage);
            onPhotoSelectedListener.photoPath(mSelectedImage.get(0));
        }
    }

    class ViewHolder {
        ImageView id_item_image;
    }

    class ViewHolder2 {
        LinearLayout id_item_image2;
    }

    public DisplayImageOptions getOption()
    {
        DisplayImageOptions options;//
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.pictures_no)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();//构建完成
        return  options;
    }
}
