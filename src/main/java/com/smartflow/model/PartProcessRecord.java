package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="trace.PartProcessRecord")
@Entity
public class PartProcessRecord implements Serializable {
	
	private Integer Id;
	private Integer ProcessStepId;
	private Integer PartSerialNumberId;
	private Integer ProcessState;
	private Integer StationId;
	private Integer Layer;
	private Date ProductionDateTime;
	private Date BookDateTime;
	private Float CycleTime;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public Integer getProcessStepId() {
		return ProcessStepId;
	}
	public Integer getPartSerialNumberId() {
		return PartSerialNumberId;
	}
	public Integer getProcessState() {
		return ProcessState;
	}
	public Integer getStationId() {
		return StationId;
	}
	public Integer getLayer() {
		return Layer;
	}
	public Date getProductionDateTime() {
		return ProductionDateTime;
	}
	public Date getBookDateTime() {
		return BookDateTime;
	}
	public Float getCycleTime() {
		return CycleTime;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public void setProcessStepId(Integer processStepId) {
		ProcessStepId = processStepId;
	}
	public void setPartSerialNumberId(Integer partSerialNumberId) {
		PartSerialNumberId = partSerialNumberId;
	}
	public void setProcessState(Integer processState) {
		ProcessState = processState;
	}
	public void setStationId(Integer stationId) {
		StationId = stationId;
	}
	public void setLayer(Integer layer) {
		Layer = layer;
	}
	public void setProductionDateTime(Date productionDateTime) {
		ProductionDateTime = productionDateTime;
	}
	public void setBookDateTime(Date bookDateTime) {
		BookDateTime = bookDateTime;
	}
	public void setCycleTime(Float cycleTime) {
		CycleTime = cycleTime;
	}
}
