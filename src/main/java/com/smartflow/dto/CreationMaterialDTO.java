package com.smartflow.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreationMaterialDTO {
	private Integer Id;
	private String MaterialNumber;
//	private Integer Version;
	private String Description;
//	private String Specification;
	private String CustomerMaterialNumber;
	private String SupplierMaterialNumber;
	private Integer MaterialGroupType;
//	private String IsProduct;
//	private Boolean IsProduct;
//	private String IsMultiPanel;
//	private boolean IsMultiPanel;
//	private boolean RequireBackflush;
//	private String RequireBackflush;
//	private Integer NumberOfPanels;
	private Integer Unit;
//	private String SetupFlag;
	private boolean SetupFlag;
//	private Integer ProcurementType;
	private BigDecimal MinimumPackageQuantity;
	private BigDecimal ExpirationTime;
	private BigDecimal SafetyStock;
//	private Integer DefaultStorageLocationId;
//	private String DefaultTargetFeedingLocation;//新增一列 默认回冲库位
//	private Integer ContainerSize;
//	private Integer MSL;
	private Integer State;
//	private Integer DefaultStationGroupId;
//	private Integer CompanyId;
//	private Integer FactoryId;
//	private String DefaultStationGroupId;
//	private String CompanyId;
	private String FactoryId;
	private Date CreationDateTime;
	private Integer CreatorId;
//	private Date ValidBegin;
//	private Date ValidEnd;
	private String ValidBegin;
	private String ValidEnd;
	private boolean RequireFIFO;//是否需要先进先出
	private boolean RequireCheckCustomerLabel;//是否需要扫描客户标签
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
//	@NotNull(message="{material.Version.required}")
//	@JsonProperty("Version")
//	public Integer getVersion() {
//		return Version;
//	}
//	public void setVersion(Integer version) {
//		Version = version;
//	}
	@Size(max=200,message="{material.Description.required}")
	@NotNull(message="{material.Description.invalid}")
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
//	@Size(max=200,message="{material.Specification.required}")
//	@JsonProperty("Specification")
//	public String getSpecification() {
//		return Specification;
//	}
//	public void setSpecification(String specification) {
//		Specification = specification;
//	}
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
//	@NotNull(message="{material.IsProduct.required}")
//	@JsonProperty("IsProduct")
//	public Boolean getIsProduct() {
//		return IsProduct;
//	}
//	public void setIsProduct(Boolean isProduct) {
//		IsProduct = isProduct;
//	}
//	@NotBlank(message="{material.IsMultiPanel.required}")
//	@JsonProperty("IsMultiPanel")
//	public String getIsMultiPanel() {
//		return IsMultiPanel;
//	}
//	public void setIsMultiPanel(String isMultiPanel) {
//		IsMultiPanel = isMultiPanel;
//	}
//	@JsonProperty("RequireBackflush")
//	public boolean getRequireBackflush() {
//		return RequireBackflush;
//	}
//	public void setRequireBackflush(boolean requireBackflush) {
//		RequireBackflush = requireBackflush;
//	}
//	@NotNull(message="{material.NumberOfPanels.required}")
//	@JsonProperty("NumberOfPanels")
//	public Integer getNumberOfPanels() {
//		return NumberOfPanels;
//	}
//	public void setNumberOfPanels(Integer numberOfPanels) {
//		NumberOfPanels = numberOfPanels;
//	}
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
//	@JsonProperty("ProcurementType")
//	public Integer getProcurementType() {
//		return ProcurementType;
//	}
//	public void setProcurementType(Integer procurementType) {
//		ProcurementType = procurementType;
//	}
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
	@JsonProperty("SafetyStock")
	public BigDecimal getSafetyStock() {
		return SafetyStock;
	}
	public void setSafetyStock(BigDecimal safetyStock) {
		SafetyStock = safetyStock;
	}
//	@JsonProperty("DefaultStorageLocationId")
//	public Integer getDefaultStorageLocationId() {
//		return DefaultStorageLocationId;
//	}
//	public void setDefaultStorageLocationId(Integer defaultStorageLocationId) {
//		DefaultStorageLocationId = defaultStorageLocationId;
//	}
//	@JsonProperty("DefaultTargetFeedingLocation")
//	public String getDefaultTargetFeedingLocation() {
//		return DefaultTargetFeedingLocation;
//	}
//	public void setDefaultTargetFeedingLocation(String defaultTargetFeedingLocation) {
//		DefaultTargetFeedingLocation = defaultTargetFeedingLocation;
//	}
//	@JsonProperty("ContainerSize")
//	public Integer getContainerSize() {
//		return ContainerSize;
//	}
//	public void setContainerSize(Integer containerSize) {
//		ContainerSize = containerSize;
//	}
//	@JsonProperty("MSL")
//	public Integer getMSL() {
//		return MSL;
//	}
//	public void setMSL(Integer mSL) {
//		MSL = mSL;
//	}
	@NotNull(message="{material.State.required}")
	@JsonProperty("State")
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
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
	@NotBlank(message="{material.FactoryId.required}")
	@JsonProperty("FactoryId")
	public String getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(String factoryId) {
		FactoryId = factoryId;
	}
	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	@JsonProperty("CreatorId")
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
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
