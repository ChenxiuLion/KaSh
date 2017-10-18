package chenxiu.sh.com.ka.modle;

/**
 * Created by Chenxiu on 2016/6/26.
 */
public class UpdataResult {

    /**
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ResultData : {"ID":0,"FileName":"20160626190354.jpg","CreateTime":"2016-06-26T19:03:54.0371093+08:00","CreateUserID":1,"CreateUserName":"aaaa","FilePath":"C:\\ChildPlatformFiles\\2016\\6\\26\\b764400723d7413aa34f0efb23d6c604.jpg"}
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    /**
     * ID : 0
     * FileName : 20160626190354.jpg
     * CreateTime : 2016-06-26T19:03:54.0371093+08:00
     * CreateUserID : 1
     * CreateUserName : aaaa
     * FilePath : C:\ChildPlatformFiles\2016\6\26\b764400723d7413aa34f0efb23d6c604.jpg
     */

    private ResultDataBean ResultData;
    private int ReusltDataTotal;

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

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }

    public static class ResultDataBean {
        private int ID;
        private String FileName;
        private String CreateTime;
        private int CreateUserID;
        private String CreateUserName;
        private String FilePath;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }
    }
}
