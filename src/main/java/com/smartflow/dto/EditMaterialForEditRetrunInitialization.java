package com.smartflow.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditMaterialForEditRetrunInitialization {
	private Integer Id;
	private String MaterialNumber;
//	private Integer Version;
	private String Description;
//	private String Specification;
	private String CustomerMaterialNumber;
	private String SupplierMaterialNumber;
	private String MaterialGroupTypeId;//改为String类型
//	private boolean IsProduct;
//	private boolean IsMultiPanel;
//	private boolean RequireBackflush;
//	private Integer NumberOfPanels;
	private Integer UnitId;
	private boolean SetupFlag;
//	private String ProcurementTypeId;//改为String类型
	private BigDecimal MinimumPackageQuantity;
	private BigDecimal ExpirationTime;
	private BigDecimal SafetyStock;
//	private Integer DefaultStorageLocationId;
//	private String DefaultStorageLocationId;
//	private String DefaultTargetFeedingLocationId;
//	private Integer ContainerSize;
//	private String MSLId;//改为String类型
	private String State;
//	private String DefaultStationGroupId;//改为String类型
//	private String CompanyId;//改为String类型
	private String FactoryId;
	private int WashQuantity;
	private int MaxWashQuantity;
	private int StationId;
	private Date ValidBegin;
	private Date ValidEnd;
	private boolean RequireFIFO;//是否需要先进先出
	private boolean RequireCheckCustomerLabel;//是否需要扫描客户标签
	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@JsonProperty("MaterialNumber")
	public String getMaterialNumber() {
		return MaterialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}
//	@JsonProperty("Version")
//	public Integer getVersion() {
//		return Version;
//	}
//	public void setVersion(Integer version) {
//		Version = version;
//	}
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
//	@JsonProperty("Specification")
//	public String getSpecification() {
//		return Specification;
//	}
//	public void setSpecification(String specification) {
//		Specification = specification;
//	}
	@JsonProperty("CustomerMaterialNumber")
	public String getCustomerMaterialNumber() {
		return CustomerMaterialNumber;
	}
	public void setCustomerMaterialNumber(String customerMaterialNumber) {
		CustomerMaterialNumber = customerMaterialNumber;
	}
	@JsonProperty("SupplierMaterialNumber")
	public String getSupplierMaterialNumber() {
		return SupplierMaterialNumber;
	}
	public void setSupplierMaterialNumber(String supplierMaterialNumber) {
		SupplierMaterialNumber = supplierMaterialNumber;
	}
	@JsonProperty("MaterialGroupTypeId")
	public String getMaterialGroupTypeId() {
		return MaterialGroupTypeId;
	}
	public void setMaterialGroupTypeId(String materialGroupTypeId) {
		MaterialGroupTypeId = materialGroupTypeId;
	}

	@JsonProperty("WashQuantity")
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
	@JsonProperty("StationId")
	public int getStationId() {
		return StationId;
	}

	public void setStationId(int stationId) {
		StationId = stationId;
	}

	@JsonProperty("UnitId")
	public Integer getUnitId() {
		return UnitId;
	}
	public void setUnitId(Integer unitId) {
		UnitId = unitId;
	}
	@JsonProperty("SetupFlag")
	public boolean getSetupFlag() {
		return SetupFlag;
	}
	public void setSetupFlag(boolean setupFlag) {
		SetupFlag = setupFlag;
	}
//	@JsonProperty("ProcurementTypeId")
//	public String getProcurementTypeId() {
//		return ProcurementTypeId;
//	}
//	public void setProcurementTypeId(String procurementTypeId) {
//		ProcurementTypeId = procurementTypeId;
//	}
	@JsonProperty("MinimumPackageQuantity")
	public BigDecimal getMinimumPackageQuantity() {
		return MinimumPackageQuantity;
	}
	public void setMinimumPackageQuantity(BigDecimal minimumPackageQuantity) {
		MinimumPackageQuantity = minimumPackageQuantity;
	}
	@JsonProperty("ExpirationTime")
	public BigDecimal getExpirationTime() {
		return ExpirationTime;
	}
	public void setExpirationTime(BigDecimal expirationTime) {
		ExpirationTime = expirationTime;
	}
	@JsonProperty("SafetyStock")
	public BigDecimal getSafetyStock() {
		return SafetyStock;
	}
	public void setSafetyStock(BigDecimal safetyStock) {
		SafetyStock = safetyStock;
	}
//	@JsonProperty("DefaultStorageLocationId")
//	public String getDefaultStorageLocationId() {
//		return DefaultStorageLocationId;
//	}
//	public void setDefaultStorageLocationId(String defaultStorageLocationId) {
//		DefaultStorageLocationId = defaultStorageLocationId;
//	}
//	@JsonProperty("DefaultTargetFeedingLocationId")
//	public String getDefaultTargetFeedingLocationId() {
//		return DefaultTargetFeedingLocationId;
//	}
//	public void setDefaultTargetFeedingLocationId(String defaultTargetFeedingLocationId) {
//		DefaultTargetFeedingLocationId = defaultTargetFeedingLocationId;
//	}
//	@JsonProperty("ContainerSize")
//	public Integer getContainerSize() {
//		return ContainerSize;
//	}
//	public void setContainerSize(Integer containerSize) {
//		ContainerSize = containerSize;
//	}
//	@JsonProperty("MSLId")
//	public String getMSLId() {
//		return MSLId;
//	}
//	public void setMSLId(String mSLId) {
//		MSLId = mSLId;
//	}
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
//	@JsonProperty("DefaultStationGroupId")
//	public String getDefaultStationGroupId() {
//		return DefaultStationGroupId;
//	}
//	public void setDefaultStationGroupId(String defaultStationGroupId) {
//		DefaultStationGroupId = defaultStationGroupId;
//	}
//	@JsonProperty("CompanyId")
//	public String getCompanyId() {
//		return CompanyId;
//	}
//	public void setCompanyId(String companyId) {
//		CompanyId = companyId;
//	}
	@JsonProperty("FactoryId")
	public String getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(String factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("ValidBegin")
	public Date getValidBegin() {
		return ValidBegin;
	}
	public void setValidBegin(Date validBegin) {
		ValidBegin = validBegin;
	}
	@JsonProperty("ValidEnd")
	public Date getValidEnd() {
		return ValidEnd;
	}
	public void setValidEnd(Date validEnd) {
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
