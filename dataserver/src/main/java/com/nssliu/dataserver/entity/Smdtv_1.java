package com.nssliu.dataserver.entity;

import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;

public class Smdtv_1{

	static{
		System.out.println("nssliu");
	}

	private Integer smid;
	private Integer markersymbolid;
	private String condid;
	private String crid;
	private String passage;
	private String sgnl_loction;
	private Integer linecolor;
	private String condtype;
	private Integer markerangle;
	private Integer fillbackcolor;
	private String outlinkid;
	private String slope;
	private Double linewidth;
	private Integer markersize;
	private Integer fillforecolor;
	private Integer fillsymbolid;
	private String inlinkid;
	private Integer linesymbolid;
	private Integer smuserid;
	private String mapid;
	private String id;
	private Double smy;
	private Long smlibtileid;
	private Double smx;

	public Integer getSmid(){
		return this.smid;
	}
	public void setSmid(Integer smid){
		this.smid=smid;
	}
	public Integer getMarkersymbolid(){
		return this.markersymbolid;
	}
	public void setMarkersymbolid(Integer markersymbolid){
		this.markersymbolid=markersymbolid;
	}
	public String getCondid(){
		return this.condid;
	}
	public void setCondid(String condid){
		this.condid=condid;
	}
	public String getCrid(){
		return this.crid;
	}
	public void setCrid(String crid){
		this.crid=crid;
	}
	public String getPassage(){
		return this.passage;
	}
	public void setPassage(String passage){
		this.passage=passage;
	}
	public String getSgnl_loction(){
		return this.sgnl_loction;
	}
	public void setSgnl_loction(String sgnl_loction){
		this.sgnl_loction=sgnl_loction;
	}
	public Integer getLinecolor(){
		return this.linecolor;
	}
	public void setLinecolor(Integer linecolor){
		this.linecolor=linecolor;
	}
	public String getCondtype(){
		return this.condtype;
	}
	public void setCondtype(String condtype){
		this.condtype=condtype;
	}
	public Integer getMarkerangle(){
		return this.markerangle;
	}
	public void setMarkerangle(Integer markerangle){
		this.markerangle=markerangle;
	}
	public Integer getFillbackcolor(){
		return this.fillbackcolor;
	}
	public void setFillbackcolor(Integer fillbackcolor){
		this.fillbackcolor=fillbackcolor;
	}
	public String getOutlinkid(){
		return this.outlinkid;
	}
	public void setOutlinkid(String outlinkid){
		this.outlinkid=outlinkid;
	}
	public String getSlope(){
		return this.slope;
	}
	public void setSlope(String slope){
		this.slope=slope;
	}
	public Double getLinewidth(){
		return this.linewidth;
	}
	public void setLinewidth(Double linewidth){
		this.linewidth=linewidth;
	}
	public Integer getMarkersize(){
		return this.markersize;
	}
	public void setMarkersize(Integer markersize){
		this.markersize=markersize;
	}
	public Integer getFillforecolor(){
		return this.fillforecolor;
	}
	public void setFillforecolor(Integer fillforecolor){
		this.fillforecolor=fillforecolor;
	}
	public Integer getFillsymbolid(){
		return this.fillsymbolid;
	}
	public void setFillsymbolid(Integer fillsymbolid){
		this.fillsymbolid=fillsymbolid;
	}
	public String getInlinkid(){
		return this.inlinkid;
	}
	public void setInlinkid(String inlinkid){
		this.inlinkid=inlinkid;
	}
	public Integer getLinesymbolid(){
		return this.linesymbolid;
	}
	public void setLinesymbolid(Integer linesymbolid){
		this.linesymbolid=linesymbolid;
	}
	public Integer getSmuserid(){
		return this.smuserid;
	}
	public void setSmuserid(Integer smuserid){
		this.smuserid=smuserid;
	}
	public String getMapid(){
		return this.mapid;
	}
	public void setMapid(String mapid){
		this.mapid=mapid;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public Double getSmy(){
		return this.smy;
	}
	public void setSmy(Double smy){
		this.smy=smy;
	}
	public Long getSmlibtileid(){
		return this.smlibtileid;
	}
	public void setSmlibtileid(Long smlibtileid){
		this.smlibtileid=smlibtileid;
	}
	public Double getSmx(){
		return this.smx;
	}
	public void setSmx(Double smx){
		this.smx=smx;
	}

	@Override
	public String toString() {
		return "Smdtv_1{" +
				"smid=" + smid +
				", markersymbolid=" + markersymbolid +
				", condid='" + condid + '\'' +
				", crid='" + crid + '\'' +
				", passage='" + passage + '\'' +
				", sgnl_loction='" + sgnl_loction + '\'' +
				", linecolor=" + linecolor +
				", condtype='" + condtype + '\'' +
				", markerangle=" + markerangle +
				", fillbackcolor=" + fillbackcolor +
				", outlinkid='" + outlinkid + '\'' +
				", slope='" + slope + '\'' +
				", linewidth=" + linewidth +
				", markersize=" + markersize +
				", fillforecolor=" + fillforecolor +
				", fillsymbolid=" + fillsymbolid +
				", inlinkid='" + inlinkid + '\'' +
				", linesymbolid=" + linesymbolid +
				", smuserid=" + smuserid +
				", mapid='" + mapid + '\'' +
				", id='" + id + '\'' +
				", smy=" + smy +
				", smlibtileid=" + smlibtileid +
				", smx=" + smx +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Smdtv_1 smdtv_1 = (Smdtv_1) o;

		if (smid != null ? !smid.equals(smdtv_1.smid) : smdtv_1.smid != null) return false;
		if (markersymbolid != null ? !markersymbolid.equals(smdtv_1.markersymbolid) : smdtv_1.markersymbolid != null)
			return false;
		if (condid != null ? !condid.equals(smdtv_1.condid) : smdtv_1.condid != null) return false;
		if (crid != null ? !crid.equals(smdtv_1.crid) : smdtv_1.crid != null) return false;
		if (passage != null ? !passage.equals(smdtv_1.passage) : smdtv_1.passage != null) return false;
		if (sgnl_loction != null ? !sgnl_loction.equals(smdtv_1.sgnl_loction) : smdtv_1.sgnl_loction != null)
			return false;
		if (linecolor != null ? !linecolor.equals(smdtv_1.linecolor) : smdtv_1.linecolor != null) return false;
		if (condtype != null ? !condtype.equals(smdtv_1.condtype) : smdtv_1.condtype != null) return false;
		if (markerangle != null ? !markerangle.equals(smdtv_1.markerangle) : smdtv_1.markerangle != null) return false;
		if (fillbackcolor != null ? !fillbackcolor.equals(smdtv_1.fillbackcolor) : smdtv_1.fillbackcolor != null)
			return false;
		if (outlinkid != null ? !outlinkid.equals(smdtv_1.outlinkid) : smdtv_1.outlinkid != null) return false;
		if (slope != null ? !slope.equals(smdtv_1.slope) : smdtv_1.slope != null) return false;
		if (linewidth != null ? !linewidth.equals(smdtv_1.linewidth) : smdtv_1.linewidth != null) return false;
		if (markersize != null ? !markersize.equals(smdtv_1.markersize) : smdtv_1.markersize != null) return false;
		if (fillforecolor != null ? !fillforecolor.equals(smdtv_1.fillforecolor) : smdtv_1.fillforecolor != null)
			return false;
		if (fillsymbolid != null ? !fillsymbolid.equals(smdtv_1.fillsymbolid) : smdtv_1.fillsymbolid != null)
			return false;
		if (inlinkid != null ? !inlinkid.equals(smdtv_1.inlinkid) : smdtv_1.inlinkid != null) return false;
		if (linesymbolid != null ? !linesymbolid.equals(smdtv_1.linesymbolid) : smdtv_1.linesymbolid != null)
			return false;
		if (smuserid != null ? !smuserid.equals(smdtv_1.smuserid) : smdtv_1.smuserid != null) return false;
		if (mapid != null ? !mapid.equals(smdtv_1.mapid) : smdtv_1.mapid != null) return false;
		if (id != null ? !id.equals(smdtv_1.id) : smdtv_1.id != null) return false;
		if (smy != null ? !smy.equals(smdtv_1.smy) : smdtv_1.smy != null) return false;
		if (smlibtileid != null ? !smlibtileid.equals(smdtv_1.smlibtileid) : smdtv_1.smlibtileid != null) return false;
		return smx != null ? smx.equals(smdtv_1.smx) : smdtv_1.smx == null;
	}

	@Override
	public int hashCode() {
		int result = smid != null ? smid.hashCode() : 0;
		result = 31 * result + (markersymbolid != null ? markersymbolid.hashCode() : 0);
		result = 31 * result + (condid != null ? condid.hashCode() : 0);
		result = 31 * result + (crid != null ? crid.hashCode() : 0);
		result = 31 * result + (passage != null ? passage.hashCode() : 0);
		result = 31 * result + (sgnl_loction != null ? sgnl_loction.hashCode() : 0);
		result = 31 * result + (linecolor != null ? linecolor.hashCode() : 0);
		result = 31 * result + (condtype != null ? condtype.hashCode() : 0);
		result = 31 * result + (markerangle != null ? markerangle.hashCode() : 0);
		result = 31 * result + (fillbackcolor != null ? fillbackcolor.hashCode() : 0);
		result = 31 * result + (outlinkid != null ? outlinkid.hashCode() : 0);
		result = 31 * result + (slope != null ? slope.hashCode() : 0);
		result = 31 * result + (linewidth != null ? linewidth.hashCode() : 0);
		result = 31 * result + (markersize != null ? markersize.hashCode() : 0);
		result = 31 * result + (fillforecolor != null ? fillforecolor.hashCode() : 0);
		result = 31 * result + (fillsymbolid != null ? fillsymbolid.hashCode() : 0);
		result = 31 * result + (inlinkid != null ? inlinkid.hashCode() : 0);
		result = 31 * result + (linesymbolid != null ? linesymbolid.hashCode() : 0);
		result = 31 * result + (smuserid != null ? smuserid.hashCode() : 0);
		result = 31 * result + (mapid != null ? mapid.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (smy != null ? smy.hashCode() : 0);
		result = 31 * result + (smlibtileid != null ? smlibtileid.hashCode() : 0);
		result = 31 * result + (smx != null ? smx.hashCode() : 0);
		return result;
	}
}