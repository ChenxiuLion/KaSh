package chenxiu.sh.com.ka.modle;

/**
 * Created by Chenxiu on 2016/7/11.
 */
public class Food {

    /**
     * RecipesName : sample string 1
     * PeriodsType : 2
     * GardenerLeaderID : 3
     * GardenerLeaderUserName : sample string 4
     * KindergartenID : 5
     * StartTime : 2016-07-11T22:57:34.0095078+08:00
     * EndTime : 2016-07-11T22:57:34.0095078+08:00
     * PageIndex : 1
     * PageSize : 1
     * ID : 1
     * OrderBy : sample string 8
     * Ascending : true
     */

    private String RecipesName;
    private int PeriodsType;
    private int GardenerLeaderID;
    private String GardenerLeaderUserName;
    private int KindergartenID;
    private String StartTime;
    private String EndTime;
    private int PageIndex;
    private int PageSize;
    private int ID;
    private String OrderBy;
    private boolean Ascending;

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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String OrderBy) {
        this.OrderBy = OrderBy;
    }

    public boolean isAscending() {
        return Ascending;
    }

    public void setAscending(boolean Ascending) {
        this.Ascending = Ascending;
    }
}
