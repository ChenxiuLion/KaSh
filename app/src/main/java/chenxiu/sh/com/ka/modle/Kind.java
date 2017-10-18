package chenxiu.sh.com.ka.modle;

/**
 * Created by Chenxiu on 2016/7/12.
 */
public class Kind {

    /**
     * ID : 1
     * Name : sample string 2
     * CreateTime : 2016-07-12T21:09:31.8610703+08:00
     * Remark : sample string 4
     * Status : true
     * Location : sample string 6
     * AgentID : 7
     * QRCodeLink : sample string 8
     * VideoViewStartTime : 2016-07-12T21:09:31.8620468+08:00
     * VideoViewEndTime : 2016-07-12T21:09:31.8620468+08:00
     */

    private ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"Name":"sample string 2","CreateTime":"2016-07-12T21:09:31.8610703+08:00","Remark":"sample string 4","Status":true,"Location":"sample string 6","AgentID":7,"QRCodeLink":"sample string 8","VideoViewStartTime":"2016-07-12T21:09:31.8620468+08:00","VideoViewEndTime":"2016-07-12T21:09:31.8620468+08:00"}
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
        private String Location;
        private int AgentID;
        private String QRCodeLink;
        private String VideoViewStartTime;
        private String VideoViewEndTime;

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

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public String getQRCodeLink() {
            return QRCodeLink;
        }

        public void setQRCodeLink(String QRCodeLink) {
            this.QRCodeLink = QRCodeLink;
        }

        public String getVideoViewStartTime() {
            return VideoViewStartTime;
        }

        public void setVideoViewStartTime(String VideoViewStartTime) {
            this.VideoViewStartTime = VideoViewStartTime;
        }

        public String getVideoViewEndTime() {
            return VideoViewEndTime;
        }

        public void setVideoViewEndTime(String VideoViewEndTime) {
            this.VideoViewEndTime = VideoViewEndTime;
        }
    }
}
