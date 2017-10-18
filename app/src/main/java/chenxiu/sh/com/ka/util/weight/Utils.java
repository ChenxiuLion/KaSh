package chenxiu.sh.com.ka.util.weight;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private final static String FORMATE_DATE_STR = "yyyy-MM-dd";

    public static final int SCALE_TYPE_FAT = 0;// 脂肪秤
    public static final int SCALE_TYPE_WEIGHT = 1;// 体重秤

    private static UserInfo info;
    
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * Convert hex string to byte[]
     * 
     * @param hexString
     *            the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * 
     * @param c
     *            char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static float paraseWeight(byte[] srcData) {
        // 解析体重秤的体重字段
        if (srcData == null || srcData.length < 5) {
            return 0;
        }

        int[] srcIntData = byteArrayToIntArray(srcData);

        int acc = srcIntData[0] & 0xf;
        double weightAccuracy = Math.pow(10, acc);
        float testWeight = srcIntData[1] * 0x1000000 + srcIntData[2] * 0x10000 + srcIntData[3]
                * 0x100 + srcIntData[4];

        float weight = (float) (testWeight / weightAccuracy);

        return weight;

    }

    private static int[] byteArrayToIntArray(byte[] srcData) {
        if (srcData == null) {
            return null;
        }

        int[] data = new int[srcData.length];

        for (int i = 0; i < srcData.length; i++) {
            data[i] = srcData[i] & 0xff;
        }
        return data;
    }

    public static UserInfo paraseWeightScaleData(byte[] srcData, UserInfo userInfo) {
        // 解析体重秤上行数据

        if (srcData == null || userInfo == null) {
            return null;
        }
        if (srcData.length < 5) {
            return null;
        }
        int[] srcIntData = byteArrayToIntArray(srcData);

        int acc = srcIntData[0] & 0xf;
        double weightAccuracy = Math.pow(10, acc);
        float testWeight = srcIntData[1] * 0x1000000 + srcIntData[2] * 0x10000 + srcIntData[3]
                * 0x100 + srcIntData[4];

        float weight = (float) (testWeight / weightAccuracy);

        int height = Integer.valueOf(userInfo.mHeight);
        if (height > 0) {
            BigDecimal bd = new BigDecimal(weight * 10000.0 / (height * height));
            bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
            userInfo.mBodyInfo.mBmi = "" + bd.floatValue();
        }

        userInfo.mBodyInfo.mWeight = String.valueOf(weight);

        return userInfo;
    }

    static UserInfo paraseData(int[] srcData, UserInfo userInfo) {
        // 解析脂肪秤上行数据
        if (userInfo == null) {
            return null;
        }
        BodyInfo bodyInfo = userInfo.mBodyInfo;
        float weight = (float) ((srcData[4] * 256 + srcData[5]) / 10.0);
        int height = 1;

        if (srcData[6] == 255 || srcData[7] == 255) {
            bodyInfo.mFat = "";
            bodyInfo.mWater = "";
            bodyInfo.mBone = "";
            bodyInfo.mMuscle = "";
            bodyInfo.mVisceralFat = "";
            bodyInfo.mCalorie = "";
        } else {
            bodyInfo.mFat = String.valueOf((srcData[6] * 256 + srcData[7]) / 10.0);
            bodyInfo.mWater = String.valueOf((srcData[8] * 256 + srcData[9]) / 10.0);
            bodyInfo.mBone = String.valueOf((srcData[10] * 256 + srcData[11]) / 10.0);
            bodyInfo.mMuscle = String.valueOf((srcData[12] * 256 + srcData[13]) / 10.0);
            bodyInfo.mVisceralFat = String.valueOf(srcData[14]);
            bodyInfo.mCalorie = String.valueOf(srcData[15] * 256 + srcData[16]);
        }

        height = Integer.valueOf(userInfo.mHeight);

        if (height > 0) {
            BigDecimal bd = new BigDecimal(weight * 10000.0 / (height * height));
            bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);

            bodyInfo.mBmi = "" + bd.floatValue();
        }
        userInfo.mBodyInfo = bodyInfo;
        return userInfo;
    }

    public static UserInfo decodeUpdata(byte[] srcData, UserInfo userInfo) {
        if (srcData == null) {
            return userInfo;
        }
        int[] data = new int[srcData.length];
        for (int i = 0; i < srcData.length; i++) {
            data[i] = srcData[i] & 0xff;
        }
        return paraseData(data, userInfo);
    }


    public static String getWeightUnit(Context context) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
        if ("ISO".equals(sp.getString("unit", "ISO"))) {
            return "Kg";
        } else {
            return "Lb";
        }
    }

    public static void setWeightUnit(Context context, String unit) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor se = sp.edit();
        if (unit.equals("Lb")) {
            se.putString("unit", "Inch");
        } else {
            se.putString("unit", "ISO");
        }

        se.commit();
    }

    public static String getUnit(Context context) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
        return sp.getString("unit", "ISO");
    }
    
    public static UserInfo getCurrentUserInfo() {
        info = new UserInfo();
        info.mUserId = 0;
        info.mUserName = "test";
        info.mBirth = "1990-1-20";
        info.mGender = 0;
        info.mHeight = 170;
        return info;
    }

    public static byte[] packageDownData(UserInfo userInfo) {
        byte data[] = new byte[5];

        data[0] = 0x10;
        data[1] = (byte) userInfo.mUserId;
        data[2] = userInfo.mGender;
        data[3] = (byte) Utils.getCurrentAgeByBirthdate(userInfo.mBirth);

        data[4] = Integer.valueOf(userInfo.mHeight).byteValue();

        return data;
    }

    public static int getCurrentAgeByBirthdate(String brithday) {

        if (TextUtils.isEmpty(brithday)) {
            return 0;
        }

        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatDate = new SimpleDateFormat(FORMATE_DATE_STR);
            String currentTime = formatDate.format(calendar.getTime());
            Date today = formatDate.parse(currentTime);
            Date brithDay = formatDate.parse(brithday);

            return today.getYear() - brithDay.getYear();
        } catch (Exception e) {
            return 0;
        }
    }

}
