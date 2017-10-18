package chenxiu.sh.com.ka.http;

import android.os.Environment;

import com.alibaba.fastjson.JSONObject;
import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.logger.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import chenxiu.sh.com.ka.constant.Constant;
import chenxiu.sh.com.ka.modle.Food;
import chenxiu.sh.com.ka.modle.LoginBean;
import chenxiu.sh.com.ka.modle.SubmitData;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络交互
 * Created by Chenxiu on 2016/6/12.
 */
public class HttpTool {

    public OkHttpClient mOkClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static HttpTool getInstance() {
        return new HttpTool();
    }

    public static final String FILE_URL = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ka";

    public HttpTool() {
        mOkClient = new OkHttpClient();
    }


    public void getGartenLeader(String id, KaCallBack callBack) {
        doGet(Constant.BASE_URL + "api/GartenLeader/" + id, callBack);
    }

    /**
     * 获取文件夹
     * <p/>
     * by chenxiu
     * 2015-12-15
     */
    public File getFiles(String path) {
        File file = new File(path);
        //如果目标文件夹不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public void getFile(String url, FileDownloadListener aaa) {
        getFiles(FILE_URL);
        LogUtils.e(Constant.IMAGE_URL+url.replace("\"","").replace("\n",""));
        FileDownloader.getImpl().create(Constant.IMAGE_URL+url.replace("\"","").replace("\n","")).setPath(FILE_URL + "azzzsasa.mp4").setListener(aaa).start();
    }

    public void getClassInfo(String id, KaCallBack callBack) {
        doGet(Constant.BASE_URL + "api/Class/" + id, callBack);
    }

    public void getKindInfo(String id, KaCallBack callBack) {
        doGet(Constant.BASE_URL + "api/Kindergarten/" + id, callBack);
    }

    public void isQiandao(String ca,KaCallBack callBack){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        jsonObject.put("CheckDay",dateFormat.format(new Date()));
        jsonObject.put("CardNO",ca);
        doPost(Constant.BASE_URL+"api/AttendanceRecord/SearchList",jsonObject.toJSONString(),callBack);

    }

    public void getBabyInfo(String id, KaCallBack callBack) {
        doGet(Constant.BASE_URL + "api/Baby?cardNo=" + id, callBack);
    }

    public void getFoodList(LoginBean login, KaCallBack callBack) {
        Food food = new Food();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        food.setStartTime(format.format(new Date(System.currentTimeMillis())));
        food.setEndTime(format.format(new Date(System.currentTimeMillis()+1000*60*60*24)));
        food.setKindergartenID(login.getResultData().getKindergartenID());

        doPost(Constant.BASE_URL + "api/BabyRecipes/SearchList", new Gson().toJson(food), callBack);
    }


    public void getKindNews(String kindId,KaCallBack callBack){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        jsonObject.put("CheckDay",dateFormat.format(new Date()));
       // jsonObject.put("CardNO",ca);

       // doPost(Constant.BASE_URL + "api/Notice/GetKindergartenNoticePagedList", new Gson().toJson(news), callback);
    }
    public void addKindergartenRecord(KaCallBack callBack) {
        addKindergartenRecord(1,callBack);
    }

    public void addKindergartenRecord( int i ,KaCallBack callBack) {
        SubmitData data = SubmitData.getInstance();
        data.setCheckType(i);
        data.setArea("1231231");
        data.setStatisticalRate("123123");
        data.setAgentName("123123");
        data.setKindergartenName("12312312");
        Logger.json(new Gson().toJson(data));
        doPost(Constant.BASE_URL + "api/AttendanceRecord/AddKindergartenRecord", new Gson().toJson(data), callBack);

    }

    public void showWx(String card,KaCallBack callBack){
        doGet("http://wxapi.youjiaopingtai.com/wx/index.asp?cardno="+card+"&action=qd&key=123456&btn=%E7%AD%BE%E5%88%B0", callBack);
    }

    public void getDeviceInfo(String id, KaCallBack callBack) {
        doGet(Constant.BASE_URL + "api/AttendanceMachine/ConfirmState?account=" + id, callBack);
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("image/jpeg; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void updataFile(String filePath, KaCallBack callBack) {
        File file = new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addPart(RequestBody.create(MEDIA_TYPE_PNG, file));
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "/api/Attachment/Add?createUserID=1&createUserName=aaaa")
                .post(requestBody)
                .build();
        mOkClient.newCall(request).enqueue(callBack);
    }


    public void updataFileA(String filePath, KaCallBack callBack) {
        File file = new File(filePath);
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "api/Attachment")
                .post(RequestBody.create(MultipartBody.FORM, file))
                .build();
        mOkClient.newCall(request).enqueue(callBack);
    }


    public static final MediaType MEDIA_TYPE_MARKDOWNA
            = MediaType.parse("text/x-markdown; charset=utf-8");

    /**
     * get方法
     *
     * @param url      接口地址
     * @param callback 回调方法
     */
    private void doGet(String url, Callback callback) {
        Logger.e("请求方式  get\n地址：" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        mOkClient.newCall(request).enqueue(callback);
    }

    /**
     * post方法
     *
     * @param url      接口地址
     * @param json     参数
     * @param callback 回调方法
     */
    private void doPost(String url, String json, Callback callback) {
        LogUtils.e("请求方式  post");
        Logger.json(json);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        mOkClient.newCall(request).enqueue(callback);
    }

    public void uploadFile(String uploadFile) {
        String end = "/r/n";
        String Hyphens = "--";
        String boundary = "*****";
        try {
            URL url = new URL(Constant.BASE_URL + "api/Attachment/Add?createUserID=1&createUserName=aaaa");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
      /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
      /* setRequestProperty */
            con.setRequestProperty("Charset", "UTF-8");
      /* 设定DataOutputStream */
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(Hyphens + boundary + end);
            ds.writeBytes(end);
      /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(uploadFile);
      /* 设定每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
      /* 从文件读取数据到缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
        /* 将数据写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(Hyphens + boundary + Hyphens + end);
            fStream.close();
            ds.flush();
      /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            LogUtils.e("上传成功");
        } catch (Exception e) {
            LogUtils.e("上传失败" + e.getMessage());
        }
    }

    /**
     * 通过post完成文件的上传
     */
    public static void postFile(String fileName) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constant.BASE_URL + "api/Attachment/Add?createUserID=1&createUserName=aaaa");
        try {
            // 需要上传的文件
            File uploadFile = new File(fileName);
            //定义FileEntity对象
            HttpEntity entity = new FileEntity(uploadFile, "multipart/form-data; charset=utf-8");
            //为httpPost设置头信息
            httpPost.setEntity(entity); //设置实体对象

            // httpClient执行httpPost提交
            HttpResponse response = httpClient.execute(httpPost);
            // 得到服务器响应实体对象
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println(EntityUtils.toString(responseEntity, "utf-8"));
                System.out.println("文件 " + fileName + "上传成功！");
            } else {
                System.out.println("服务器无响应！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            httpClient.getConnectionManager().shutdown();
        }
    }
}
