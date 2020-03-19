package com.nssliu.dataserver.entity;

import com.nssliu.dataserver.trueversion.annotations.TableFieldDetails;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/3/18 18:25
 * @describe:
 */
public class Bus {
    @TableFieldDetails(esName = "description",esType = "text")
    private String description;
    @TableFieldDetails(esName = "evaluation",esType = "text")
    private String evaluation;
    //@TableFieldDetails(esName = "roads",esType = "text")
    private String roads;
    @TableFieldDetails(esName = "dateTime",esType = "date",param = "format",paramDetail = "yyyy-MM-dd")
    private String dateTime;
    @TableFieldDetails(esName = "roadName",esType = "text")
    private String roadName;
    @TableFieldDetails(esName = "status",esType = "text")
    private String status;

    public Bus() {
    }

    public Bus(String description, String evaluation, String roads, String dateTime) {
        this.description = description;
        this.evaluation = evaluation;
        this.roads = roads;
        this.dateTime = dateTime;
    }

    public Bus(String description, String evaluation, String roads, String dateTime, String roadName, String status) {
        this.description = description;
        this.evaluation = evaluation;
        this.roads = roads;
        this.dateTime = dateTime;
        this.roadName = roadName;
        this.status = status;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Bus(String description, String evaluation, String roads, String dateTime, String roadName) {
        this.description = description;
        this.evaluation = evaluation;
        this.roads = roads;
        this.dateTime = dateTime;
        this.roadName = roadName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getRoads() {
        return roads;
    }

    public void setRoads(String roads) {
        this.roads = roads;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
