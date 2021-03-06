package com.smartflow.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * @author haita
 */
@Entity
@Table(name="core.Material")
public class Material {
	private Integer Id;
	private String MaterialNumber;
	private Integer Version;
	private String Description;
	private String Specification;
	private String CustomerMaterialNumber;
	private String SupplierMaterialNumber;
	private Integer MaterialGroupType;
	private Boolean IsProduct;
	private Boolean IsMultiPanel;
	private Boolean RequireBackflush;
	private Integer NumberOfPanels;
	private Integer Unit;
	private Boolean SetupFlag;
	private BigDecimal MinimumPackageQuantity;
	private BigDecimal ExpirationTime;
	private Integer DefaultStorageLocationId;
	private Integer DefaultTargetFeedingLocationId;
	private Integer ContainerSize;
	private Integer MSL;
	private Integer State;
	private Integer DefaultStationGroupId;
	private Integer CompanyId;
	private Integer FactoryId;
	private Date CreationDateTime;
	private Integer CreatorId;
	private Date ValidBegin;
	private Date ValidEnd;
	private Integer EditorId;
	private Date EditDateTime;
	private String ProjectName;
	private String OriginalNumber;
	private String CADDrawingPartNumber;
	private Boolean RequireFIFO;
	private Boolean RequireCheckCustomerLabel;
	private int MaxDeliveryQuantity;
	private int MaxWashQuantity;
    private ContainerType ContainerType;
    private Station station;
    private Station station2;





	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "StationId")
	public Station getStation2() {
		return station2;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TrackStationId")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ContainerTypeId")
	public com.smartflow.model.ContainerType getContainerType() {
		return ContainerType;
	}

	public void setContainerType(com.smartflow.model.ContainerType containerType) {
		ContainerType = containerType;
	}

	public String getMaterialNumber() {
		return MaterialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		MaterialNumber = materialNumber;
	}
	public Integer getVersion() {
		return Version;
	}
	public void setVersion(Integer version) {
		Version = version;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSpecification() {
		return Specification;
	}
	public void setSpecification(String specification) {
		Specification = specification;
	}
	public String getCustomerMaterialNumber() {
		return CustomerMaterialNumber;
	}
	public void setCustomerMaterialNumber(String customerMaterialNumber) {
		CustomerMaterialNumber = customerMaterialNumber;
	}
	public String getSupplierMaterialNumber() {
		return SupplierMaterialNumber;
	}
	public void setSupplierMaterialNumber(String supplierMaterialNumber) {
		SupplierMaterialNumber = supplierMaterialNumber;
	}
	public Integer getMaterialGroupType() {
		return MaterialGroupType;
	}
	public void setMaterialGroupType(Integer materialGroupType) {
		MaterialGroupType = materialGroupType;
	}
	public Boolean getIsProduct() {
		return IsProduct;
	}
	public void setIsProduct(Boolean isProduct) {
		IsProduct = isProduct;
	}
	public Boolean getIsMultiPanel() {
		return IsMultiPanel;
	}
	public void setIsMultiPanel(Boolean isMultiPanel) {
		IsMultiPanel = isMultiPanel;
	}
	public Boolean getRequireBackflush() {
		return RequireBackflush;
	}
	public void setRequireBackflush(Boolean requireBackflush) {
		RequireBackflush = requireBackflush;
	}
	public Integer getNumberOfPanels() {
		return NumberOfPanels;
	}
	public void setNumberOfPanels(Integer numberOfPanels) {
		NumberOfPanels = numberOfPanels;
	}
	public Integer getUnit() {
		return Unit;
	}
	public void setUnit(Integer unit) {
		Unit = unit;
	}
	public Boolean getSetupFlag() {
		return SetupFlag;
	}
	public void setSetupFlag(Boolean setupFlag) {
		SetupFlag = setupFlag;
	}

	public BigDecimal getMinimumPackageQuantity() {
		return MinimumPackageQuantity;
	}
	public void setMinimumPackageQuantity(BigDecimal minimumPackageQuantity) {
		MinimumPackageQuantity = minimumPackageQuantity;
	}
	public BigDecimal getExpirationTime() {
		return ExpirationTime;
	}
	public void setExpirationTime(BigDecimal expirationTime) {
		ExpirationTime = expirationTime;
	}

	public Integer getDefaultStorageLocationId() {
		return DefaultStorageLocationId;
	}
	public void setDefaultStorageLocationId(Integer defaultStorageLocationId) {
		DefaultStorageLocationId = defaultStorageLocationId;
	}
	public Integer getDefaultTargetFeedingLocationId() {
		return DefaultTargetFeedingLocationId;
	}
	public void setDefaultTargetFeedingLocationId(Integer defaultTargetFeedingLocationId) {
		DefaultTargetFeedingLocationId = defaultTargetFeedingLocationId;
	}
	public Integer getContainerSize() {
		return ContainerSize;
	}
	public void setContainerSize(Integer containerSize) {
		ContainerSize = containerSize;
	}
	public Integer getMSL() {
		return MSL;
	}
	public void setMSL(Integer mSL) {
		MSL = mSL;
	}
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}
	public Integer getDefaultStationGroupId() {
		return DefaultStationGroupId;
	}
	public void setDefaultStationGroupId(Integer defaultStationGroupId) {
		DefaultStationGroupId = defaultStationGroupId;
	}
	public Integer getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(Integer companyId) {
		CompanyId = companyId;
	}
	public Integer getFactoryId() {
		return FactoryId;
	}
	public void setFactoryId(Integer factoryId) {
		FactoryId = factoryId;
	}
	public Date getCreationDateTime() {
		return CreationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	public Date getValidBegin() {
		return ValidBegin;
	}
	public void setValidBegin(Date validBegin) {
		ValidBegin = validBegin;
	}
	public Date getValidEnd() {
		return ValidEnd;
	}
	public void setValidEnd(Date validEnd) {
		ValidEnd = validEnd;
	}
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getOriginalNumber() {
		return OriginalNumber;
	}
	public void setOriginalNumber(String originalNumber) {
		OriginalNumber = originalNumber;
	}
	public String getCADDrawingPartNumber() {
		return CADDrawingPartNumber;
	}
	public void setCADDrawingPartNumber
			(String cADDrawingPartNumber) {
		CADDrawingPartNumber = cADDrawingPartNumber;
	}
	public Boolean getRequireFIFO()
	{
		return RequireFIFO;
	}
	public void setRequireFIFO
			(Boolean requireFIFO) {
		RequireFIFO = requireFIFO;
	}
	public Boolean getRequireCheckCustomerLabel() {
		return RequireCheckCustomerLabel;
	}
	public void setRequireCheckCustomerLabel(Boolean requireCheckCustomerLabel) {
		RequireCheckCustomerLabel = requireCheckCustomerLabel;
	}

    public int getMaxDeliveryQuantity() {
        return MaxDeliveryQuantity;
    }

    public void setMaxDeliveryQuantity(int maxDeliveryQuantity) {
        MaxDeliveryQuantity = maxDeliveryQuantity;
    }

    public int getMaxWashQuantity() {
		return MaxWashQuantity;
	}

	public void setMaxWashQuantity(int maxWashQuantity) {
		MaxWashQuantity = maxWashQuantity;
	}

	@Override
	public String toString() {
		return "Material{" +
				"Id=" + Id +
				", MaterialNumber='" + MaterialNumber + '\'' +
				", Version=" + Version +
				", Description='" + Description + '\'' +
				", Specification='" + Specification + '\'' +
				", CustomerMaterialNumber='" + CustomerMaterialNumber + '\'' +
				", SupplierMaterialNumber='" + SupplierMaterialNumber + '\'' +
				", MaterialGroupType=" + MaterialGroupType +
				", IsProduct=" + IsProduct +
				", IsMultiPanel=" + IsMultiPanel +
				", RequireBackflush=" + RequireBackflush +
				", NumberOfPanels=" + NumberOfPanels +
				", Unit=" + Unit +
				", SetupFlag=" + SetupFlag +
				", MinimumPackageQuantity=" + MinimumPackageQuantity +
				", ExpirationTime=" + ExpirationTime +
				", DefaultStorageLocationId=" + DefaultStorageLocationId +
				", DefaultTargetFeedingLocationId=" + DefaultTargetFeedingLocationId +
				", ContainerSize=" + ContainerSize +
				", MSL=" + MSL +
				", State=" + State +
				", DefaultStationGroupId=" + DefaultStationGroupId +
				", CompanyId=" + CompanyId +
				", FactoryId=" + FactoryId +
				", CreationDateTime=" + CreationDateTime +
				", CreatorId=" + CreatorId +
				", ValidBegin=" + ValidBegin +
				", ValidEnd=" + ValidEnd +
				", EditorId=" + EditorId +
				", EditDateTime=" + EditDateTime +
				", ProjectName='" + ProjectName + '\'' +
				", OriginalNumber='" + OriginalNumber + '\'' +
				", CADDrawingPartNumber='" + CADDrawingPartNumber + '\'' +
				", RequireFIFO=" + RequireFIFO +
				", RequireCheckCustomerLabel=" + RequireCheckCustomerLabel +
				", MaxDeliveryQuantity=" + MaxDeliveryQuantity +
				", MaxWashQuantity=" + MaxWashQuantity +
				", ContainerType=" + ContainerType +
				", station=" + station +
				", station2=" + station2 +
				'}';
	}
}
