package com.nssliu.dataserver.entity;

import com.nssliu.dataserver.trueversion.annotations.Group;
import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/17 17:38
 * @describe: 热门景点模板
 */
public class ScenePoint {
    @TableFieldDetails(esName = "dateTime",esType = "date",param = "format",paramDetail = "yyyy-MM-dd")
    private String dateTime;
    @TableFieldDetails(esName = "name",esType = "text")
    private String name;

    @TableFieldDetails(esName = "lon",esType = "double")
    @Group(groupName = "location",groupType = "geo_point")
    private double lng;
    @TableFieldDetails(esName = "lat",esType = "double")
    @Group(groupName = "location",groupType = "geo_point")
    private double lat;

    @TableFieldDetails(esName = "address",esType = "text")
    private String address;
    @TableFieldDetails(esName = "province",esType = "text")
    private String province;
    @TableFieldDetails(esName = "city",esType = "text")
    private String city;
    @TableFieldDetails(esName = "area",esType = "text")
    private String area;
    @TableFieldDetails(esName = "telephone",esType = "text")
    private String telephone;
    @TableFieldDetails(esName = "detail",esType = "integer")
    private Integer detail;
    @TableFieldDetails(esName = "uid",esType = "text")
    private String uid;
    @TableFieldDetails(esName = "detail_info",esType = "text")
    private String detail_info;
    @TableFieldDetails(esName = "overall_rating",esType = "double")
    private double overall_rating;


    public ScenePoint() {
    }

    public ScenePoint(String dateTime, String name, double lng, double lat, String address, String province, String city, String area, String telephone, Integer detail, String uid, String detail_info, double overall_rating) {
        this.dateTime = dateTime;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.address = address;
        this.province = province;
        this.city = city;
        this.area = area;
        this.telephone = telephone;
        this.detail = detail;
        this.uid = uid;
        this.detail_info = detail_info;
        this.overall_rating = overall_rating;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDetail_info() {
        return detail_info;
    }

    public void setDetail_info(String detail_info) {
        this.detail_info = detail_info;
    }

    public double getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating(double overall_rating) {
        this.overall_rating = overall_rating;
    }
}
