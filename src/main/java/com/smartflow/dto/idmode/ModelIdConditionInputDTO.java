package com.smartflow.dto.idmode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 14:28
 * @description：${description}
 * @modified By：
 * @version: version
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ModelIdConditionInputDTO {
    private int pageSize;
    private int pageIndex;
    private String modelName;
    private String modelCode;
    private String productName;
    private String stationNumber;
    private  String TDto;



    @JsonProperty("ProductName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPageSize() {
        return pageSize;
    }

    @JsonProperty("PageSize")
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }
    @JsonProperty("PageIndex")
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getModelName() {
        return modelName;
    }
    @JsonProperty("ModelName")
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelCode() {
        return modelCode;
    }
    @JsonProperty("ModelCode")
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getTDto() {
        return TDto;
    }

    public void setTDto(String TDto) {
        this.TDto = TDto;
    }

    @Override
    public String toString() {
        return "ModelIdConditionInputDTO{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", modelName='" + modelName + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", TDto='" + TDto + '\'' +
                '}';
    }


    @JsonProperty("StationNumber")
    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
}
