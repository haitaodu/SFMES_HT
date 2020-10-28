package com.smartflow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ：tao
 * @date ：Created in 2020/8/31 14:24
 * @description：${description}
 */

@Entity
@Table(name="core.ContainerType")
public class ContainerType implements Serializable {
    private int id;
    private String typeCode;
    private String name;
    private String describe;

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TypeCode", nullable = false, length = 50)
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Describe", nullable = true, length = 50)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContainerType that = (ContainerType) o;
        return id == that.id &&
                Objects.equals(typeCode, that.typeCode) &&
                Objects.equals(name, that.name) &&
                Objects.equals(describe, that.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeCode, name, describe);
    }
}
