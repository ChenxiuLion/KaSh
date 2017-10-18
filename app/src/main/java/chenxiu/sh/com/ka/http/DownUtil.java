package chenxiu.sh.com.ka.http;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 下载工具类
 *
 *by chenxiu
 *2015-12-15
 */
public class DownUtil {

    public static DownUtil downUtil;

    @SuppressLint("UseSparseArrays") public static HashMap<Integer , Long> mTheda = new HashMap<Integer, Long>();

    @SuppressLint("UseSparseArrays") public static HashMap<Integer , Long> mSTheda = new HashMap<Integer, Long>(); //总长度

    public static int  index = 0;
    public static DownUtil getInstance(Context context)
    {
        if(downUtil == null)
        { downUtil = new DownUtil(); }
        return downUtil;
    }

    public DownUtil()
    {
        File file = new File(FILE_URL);

        //如果目标文件夹不存在则创建
        if(!file.exists())
        {
            file.mkdirs();
        }
    }

    /**
     * 获取文件夹
     *
     *by chenxiu
     *2015-12-15
     */
    public File getFiles(String path)
    {
        File file = new File(path);

        //如果目标文件夹不存在则创建
        if(!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }
    /**
     * 获取文件
     *
     *by chenxiu
     *2015-12-15
     */
    public File getFile(String fileName)
    {
        File file = new File(FILE_URL+fileName);

        return file;
    }
    public static final String FILE_URL = Environment.getExternalStorageDirectory()
            + File.separator+".XYVideo2" + File.separator;

    public void downFile(String url,final String fileName,final MonitorDown downLisen)
    {
        x.http().request(HttpMethod.GET, null, new Callback.CommonCallback<File>(){
            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static abstract class MonitorDown
    {
        public MonitorDown()
        {
            id = index;
            index++;
        }
        public int id;
        public abstract void onFileExists(HttpException error, String msg,String fileName);

        public abstract void onSuccess();

        public abstract void onLoading(long total, long current);
    }

    /**
     * 获取当前所有下载的图片长度
     *
     *by chenxiu
     *2015-12-15
     */
    public static long getFileLength()
    {
        long count = 0;
        Iterator<Entry<Integer, Long>> iter = mTheda.entrySet().iterator();
        while(iter.hasNext())
        {
            count +=iter.next().getValue();
        }
        return count;

    }
    public static long getFileSLength()
    {
        long count = 0;
        Iterator<Entry<Integer, Long>> iter = mSTheda.entrySet().iterator();
        while(iter.hasNext())
        {
            count +=iter.next().getValue();
        }
        return count;

    }
    public void clearData()
    {
        mSTheda.clear();
        mTheda.clear();
        index = 0;
    }
}