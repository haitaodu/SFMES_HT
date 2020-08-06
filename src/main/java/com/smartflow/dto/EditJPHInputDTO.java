package com.smartflow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class EditJPHInputDTO {
    private Integer Id;
    private BigDecimal JPH;
    private Integer EditorId;
    private Integer State;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public BigDecimal getJPH() {
        return JPH;
    }

    public void setJPH(BigDecimal JPH) {
        this.JPH = JPH;
    }

    public Integer getEditorId() {
        return EditorId;
    }

    public void setEditorId(Integer editorId) {
        EditorId = editorId;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
