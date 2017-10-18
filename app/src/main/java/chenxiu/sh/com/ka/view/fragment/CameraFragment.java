package chenxiu.sh.com.ka.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.linj.FileOperateUtil;
import com.linj.album.view.FilterImageView;
import com.linj.camera.view.CameraContainer;
import com.linj.camera.view.CameraView;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.List;

import chenxiu.sh.com.ka.R;
import chenxiu.sh.com.ka.base.BaseFragment;
import chenxiu.sh.com.ka.modle.SubmitData;
import chenxiu.sh.com.ka.modle.UpdataResult;
import chenxiu.sh.com.ka.util.Upload;
import chenxiu.sh.com.ka.view.KaMainActivity;
import chenxiu.sh.com.ka.view.TemperatureActivity;

/**
 * Created by Chenxiu on 2016/6/29.
 */
public class CameraFragment extends BaseFragment  implements CameraContainer.TakePictureListener {
    public final static String TAG="CameraAty";
    private boolean mIsRecordMode=false;
    private String mSaveRoot;

    public static CameraFragment cameraFragment;
    @ViewInject(R.id.container)
    private CameraContainer mContainer;

    @ViewInject(R.id.btn_thumbnail)
    private FilterImageView mThumbView;

    @ViewInject(R.id.btn_shutter_camera)
    private ImageButton mCameraShutterButton;

    @ViewInject(R.id.btn_shutter_record)
    private ImageButton mRecordShutterButton;

    @ViewInject(R.id.btn_flash_mode)
    private ImageView mFlashView;

    @ViewInject(R.id.btn_switch_camera)
    private ImageButton mSwitchModeButton;

    @ViewInject(R.id.btn_switch_mode)
    private ImageView mSwitchCameraView;


    @ViewInject(R.id.btn_other_setting)
    private ImageView mSettingView;

    @ViewInject(R.id.videoicon)
    private ImageView mVideoIconView;

    @ViewInject(R.id.camera_header_bar)
    private View mHeaderBar;


    private boolean isRecording=false;



    @Override
    protected int initView() {
        return R.layout.camera;
    }

    @Override
    protected void initData() {
        cameraFragment =this;
        mSaveRoot="test";
        mContainer.setFocusable(false);
        mFlashView.setFocusable(false);
        mContainer.setRootPath(mSaveRoot);
        mContainer.setErro(new CameraContainer.OnCameraErro() {
            @Override
            public void onErro() {
                SubmitData.getInstance().setmPath("");
                getMain().onNextFragment();
            }
        });
        initThumbnail();
        mContainer.setOnCamereEnd(new CameraContainer.OnCamereEnd() {
            @Override
            public void onEnd(final String imagePath) {
                Log.e("123",imagePath);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            String json = Upload.uploadFile(imagePath);
                            LogUtils.e(json);
                            UpdataResult result = new Gson().fromJson(json,UpdataResult.class);
                            SubmitData.getInstance().setPhoto(result.getResultData().getFilePath());;
                            if(isTui == 1){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mpai.onPai();
                                    }
                                });
                            }
                            //SubmitData.getInstance().setmPath(imagePath);;
                        }catch (Exception e){

                        }

                    }
                }.start();
                if(mpai!=null &&isTui == 0) {
                    mpai.onPai();
                }

            }
        });
    }

    private OnPai mpai;

    private int isTui = 0;
    public void pai(OnPai pa){
      pai(0,pa);
    }

    public void pai(int i , OnPai pa){
        isTui = i ;
        mpai = pa;
        mContainer.takePicture(CameraFragment.this);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    public interface OnPai{
        void onPai();
    }
    private void stopRecord() {
        mContainer.stopRecord(this);
        isRecording=false;
        mRecordShutterButton.setBackgroundResource(R.drawable.btn_shutter_record);
    }
    @Override
    public void onTakePictureEnd(Bitmap bm) {
        mCameraShutterButton.setClickable(true);
    }

    @Override
    public void onAnimtionEnd(Bitmap bm, boolean isVideo) {
        if(bm!=null){
            //生成缩略图
            Bitmap thumbnail= ThumbnailUtils.extractThumbnail(bm, 213, 213);
            mThumbView.setImageBitmap(thumbnail);
            if(isVideo)
                mVideoIconView.setVisibility(View.VISIBLE);
            else {
                mVideoIconView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 加载缩略图
     */
    private void initThumbnail() {
        String thumbFolder= FileOperateUtil.getFolderPath(getActivity(), FileOperateUtil.TYPE_THUMBNAIL, mSaveRoot);
        List<File> files=FileOperateUtil.listFiles(thumbFolder, ".jpg");
        if(files!=null&&files.size()>0){
            Bitmap thumbBitmap= BitmapFactory.decodeFile(files.get(0).getAbsolutePath());
            if(thumbBitmap!=null){
                mThumbView.setImageBitmap(thumbBitmap);
                //视频缩略图显示播放图案
                if(files.get(0).getAbsolutePath().contains("video")){
                    mVideoIconView.setVisibility(View.VISIBLE);
                }else {
                    mVideoIconView.setVisibility(View.GONE);
                }
            }
        }else {
            mThumbView.setImageBitmap(null);
            mVideoIconView.setVisibility(View.VISIBLE);
        }

    }

}
