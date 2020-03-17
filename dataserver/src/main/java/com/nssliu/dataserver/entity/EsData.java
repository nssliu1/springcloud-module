package com.nssliu.dataserver.entity;

import com.nssliu.dataserver.trueversion.annotations.Group;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/23 22:36
 * @describe: 不加@TableFieldDetails注解不会创建该属性，@Group是子级嵌套
 */
public class EsData {
    @TableFieldDetails(esName = "Name",esType = "text")
    private String name;

    private String details;
    @TableFieldDetails(esName = "dates",esType = "date",param = "format",paramDetail = "yyyy.MM")
    private String dates;//date在java中要处理为String,es中处理为date并添加format，这样就会自动
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
