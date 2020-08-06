package com.smartflow.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trace.PartMaterialRecord")
public class PartMaterialRecord {

	private BigInteger Id;
	private BigInteger PartSerialNumberId;
	private BigInteger MaterialContainerId;
	private int MaterialSetupStationId;
	private Date MaterialSetupDateTime;
	private Date MaterialInstallDateTime;
	private int MaterialSetupOperatorId;
	private int Designator;
	private int Layer;
	private String FeederLocation;
	private Float ConsumedQuantity;
	private int ProcessStepId;
	@GeneratedValue
	@Id
	public BigInteger getId() {
		return Id;
	}
	public BigInteger getPartSerialNumberId() {
		return PartSerialNumberId;
	}
	public BigInteger getMaterialContainerId() {
		return MaterialContainerId;
	}
	public int getMaterialSetupStationId() {
		return MaterialSetupStationId;
	}
	public Date getMaterialSetupDateTime() {
		return MaterialSetupDateTime;
	}
	public Date getMaterialInstallDateTime() {
		return MaterialInstallDateTime;
	}
	public int getMaterialSetupOperatorId() {
		return MaterialSetupOperatorId;
	}
	public int getDesignator() {
		return Designator;
	}
	public int getLayer() {
		return Layer;
	}
	public String getFeederLocation() {
		return FeederLocation;
	}
	public Float getConsumedQuantity() {
		return ConsumedQuantity;
	}
	public int getProcessStepId() {
		return ProcessStepId;
	}
	public void setId(BigInteger id) {
		Id = id;
	}
	public void setPartSerialNumberId(BigInteger partSerialNumberId) {
		PartSerialNumberId = partSerialNumberId;
	}
	public void setMaterialContainerId(BigInteger materialContainerId) {
		MaterialContainerId = materialContainerId;
	}
	public void setMaterialSetupStationId(int materialSetupStationId) {
		MaterialSetupStationId = materialSetupStationId;
	}
	public void setMaterialSetupDateTime(Date materialSetupDateTime) {
		MaterialSetupDateTime = materialSetupDateTime;
	}
	public void setMaterialInstallDateTime(Date materialInstallDateTime) {
		MaterialInstallDateTime = materialInstallDateTime;
	}
	public void setMaterialSetupOperatorId(int materialSetupOperatorId) {
		MaterialSetupOperatorId = materialSetupOperatorId;
	}
	public void setDesignator(int designator) {
		Designator = designator;
	}
	public void setLayer(int layer) {
		Layer = layer;
	}
	public void setFeederLocation(String feederLocation) {
		FeederLocation = feederLocation;
	}
	public void setConsumedQuantity(Float consumedQuantity) {
		ConsumedQuantity = consumedQuantity;
	}
	public void setProcessStepId(int processStepId) {
		ProcessStepId = processStepId;
	}
}
