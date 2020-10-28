package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="trace.PartFailureDataRecord")
public class PartFailureDataRecord implements Serializable {
	private Integer Id;
	private Integer PartSerialNumberId;
	private String SequenceNumber;
	private Integer FailureTypeId;
	private Integer FailureCauseId;
	private Integer Layer;
	private String Designator;
	private Integer IsFalseReject;
	private Integer IsCriticalFailure;
	private Date ProductionDateTime;
	private Date BookDateTime;
	private Integer StationId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getPartSerialNumberId() {
		return PartSerialNumberId;
	}
	public void setPartSerialNumberId(Integer partSerialNumberId) {
		PartSerialNumberId = partSerialNumberId;
	}
	public String getSequenceNumber() {
		return SequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		SequenceNumber = sequenceNumber;
	}
	public Integer getFailureTypeId() {
		return FailureTypeId;
	}
	public void setFailureTypeId(Integer failureTypeId) {
		FailureTypeId = failureTypeId;
	}
	public Integer getFailureCauseId() {
		return FailureCauseId;
	}
	public void setFailureCauseId(Integer failureCauseId) {
		FailureCauseId = failureCauseId;
	}
	public Integer getLayer() {
		return Layer;
	}
	public void setLayer(Integer layer) {
		Layer = layer;
	}
	public String getDesignator() {
		return Designator;
	}
	public void setDesignator(String designator) {
		Designator = designator;
	}
	public Integer getIsFalseReject() {
		return IsFalseReject;
	}
	public void setIsFalseReject(Integer isFalseReject) {
		IsFalseReject = isFalseReject;
	}
	public Integer getIsCriticalFailure() {
		return IsCriticalFailure;
	}
	public void setIsCriticalFailure(Integer isCriticalFailure) {
		IsCriticalFailure = isCriticalFailure;
	}
	public Date getProductionDateTime() {
		return ProductionDateTime;
	}
	public void setProductionDateTime(Date productionDateTime) {
		ProductionDateTime = productionDateTime;
	}
	public Date getBookDateTime() {
		return BookDateTime;
	}
	public void setBookDateTime(Date bookDateTime) {
		BookDateTime = bookDateTime;
	}
	public Integer getStationId() {
		return StationId;
	}
	public void setStationId(Integer stationId) {
		StationId = stationId;
	}
	
}
