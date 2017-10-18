package chenxiu.sh.com.ka.modle;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/11.
 */
public class FoodliST {

    /**
     * ResultData : [{"ID":1,"RecipesName":"sample string 2","PeriodsType":3,"Photo":"sample string 4","GardenerLeaderID":5,"GardenerLeaderUserName":"sample string 6","KindergartenID":7,"CreateTime":"2016-07-11T22:57:34.0192734+08:00"},{"ID":1,"RecipesName":"sample string 2","PeriodsType":3,"Photo":"sample string 4","GardenerLeaderID":5,"GardenerLeaderUserName":"sample string 6","KindergartenID":7,"CreateTime":"2016-07-11T22:57:34.0192734+08:00"}]
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 1
     * RecipesName : sample string 2
     * PeriodsType : 3
     * Photo : sample string 4
     * GardenerLeaderID : 5
     * GardenerLeaderUserName : sample string 6
     * KindergartenID : 7
     * CreateTime : 2016-07-11T22:57:34.0192734+08:00
     */

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
        private int ID;
        private String RecipesName;
        private int PeriodsType;
        private String Photo;
        private int GardenerLeaderID;
        private String GardenerLeaderUserName;
        private int KindergartenID;
        private String CreateTime;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getRecipesName() {
            return RecipesName;
        }

        public void setRecipesName(String RecipesName) {
            this.RecipesName = RecipesName;
        }

        public int getPeriodsType() {
            return PeriodsType;
        }

        public void setPeriodsType(int PeriodsType) {
            this.PeriodsType = PeriodsType;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public int getGardenerLeaderID() {
            return GardenerLeaderID;
        }

        public void setGardenerLeaderID(int GardenerLeaderID) {
            this.GardenerLeaderID = GardenerLeaderID;
        }

        public String getGardenerLeaderUserName() {
            return GardenerLeaderUserName;
        }

        public void setGardenerLeaderUserName(String GardenerLeaderUserName) {
            this.GardenerLeaderUserName = GardenerLeaderUserName;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
