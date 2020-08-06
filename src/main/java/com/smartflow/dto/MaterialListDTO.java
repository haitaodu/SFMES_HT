package com.smartflow.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;

public class MaterialListDTO {
	private Integer Id;
	private String MaterialNumber;
//	private Integer Version;
	private String Description;
//	private String Specification;
	private String CustomerMaterialNumber;
	private String SupplierMaterialNumber;
	private String MaterialGroupType;
//	private String IsProduct;
//	private String IsMultiPanel;
//	private String RequireBackflush;
//	private Integer NumberOfPanels;
	private String Unit;
	private String SetupFlag;
//	private String ProcurementType;
	private BigDecimal MinimumPackageQuantity;
	private BigDecimal ExpirationTime;
	private BigDecimal SafetyStock;
//	private String DefaultStorageLocation;
//	private String DefaultTargetFeedingLocation;//新增一列 默认回冲库位
//	private Integer ContainerSize;
//	private String MSL;
	private String StateValue;
//	private String DefaultStationGroup;
//	private String CompanyNumber;
	private String FactoryNumber;
	private Date ValidBegin;
	private Date ValidEnd;
	private String Creator;
	private Date CreationDateTime;
	private String Editor;
	private Date EditDateTime;
	private String RequireFIFO;//是否需要先进先出
	private String RequireCheckCustomerLabel;//是否需要扫描客户标签
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

//    @JsonProperty("Version")
//    public Integer getVersion() {
//        return Version;
//    }
//
//    public void setVersion(Integer version) {
//        Version = version;
//    }

	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

//    @JsonProperty("Specification")
//    public String getSpecification() {
//        return Specification;
//    }
//
//    public void setSpecification(String specification) {
//        Specification = specification;
//    }

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

	@JsonProperty("MaterialGroupType")
	public String getMaterialGroupType() {
		return MaterialGroupType;
	}

	public void setMaterialGroupType(String materialGroupType) {
		MaterialGroupType = materialGroupType;
	}

//	@JsonProperty("IsProduct")
//	public String getIsProduct() {
//		return IsProduct;
//	}
//
//	public void setIsProduct(String isProduct) {
//		IsProduct = isProduct;
//	}

//    @JsonProperty("IsMultiPanel")
//    public String getIsMultiPanel() {
//        return IsMultiPanel;
//    }
//
//    public void setIsMultiPanel(String isMultiPanel) {
//        IsMultiPanel = isMultiPanel;
//    }

//	@JsonProperty("RequireBackflush")
//	public String getRequireBackflush() {
//		return RequireBackflush;
//	}
//
//	public void setRequireBackflush(String requireBackflush) {
//		RequireBackflush = requireBackflush;
//	}

//    @JsonProperty("NumberOfPanels")
//    public Integer getNumberOfPanels() {
//        return NumberOfPanels;
//    }
//
//    public void setNumberOfPanels(Integer numberOfPanels) {
//        NumberOfPanels = numberOfPanels;
//    }

	@JsonProperty("Unit")
	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	@JsonProperty("SetupFlag")
	public String getSetupFlag() {
		return SetupFlag;
	}

	public void setSetupFlag(String setupFlag) {
		SetupFlag = setupFlag;
	}

//    @JsonProperty("ProcurementType")
//    public String getProcurementType() {
//        return ProcurementType;
//    }
//
//    public void setProcurementType(String procurementType) {
//        ProcurementType = procurementType;
//    }

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

//	@JsonProperty("DefaultStorageLocation")
//	public String getDefaultStorageLocation() {
//		return DefaultStorageLocation;
//	}
//
//	public void setDefaultStorageLocation(String defaultStorageLocation) {
//		DefaultStorageLocation = defaultStorageLocation;
//	}
//
//	@JsonProperty("DefaultTargetFeedingLocation")
//	public String getDefaultTargetFeedingLocation() {
//		return DefaultTargetFeedingLocation;
//	}
//
//	public void setDefaultTargetFeedingLocation(String defaultTargetFeedingLocation) {
//		DefaultTargetFeedingLocation = defaultTargetFeedingLocation;
//	}

//	@JsonProperty("ContainerSize")
//	public Integer getContainerSize() {
//		return ContainerSize;
//	}
//
//	public void setContainerSize(Integer containerSize) {
//		ContainerSize = containerSize;
//	}

//    @JsonProperty("MSL")
//    public String getMSL() {
//        return MSL;
//    }
//
//    public void setMSL(String mSL) {
//        MSL = mSL;
//    }

	@JsonProperty("StateValue")
	public String getStateValue() {
		return StateValue;
	}

	public void setStateValue(String stateValue) {
		StateValue = stateValue;
	}

//    @JsonProperty("DefaultStationGroup")
//    public String getDefaultStationGroup() {
//        return DefaultStationGroup;
//    }
//
//    public void setDefaultStationGroup(String defaultStationGroup) {
//        DefaultStationGroup = defaultStationGroup;
//    }

//    @JsonProperty("CompanyNumber")
//    public String getCompanyNumber() {
//        return CompanyNumber;
//    }
//
//    public void setCompanyNumber(String companyNumber) {
//        CompanyNumber = companyNumber;
//    }

	@JsonProperty("FactoryNumber")
	public String getFactoryNumber() {
		return FactoryNumber;
	}

	public void setFactoryNumber(String factoryNumber) {
		FactoryNumber = factoryNumber;
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

	@JsonProperty("Creator")
	public String getCreator() {
		return Creator;
	}

	public void setCreator(String creator) {
		Creator = creator;
	}

	@JsonProperty("CreationDateTime")
	public Date getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	@JsonProperty("Editor")
	public String getEditor() {
		return Editor;
	}

	public void setEditor(String editor) {
		Editor = editor;
	}

	@JsonProperty("EditDateTime")
	public Date getEditDateTime() {
		return EditDateTime;
	}

	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}

	@JsonProperty("RequireFIFO")
	public String getRequireFIFO() {
		return RequireFIFO;
	}

	public void setRequireFIFO(String requireFIFO) {
		RequireFIFO = requireFIFO;
	}

	@JsonProperty("RequireCheckCustomerLabel")
	public String getRequireCheckCustomerLabel() {
		return RequireCheckCustomerLabel;
	}

	public void setRequireCheckCustomerLabel(String requireCheckCustomerLabel) {
		RequireCheckCustomerLabel = requireCheckCustomerLabel;
	}
	
	
}
