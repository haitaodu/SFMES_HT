package com.smartflow.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="core.Container")
public class Container {
	private int Id;
	private String ContainerNumber;
	private Material Material;
	private float TotalQuantity;
	private int CurrentLocationId;
	private String BatchNumber;
	private int SupplierId;
	private Date ManufacturingDateTime;
	private Date ExpirationDate;
	private Date CreationDateTime;
	private Date PackingStartDateTime;
	private Date PackingCompleteDateTime;
	private int CreatorId;
	private Integer PackingUserId;
	private Double MSDLifeReminingTimeInMinute;
	private int state;
	@Id
	@GeneratedValue
	public int getId() {
		return Id;
	}
	public String getContainerNumber() {
		return ContainerNumber;
	}
	public float getTotalQuantity() {
		return TotalQuantity;
	}
	public int getCurrentLocationId() {
		return CurrentLocationId;
	}
	public String getBatchNumber() {
		return BatchNumber;
	}
	public int getSupplierId() {
		return SupplierId;
	}
	public Date getManufacturingDateTime() {
		return ManufacturingDateTime;
	}
	public Date getExpirationDate() {
		return ExpirationDate;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public Date getPackingStartDateTime() {
		return PackingStartDateTime;
	}
	public Date getPackingCompleteDateTime() {
		return PackingCompleteDateTime;
	}
	public int getCreatorId() {
		return CreatorId;
	}
	public Integer getPackingUserId() {
		return PackingUserId;
	}
	public Double getMSDLifeReminingTimeInMinute() {
		return MSDLifeReminingTimeInMinute;
	}
	public int getState() {
		return state;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setContainerNumber(String containerNumber) {
		ContainerNumber = containerNumber;
	}
	
	public void setTotalQuantity(float totalQuantity) {
		TotalQuantity = totalQuantity;
	}
	public void setCurrentLocationId(int currentLocationId) {
		CurrentLocationId = currentLocationId;
	}
	public void setBatchNumber(String batchNumber) {
		BatchNumber = batchNumber;
	}
	public void setSupplierId(int supplierId) {
		SupplierId = supplierId;
	}
	public void setManufacturingDateTime(Date manufacturingDateTime) {
		ManufacturingDateTime = manufacturingDateTime;
	}
	public void setExpirationDate(Date expirationDate) {
		ExpirationDate = expirationDate;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public void setPackingStartDateTime(Date packingStartDateTime) {
		PackingStartDateTime = packingStartDateTime;
	}
	public void setPackingCompleteDateTime(Date packingCompleteDateTime) {
		PackingCompleteDateTime = packingCompleteDateTime;
	}
	public void setCreatorId(int creatorId) {
		CreatorId = creatorId;
	}
	public void setPackingUserId(Integer packingUserId) {
		PackingUserId = packingUserId;
	}
	public void setMSDLifeReminingTimeInMinute(Double mSDLifeReminingTimeInMinute) {
		MSDLifeReminingTimeInMinute = mSDLifeReminingTimeInMinute;
	}
	public void setState(int state) {
		this.state = state;
	}
	@ManyToOne
	@JoinColumn(name="MaterialId")
	public Material getMaterial() {
		return Material;
	}
	public void setMaterial(Material material) {
		Material = material;
	}

}
