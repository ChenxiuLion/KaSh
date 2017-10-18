package chenxiu.sh.com.ka.modle;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenxiu on 2016/6/23.
 */
public class SubmitData {

    public static SubmitData mSubmitData;
    /**
     * ID : 1
     * CheckTime : 2016-06-26T13:02:36.4853515+08:00
     * CheckType : 2
     * Photo : sample string 3
     * CardNO : sample string 4
     * Weight : 1.0
     * Temperature : 1.0
     * Height : 5.0
     * HealthStatus : 1
     * Reason : sample string 6
     * Area : sample string 7
     * AgentName : sample string 8
     * KindergartenName : sample string 9
     * StatisticalRate : sample string 10
     */

    private int ID;
    private String CheckTime;
    private int CheckType;
    private String Photo;
    private String CardNO;
    private double Weight;
    private double Temperature;
    private double Height;
    private int HealthStatus;
    private String Reason;
    private String Area;
    private String AgentName;
    private String KindergartenName;
    private String EntryPhoto;

    public String getEntryPhoto() {
        return EntryPhoto;
    }

    public void setEntryPhoto(String entryPhoto) {
        EntryPhoto = entryPhoto;
    }

    private String StatisticalRate;

    private String mPath;

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public static SubmitData getNewInstance(){
        mSubmitData = new SubmitData();
        return mSubmitData;
    }


    public static SubmitData getInstance(){
        if(mSubmitData == null){
            return getNewInstance();
        }else{
            return mSubmitData;
        }
    }


    public void setCheckTime(long CheckTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.CheckTime = format.format(new Date(CheckTime));
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String CheckTime) {
        this.CheckTime = CheckTime;
    }

    public int getCheckType() {
        return CheckType;
    }

    public void setCheckType(int CheckType) {
        this.CheckType = CheckType;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getCardNO() {
        return CardNO;
    }

    public void setCardNO(String CardNO) {
        this.CardNO = CardNO;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double Weight) {
        this.Weight = Weight;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double Temperature) {
        this.Temperature = Temperature;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double Height) {
        this.Height = Height;
    }

    public int getHealthStatus() {
        return HealthStatus;
    }

    public void setHealthStatus(int HealthStatus) {
        this.HealthStatus = HealthStatus;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String AgentName) {
        this.AgentName = AgentName;
    }

    public String getKindergartenName() {
        return KindergartenName;
    }

    public void setKindergartenName(String KindergartenName) {
        this.KindergartenName = KindergartenName;
    }

    public String getStatisticalRate() {
        return StatisticalRate;
    }

    public void setStatisticalRate(String StatisticalRate) {
        this.StatisticalRate = StatisticalRate;
    }
}
