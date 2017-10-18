package chenxiu.sh.com.ka.modle;

import java.util.List;

/**
 * Created by Chenxiu on 2016/11/27.
 */

public class GetQian {
    /**
     * ResultData : [{"ID":11399,"CheckTime":"2016-11-26 22:21:43","CheckType":1,"Photo":"2016/11/26/8e13617893c8487681ec572a0bbd407e.jpg","CardNO":"1952480340","ClassID":1,"BabyID":2,"UserName":"张山","Weight":0,"Temperature":0,"Height":0,"HealthStatus":0,"Reason":"123123","Area":null,"AgentName":null,"KindergartenName":null,"StatisticalRate":null}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    private List<ResultDataBean> ResultData;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        /**
         * ID : 11399
         * CheckTime : 2016-11-26 22:21:43
         * CheckType : 1
         * Photo : 2016/11/26/8e13617893c8487681ec572a0bbd407e.jpg
         * CardNO : 1952480340
         * ClassID : 1
         * BabyID : 2
         * UserName : 张山
         * Weight : 0
         * Temperature : 0
         * Height : 0
         * HealthStatus : 0
         * Reason : 123123
         * Area : null
         * AgentName : null
         * KindergartenName : null
         * StatisticalRate : null
         */

        private int ID;
        private String CheckTime;
        private int CheckType;
        private String Photo;
        private String CardNO;
        private int ClassID;
        private int BabyID;
        private String UserName;
        private float Weight;
        private float Temperature;
        private float Height;
        private int HealthStatus;
        private String Reason;
        private Object Area;
        private Object AgentName;
        private Object KindergartenName;
        private Object StatisticalRate;

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

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getBabyID() {
            return BabyID;
        }

        public void setBabyID(int BabyID) {
            this.BabyID = BabyID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public float getWeight() {
            return Weight;
        }

        public void setWeight(float Weight) {
            this.Weight = Weight;
        }

        public float getTemperature() {
            return Temperature;
        }

        public void setTemperature(int Temperature) {
            this.Temperature = Temperature;
        }

        public float getHeight() {
            return Height;
        }

        public void setHeight(float Height) {
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

        public Object getArea() {
            return Area;
        }

        public void setArea(Object Area) {
            this.Area = Area;
        }

        public Object getAgentName() {
            return AgentName;
        }

        public void setAgentName(Object AgentName) {
            this.AgentName = AgentName;
        }

        public Object getKindergartenName() {
            return KindergartenName;
        }

        public void setKindergartenName(Object KindergartenName) {
            this.KindergartenName = KindergartenName;
        }

        public Object getStatisticalRate() {
            return StatisticalRate;
        }

        public void setStatisticalRate(Object StatisticalRate) {
            this.StatisticalRate = StatisticalRate;
        }
    }
}
