package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author haita
 */
public class EditMaterialDTO {
	private Integer Id;
	private String MaterialNumber;
	private String Description;
	private String CustomerMaterialNumber;
	private String SupplierMaterialNumber;
	private Integer MaterialGroupType;
	private Integer Unit;
	private boolean SetupFlag;
	private BigDecimal MinimumPackageQuantity;
	private BigDecimal ExpirationTime;
	private Integer State;
	private String FactoryId;
	private Integer EditorId;
	private String ValidBegin;
	private String ValidEnd;
	private boolean RequireFIFO;//是否需要先进先出
	private boolean RequireCheckCustomerLabel;//是否需要扫描客户标签
	private int WashQuantity;
	private int MaxWashQuantity;
	private int ContainerTypeId;
	private int TraceStationId;
	private Integer StationId;


	@JsonProperty("StationId")
	public Integer getStationId() {
		return StationId;
	}

	public void setStationId(Integer stationId) {
		StationId = stationId;
	}
	@JsonProperty("TraceStationId")
	public int getTraceStationId() {
		return TraceStationId;
	}

	public void setTraceStationId(int traceStationId) {
		TraceStationId = traceStationId;
	}

	@JsonProperty("ContainerTypeId")
	public int getContainerTypeId() {
		return ContainerTypeId;
	}

	public void setContainerTypeId(int containerTypeId) {
		ContainerTypeId = containerTypeId;
	}

	@JsonProperty("MaxDeliveryQuantity")
	public int getWashQuantity() {
		return WashQuantity;
	}

	public void setWashQuantity(int washQuantity) {
		WashQuantity = washQuantity;
	}
	@JsonProperty("MaxWashQuantity")
	public int getMaxWashQuantity() {
		return MaxWashQuantity;
	}

	public void setMaxWashQuantity(int maxWashQuantity) {
		MaxWashQuantity = maxWashQuantity;
	}


	@NotNull(message="{material.Id.required}")
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@NotNull(message="{material.MaterialNumber.invalid}")
	@Size(max=46,message="{material.MaterialNumber.required}")
	@JsonProperty("MaterialNumber")
	public String getMaterialNumber() {
		return MaterialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}
	@Size(max=200,message="{material.Description.required}")
	@NotNull(message="{material.Description.invalid}")
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Size(max=50,message="{material.CustomerMaterialNumber.required}")
	@JsonProperty("CustomerMaterialNumber")
	public String getCustomerMaterialNumber() {
		return CustomerMaterialNumber;
	}
	public void setCustomerMaterialNumber(String customerMaterialNumber) {
		CustomerMaterialNumber = customerMaterialNumber;
	}
	@Size(max=50,message="{material.SupplierMaterialNumber.required}")
	@JsonProperty("SupplierMaterialNumber")
	public String getSupplierMaterialNumber() {
		return SupplierMaterialNumber;
	}
	public void setSupplierMaterialNumber(String supplierMaterialNumber) {
		SupplierMaterialNumber = supplierMaterialNumber;
	}
	@JsonProperty("MaterialGroupType")
	public Integer getMaterialGroupType() {
		return MaterialGroupType;
	}
	public void setMaterialGroupType(Integer materialGroupType) {
		MaterialGroupType = materialGroupType;
	}
	@NotNull(message="{material.Unit.required}")
	@JsonProperty("Unit")
	public Integer getUnit() {
		return Unit;
	}
	public void setUnit(Integer unit) {
		Unit = unit;
	}
	@JsonProperty("SetupFlag")
	public boolean getSetupFlag() {
		return SetupFlag;
	}
	public void setSetupFlag(boolean setupFlag) {
		SetupFlag = setupFlag;
	}
	@Digits(integer = 10, fraction = 0, message="{material.MinimumPackageQuantity.required}")
	@JsonProperty("MinimumPackageQuantity")
	public BigDecimal getMinimumPackageQuantity() {
		return MinimumPackageQuantity;
	}
	public void setMinimumPackageQuantity(BigDecimal minimumPackageQuantity) {
		MinimumPackageQuantity = minimumPackageQuantity;
	}
	@Digits(integer = 19, fraction = 0, message="{material.ExpirationTime.required}")
	@JsonProperty("ExpirationTime")
	public BigDecimal getExpirationTime() {
		return ExpirationTime;
	}
	public void setExpirationTime(BigDecimal expirationTime) {
		ExpirationTime = expirationTime;
	}
	@Digits(integer = 15, fraction = 0, message="{material.SafetyStock.required}")
	@NotNull(message="{material.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	@NotBlank(message="{material.FactoryId.required}")
	@JsonProperty("FactoryId")
	public String getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(String factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("EditorId")
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	@JsonProperty("ValidBegin")
	public String getValidBegin() {
		return ValidBegin;
	}
	public void setValidBegin(String validBegin) {
		ValidBegin = validBegin;
	}
	@JsonProperty("ValidEnd")
	public String getValidEnd() {
		return ValidEnd;
	}
	public void setValidEnd(String validEnd) {
		ValidEnd = validEnd;
	}
	@JsonProperty("RequireFIFO")
	public boolean getRequireFIFO() {
		return RequireFIFO;
	}
	public void setRequireFIFO(boolean requireFIFO) {
		RequireFIFO = requireFIFO;
	}
	@JsonProperty("RequireCheckCustomerLabel")
	public boolean getRequireCheckCustomerLabel() {
		return RequireCheckCustomerLabel;
	}
	public void setRequireCheckCustomerLabel(boolean requireCheckCustomerLabel) {
		RequireCheckCustomerLabel = requireCheckCustomerLabel;
	}
	
	
}
