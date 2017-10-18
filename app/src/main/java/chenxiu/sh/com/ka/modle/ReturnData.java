package chenxiu.sh.com.ka.modle;

import java.util.List;

/**
 * Created by Chenxiu on 2016/6/12.
 */
public class ReturnData<T> {
    /**
     * ResultData : null
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private List<T> ResultData;
    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

    public List<T> getResultData() {
        return ResultData;
    }

    public void setResultData(List<T> resultData) {
        ResultData = resultData;
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
}
