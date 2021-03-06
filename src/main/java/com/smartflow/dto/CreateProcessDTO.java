package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author haita
 */
@Data
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateProcessDTO {
    @NotBlank(message="{process.ProcessNumber.invalid}")
    @Size(max=46,message="{process.ProcessNumber.required}")
    private String ProcessNumber;
    @NotBlank(message="{process.MaterialNumber.invalid}")
    private String MaterialNumber;
    @NotNull(message="{process.State.invalid}")
    private Integer State;
    private String ValidBegin;
    private String ValidEnd;
    @NotNull(message="{process.FactoryId.invalid}")
    private Integer FactoryId;
    private Integer CreatorId;
    @NotBlank(message="{process.ParentProcessNumber.invalid}")
    @Size(max=46,message="{process.ParentProcessNumber.required}")
    private String ParentProcessNumber;
    private String Description;
    @NotBlank(message="{process.MaterialNumber.invalid}")
    private String MaterialSplits;// "物料号|版本号|名称"
}
