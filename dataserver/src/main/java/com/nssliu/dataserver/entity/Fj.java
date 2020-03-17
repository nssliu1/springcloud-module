package com.nssliu.dataserver.entity;

import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/12 12:06
 * @describe:
 */
public class Fj {
    @TableFieldDetails(esName = "area_name",esType = "text")
    private String area_name;
    @TableFieldDetails(esName = "area_code",esType = "text")
    private String area_code;
    @TableFieldDetails(esName = "date_date",esType = "date",param = "format",paramDetail = "yyyy.MM")
    private String date_date;
    @TableFieldDetails(esName = "date_str",esType = "text")
    private String date_str;
    @TableFieldDetails(esName = "price",esType = "double")
    private Double price;

    public Fj(String area_name, String area_code, String date_date, String date_str, Double price) {
        this.area_name = area_name;
        this.area_code = area_code;
        this.date_date = date_date;
        this.date_str = date_str;
        this.price = price;
    }

    public Fj() {
    }

    @Override
    public String toString() {
        return "Fj{" +
                "area_name='" + area_name + '\'' +
                ", area_code='" + area_code + '\'' +
                ", date_date='" + date_date + '\'' +
                ", date_str='" + date_str + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fj fj = (Fj) o;

        if (area_name != null ? !area_name.equals(fj.area_name) : fj.area_name != null) return false;
        if (area_code != null ? !area_code.equals(fj.area_code) : fj.area_code != null) return false;
        if (date_date != null ? !date_date.equals(fj.date_date) : fj.date_date != null) return false;
        if (date_str != null ? !date_str.equals(fj.date_str) : fj.date_str != null) return false;
        return price != null ? price.equals(fj.price) : fj.price == null;
    }

    @Override
    public int hashCode() {
        int result = area_name != null ? area_name.hashCode() : 0;
        result = 31 * result + (area_code != null ? area_code.hashCode() : 0);
        result = 31 * result + (date_date != null ? date_date.hashCode() : 0);
        result = 31 * result + (date_str != null ? date_str.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getDate_date() {
        return date_date;
    }

    public void setDate_date(String date_date) {
        this.date_date = date_date;
    }

    public String getDate_str() {
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
