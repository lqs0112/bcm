package com.rtmap.traffic.bcm.domain;

import java.util.Date;

public class RptDriverCond {
	private Date beginStatsDay;

	private Date endStatsDay;

	private String buildingNo;

	private String driverNo;

	private String vehicleNo;

	public Date getBeginStatsDay() {
		return beginStatsDay;
	}

	public void setBeginStatsDay(Date beginStatsDay) {
		this.beginStatsDay = beginStatsDay;
	}

	public Date getEndStatsDay() {
		return endStatsDay;
	}

	public void setEndStatsDay(Date endStatsDay) {
		this.endStatsDay = endStatsDay;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo == null ? null : buildingNo.trim();
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo == null ? null : driverNo.trim();
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
	}
}
