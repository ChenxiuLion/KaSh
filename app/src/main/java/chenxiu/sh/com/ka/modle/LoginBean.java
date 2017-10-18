package chenxiu.sh.com.ka.modle;

import android.text.TextUtils;

/**
 * Created by Chenxiu on 2016/6/19.
 */
public class LoginBean {


    /**
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ResultData : {"ID":5,"Account":"A20160614","LastConfirmTime":"2016-06-26T22:58:51.5859375+08:00","Position":null,"ADLink":"http://122.114.98.206:8080/1.mp4","AgentID":1,"KindergartenID":1,"TemperatureMac":"0f:04:e0:42:90:e6\r\n","WeightMac":"88:C2:55:17:BB:0D\r\n"}
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    /**
     * ID : 5
     * Account : A20160614
     * LastConfirmTime : 2016-06-26T22:58:51.5859375+08:00
     * Position : null
     * ADLink : http://122.114.98.206:8080/1.mp4
     * AgentID : 1
     * KindergartenID : 1
     * TemperatureMac : 0f:04:e0:42:90:e6

     * WeightMac : 88:C2:55:17:BB:0D

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
        if(TextUtils.isEmpty(ResultMessage))
        {
            ResultMessage="";
        }
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
        private String Account;
        private String LastConfirmTime;
        private Object Position;
        private String ADLink;
        private int AgentID;
        private int KindergartenID;
        private String TemperatureMac;
        private String WeightMac;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getLastConfirmTime() {
            return LastConfirmTime;
        }

        public void setLastConfirmTime(String LastConfirmTime) {
            this.LastConfirmTime = LastConfirmTime;
        }

        public Object getPosition() {
            return Position;
        }

        public void setPosition(Object Position) {
            this.Position = Position;
        }

        public String getADLink() {
            return ADLink;
        }

        public void setADLink(String ADLink) {
            this.ADLink = ADLink;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public String getTemperatureMac() {
            return TemperatureMac;
        }

        public void setTemperatureMac(String TemperatureMac) {
            this.TemperatureMac = TemperatureMac;
        }

        public String getWeightMac() {
            return WeightMac;
        }

        public void setWeightMac(String WeightMac) {
            this.WeightMac = WeightMac;
        }
    }
}
