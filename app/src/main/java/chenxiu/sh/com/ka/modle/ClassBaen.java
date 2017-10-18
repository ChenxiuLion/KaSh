package chenxiu.sh.com.ka.modle;

/**
 * Created by Chenxiu on 2016/6/30.
 */
public class ClassBaen {

    /**
     * ID : 1
     * Name : sample string 2
     * CreateTime : 2016-06-30T23:07:18.1959375+08:00
     * Remark : sample string 4
     * Status : true
     * GradeID : 6
     */

    private ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"Name":"sample string 2","CreateTime":"2016-06-30T23:07:18.1959375+08:00","Remark":"sample string 4","Status":true,"GradeID":6}
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
        private String Name;
        private String CreateTime;
        private String Remark;
        private boolean Status;
        private int GradeID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public int getGradeID() {
            return GradeID;
        }

        public void setGradeID(int GradeID) {
            this.GradeID = GradeID;
        }
    }
}
