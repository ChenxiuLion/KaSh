package com.cosfund.library.libdao;


import com.cosfund.library.util.SDCardUtils;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 库中常用常量
 */
public class ConstantValue {

    /**
     * ImageLoader缓存路径
     */
    public static final String CACHE_PATH = "file:///mnt/sdcard/cosfund/photo/";
    public static final String CACHE_DIR = SDCardUtils.getSDCardPath() + "cosfund/photo/Image/";

    /**
     * 系统相册
     */
    public static final int CAMERA_REQUEST_CODE = 10000;
    public static final int PHOTO_REQUEST_CUT = 10001;
    /**
     * JPush消息推送
     */
    public static final String MESSAGE_RECEIVED_ACTION = "com.cosfund.app.MESSAGE_RECEIVED_ACTION";
    public static final String MESSAGE_KEY = "message";
    /**
     * 下载媒体资源路径
     */
    public static final String DOWNLOAD_CONTENT_PATH = SDCardUtils.getSDCardPath() + "daoke/photo/data/";
    public static final String DATA_FORM = ".png";

}
