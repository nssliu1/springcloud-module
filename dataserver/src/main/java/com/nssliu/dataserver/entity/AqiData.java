package com.nssliu.dataserver.entity;

import com.nssliu.dataserver.trueversion.annotations.Group;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 11:06
 * @describe:
 */
public class AqiData {
    @TableFieldDetails(esName = "lat",esType = "double")
    @Group(groupName = "location",groupType = "geo_point")
    private double smx;
    @TableFieldDetails(esName = "lon",esType = "double")
    @Group(groupName = "location",groupType = "geo_point")
    private double smy;
    @TableFieldDetails(esName = "lat",esType = "double")
    @Group(groupName = "location1",groupType = "geo_point")
    private double smx1;
    @Group(groupName = "location1",groupType = "geo_point")
    @TableFieldDetails(esName = "lon",esType = "double")
    private double smy1;
    //@TableFieldDetails(esName = "updateTime",esType = "date",param = "format",paramDetail = "yyyy-MM-dd hh:mm:ss")
    @TableFieldDetails(esName = "updateTime",esType = "date",param = "format",paramDetail = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
    @TableFieldDetails(esName = "position_name",esType = "text")
    private String position_name;
    @TableFieldDetails(esName = "aqi",esType = "double")
    private double aqi;
    @TableFieldDetails(esName = "level",esType = "text")
    private String level;
    @TableFieldDetails(esName = "primary_pollutant",esType = "text")
    private String primary_pollutant;
    @TableFieldDetails(esName = "pm25",esType = "double")
    private double pm25;
    @TableFieldDetails(esName = "pm10",esType = "double")
    private double pm10;
    @TableFieldDetails(esName = "co",esType = "double")
    private double co;
    @TableFieldDetails(esName = "no2",esType = "date")
    private double no2;
    @TableFieldDetails(esName = "o31h",esType = "double")
    private double o31h;
    @TableFieldDetails(esName = "o38h",esType = "double")
    private double o38h;
    @TableFieldDetails(esName = "so2",esType = "double")
    private double so2;

    public AqiData() {
    }

    public AqiData(double smx, double smy, double smx1, double smy1, String updateTime, String position_name, double aqi, String level, String primary_pollutant, double pm25, double pm10, double co, double no2, double o31h, double o38h, double so2) {
        this.smx = smx;
        this.smy = smy;
        this.smx1 = smx1;
        this.smy1 = smy1;
        this.updateTime = updateTime;
        this.position_name = position_name;
        this.aqi = aqi;
        this.level = level;
        this.primary_pollutant = primary_pollutant;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.co = co;
        this.no2 = no2;
        this.o31h = o31h;
        this.o38h = o38h;
        this.so2 = so2;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public double getAqi() {
        return aqi;
    }

    public void setAqi(double aqi) {
        this.aqi = aqi;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrimary_pollutant() {
        return primary_pollutant;
    }

    public void setPrimary_pollutant(String primary_pollutant) {
        this.primary_pollutant = primary_pollutant;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getO31h() {
        return o31h;
    }

    public void setO31h(double o31h) {
        this.o31h = o31h;
    }

    public double getO38h() {
        return o38h;
    }

    public void setO38h(double o38h) {
        this.o38h = o38h;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }
}
