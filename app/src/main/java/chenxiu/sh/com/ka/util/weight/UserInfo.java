package chenxiu.sh.com.ka.util.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author xingguozhu
 * 
 */
public class UserInfo implements Serializable {

    public static final int HEALTH_STATUS_GOOD = 1;
    public static final int HEALTH_STATUS_SUB = 2;
    public static final int HEALTH_STATUS_THIN = 3;
    public static final int HEALTH_STATUS_BAD = 4;
    /**
	 * 
	 */
    private static final long serialVersionUID = -1925114272299021250L;

    public int mUserId;
    public String mUserName;
    public String mBirth;
    // 0:male;1:female
    public byte mGender;
    public int mHeight;

    public Bitmap mUserPhotoBmp;
    public int mHealthStatus;
    public String mTipText;

    public BodyInfo mBodyInfo;

    public UserInfo() {
        mBodyInfo = new BodyInfo();
    }

    @Override
    public String toString() {
        return "name:" + mUserName + ", weight: " + mBodyInfo.mWeight + ", bmi: " + mBodyInfo.mBmi + ", mFat: "
                + mBodyInfo.mFat + ", water: " + mBodyInfo.mWater;
        // return mUserName;
    }

    @Override
    public boolean equals(Object o) {

        if (TextUtils.isEmpty(this.mUserName))
            return false;

        if (o instanceof UserInfo) {
            UserInfo user = (UserInfo) o;
            if (this.mUserName.equals(user.mUserName)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public float getUserWeightWithUnit(Context context) {
        if (TextUtils.isEmpty(mBodyInfo.mWeight)) {
            return 0;
        }

        if ("Kg".equalsIgnoreCase(Utils.getWeightUnit(context))) {

            BigDecimal bd = new BigDecimal(Float.parseFloat(mBodyInfo.mWeight));
            bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);

            return Float.parseFloat(mBodyInfo.mWeight);
        } else {
            BigDecimal bd = new BigDecimal(
                    Float.parseFloat(mBodyInfo.mWeight) * 2.20462);
            bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();
        }

    }

}
