package chenxiu.sh.com.ka.modle;

import java.util.List;

/**
 * Created by chenxiu on 2017/10/8.
 */

public class QdModel
{
    /**
     * ResultData : []
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    private List<?> ResultData;

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

    public List<?> getResultData() {
        return ResultData;
    }

    public void setResultData(List<?> ResultData) {
        this.ResultData = ResultData;
    }
}
