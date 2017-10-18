package chenxiu.sh.com.ka.modle;

/**
 * Created by Chenxiu on 2016/6/16.
 */
public class Student {


    /**
     * ID : 1
     * UserName : sample string 2
     * EntryDate : 2016-06-16T08:48:37.7815+08:00
     * Photo : sample string 4
     * BornDate : 2016-06-16T08:48:37.7815+08:00
     * CardNO : sample string 6
     * LearnStatus : true
     * Status : true
     * ClassID : 9
     * GradeID : 10
     * KindergartenID : 11
     */

    private ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"UserName":"sample string 2","EntryDate":"2016-06-16T08:48:37.7815+08:00","Photo":"sample string 4","BornDate":"2016-06-16T08:48:37.7815+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"GradeID":10,"KindergartenID":11}
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

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

    public static class ResultDataBean {
        private int ID;
        private String UserName;
        private String EntryDate;
        private String Photo;
        private String BornDate;
        private String EntryPhoto;

        public String getEntryPhoto() {
            return EntryPhoto;
        }

        public void setEntryPhoto(String entryPhoto) {
            EntryPhoto = entryPhoto;
        }

        private String CardNO;
        private boolean LearnStatus;
        private boolean Status;
        private int ClassID;
        private int GradeID;
        private int KindergartenID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getEntryDate() {
            return EntryDate;
        }

        public void setEntryDate(String EntryDate) {
            this.EntryDate = EntryDate;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getBornDate() {
            return BornDate;
        }

        public void setBornDate(String BornDate) {
            this.BornDate = BornDate;
        }

        public String getCardNO() {
            return CardNO;
        }

        public void setCardNO(String CardNO) {
            this.CardNO = CardNO;
        }

        public boolean isLearnStatus() {
            return LearnStatus;
        }

        public void setLearnStatus(boolean LearnStatus) {
            this.LearnStatus = LearnStatus;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getGradeID() {
            return GradeID;
        }

        public void setGradeID(int GradeID) {
            this.GradeID = GradeID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }
    }
}
