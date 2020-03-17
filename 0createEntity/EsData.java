public class EsData {
    @TableFieldDetails(esName = "Name",esType = "text")
    private String name;

    private String details;
    @TableFieldDetails(esName = "dates",esType = "date",param = "format",paramDetail = "yyyy.MM")
    private String dates;
    @Group(groupName = "location",groupType = "geo_point")
    @TableFieldDetails(esName = "lat",esType = "double")
    private double smx;
    @Group(groupName = "location",groupType = "geo_point")
    @TableFieldDetails(esName = "lon",esType = "double")
    private double smy;
    @Group(groupName = "location1",groupType = "geo_point")
    @TableFieldDetails(esName = "lat",esType = "double")
    private double smx1;
    @Group(groupName = "location1",groupType = "geo_point")
    @TableFieldDetails(esName = "lon",esType = "double")
    private double smy1;
    @TableFieldDetails(esName = "smyyy",esType = "double")
    private double smyyy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSmyyy() {
        return smyyy;
    }

    public void setSmyyy(double smyyy) {
        this.smyyy = smyyy;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public double getSmx() {
        return smx;
    }

    public void setSmx(double smx) {
        this.smx = smx;
    }

    public double getSmy() {
        return smy;
    }

    public void setSmy(double smy) {
        this.smy = smy;
    }

    public double getSmx1() {
        return smx1;
    }

    public void setSmx1(double smx1) {
        this.smx1 = smx1;
    }

    public double getSmy1() {
        return smy1;
    }

    public void setSmy1(double smy1) {
        this.smy1 = smy1;
    }
}
