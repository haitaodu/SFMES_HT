package com.smartflow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ：tao
 * @date ：Created in 2020/6/11 10:48
 * @description：${description}
 * @modified By：
 * @version: version
 */

@Entity
@Table(name = "IdLayout", schema = "core", catalog = "MESDB_SXHTDL")
public class IdLayoutEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="Id")
    private int id;
    private int idModelId;
    private int sequence;
    private int type;
    private String value;
    private int minLength;
    private int maxLength;
    private String fillCharacter;
    private boolean fillPrefixOrSuffix;
    private boolean allowSpecialCharacters;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdModelId", nullable = false)
    public int getIdModelId() {
        return idModelId;
    }

    public void setIdModelId(int idModelId) {
        this.idModelId = idModelId;
    }

    @Basic
    @Column(name = "Sequence", nullable = false)
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Basic
    @Column(name = "Type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Value", nullable = true, length = 20)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "MinLength", nullable = false)
    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    @Basic
    @Column(name = "MaxLength", nullable = false)
    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Basic
    @Column(name = "FillCharacter", nullable = true, length = 20)
    public String getFillCharacter() {
        return fillCharacter;
    }

    public void setFillCharacter(String fillCharacter) {
        this.fillCharacter = fillCharacter;
    }

    @Basic
    @Column(name = "FillPrefixOrSuffix", nullable = false)
    public boolean isFillPrefixOrSuffix() {
        return fillPrefixOrSuffix;
    }

    public void setFillPrefixOrSuffix(boolean fillPrefixOrSuffix) {
        this.fillPrefixOrSuffix = fillPrefixOrSuffix;
    }

    @Basic
    @Column(name = "AllowSpecialCharacters", nullable = false)
    public boolean isAllowSpecialCharacters() {
        return allowSpecialCharacters;
    }

    public void setAllowSpecialCharacters(boolean allowSpecialCharacters) {
        this.allowSpecialCharacters = allowSpecialCharacters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdLayoutEntity that = (IdLayoutEntity) o;
        return id == that.id &&
                idModelId == that.idModelId &&
                sequence == that.sequence &&
                type == that.type &&
                minLength == that.minLength &&
                maxLength == that.maxLength &&
                fillPrefixOrSuffix == that.fillPrefixOrSuffix &&
                allowSpecialCharacters == that.allowSpecialCharacters &&
                Objects.equals(value, that.value) &&
                Objects.equals(fillCharacter, that.fillCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idModelId, sequence, type, value, minLength, maxLength, fillCharacter, fillPrefixOrSuffix, allowSpecialCharacters);
    }

    @Override
    public String toString() {
        return "IdLayoutEntity{" +
                "id=" + id +
                ", idModelId=" + idModelId +
                ", sequence=" + sequence +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", fillCharacter='" + fillCharacter + '\'' +
                ", fillPrefixOrSuffix=" + fillPrefixOrSuffix +
                ", allowSpecialCharacters=" + allowSpecialCharacters +
                '}';
    }
}
