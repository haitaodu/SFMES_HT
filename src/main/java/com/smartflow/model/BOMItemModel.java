package com.smartflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author haita
 */
@Entity
@Table(name="core.BOMItem")
public class BOMItemModel implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int Id;
	@Column(name="BOMHeadId")
	private int BOMHeadId;
	@Column(name="MaterialId")
	private int MaterialId;
	@Column(name="Designator")
	private String Designator;
	@Column(name="Quantity")
	private float Quantity;
	@Column(name="StationGroupId")
	private int StationGroupId;
	@Column(name="IsNeedSetupCheck")
	private boolean IsNeedSetupCheck;
	@Column(name="Layer")
	private int Layer;
	@Column(name="Unit")
	private int Unit;
    @Column(name="IsAlternative")
    private boolean IsAlternative;
    @Column(name="Version")
    private int Version;
    public BOMItemModel() {};
	public BOMItemModel(int id, int bOMHeadId, int materialId, String designator, float quantity, int stationGroupId,
			boolean isNeedSetupCheck, int layer, int unit, boolean isAlternative, int version) {
		
		Id = id;
		BOMHeadId = bOMHeadId;
		MaterialId = materialId;
		Designator = designator;
		Quantity = quantity;
		StationGroupId = stationGroupId;
		IsNeedSetupCheck = isNeedSetupCheck;
		Layer = layer;
		Unit = unit;
		IsAlternative = isAlternative;
		Version = version;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getBOMHeadId() {
		return BOMHeadId;
	}
	public void setBOMHeadId(int bOMHeadId) {
		BOMHeadId = bOMHeadId;
	}
	public int getMaterialId() {
		return MaterialId;
	}
	public void setMaterialId(int materialId) {
		MaterialId = materialId;
	}
	public String getDesignator() {
		return Designator;
	}
	public void setDesignator(String designator) {
		Designator = designator;
	}
	public float getQuantity() {
		return Quantity;
	}
	public void setQuantity(float quantity) {
		Quantity = quantity;
	}
	public int getStationGroupId() {
		return StationGroupId;
	}
	public void setStationGroupId(int stationGroupId) {
		StationGroupId = stationGroupId;
	}
	public boolean isIsNeedSetupCheck() {
		return IsNeedSetupCheck;
	}
	public void setIsNeedSetupCheck(boolean isNeedSetupCheck) {
		IsNeedSetupCheck = isNeedSetupCheck;
	}
	public int getLayer() {
		return Layer;
	}
	public void setLayer(int layer) {
		Layer = layer;
	}
	public int getUnit() {
		return Unit;
	}
	public void setUnit(int unit) {
		Unit = unit;
	}
	public boolean isIsAlternative() {
		return IsAlternative;
	}
	public void setIsAlternative(boolean isAlternative) {
		IsAlternative = isAlternative;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}
		
	}
