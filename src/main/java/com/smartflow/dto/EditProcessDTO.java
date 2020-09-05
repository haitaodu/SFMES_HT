package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class EditProcessDTO {

    @NotNull(message="{process.Id.invalid}")
    private Integer Id;
    @NotBlank(message="{process.ProcessNumber.invalid}")
    @Size(max=46,message="{process.ProcessNumber.required}")
    private String ProcessNumber;
    private Integer Version;
    @NotNull(message="{process.State.invalid}")
    private Integer State;
    private String ValidBegin;
    private String ValidEnd;
    @NotNull(message="{process.FactoryId.invalid}")
    private Integer FactoryId;
    private Integer EditorId;
    @NotBlank(message="{process.ParentProcessNumber.invalid}")
    @Size(max=46,message="{process.ParentProcessNumber.required}")
    private String ParentProcessNumber;
    private String Description;
}
