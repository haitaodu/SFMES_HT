package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="core.ProcurementType")
public class ProcurementType {
	private Integer Id;
	private String ProcurementCode;
	private String Name;
	private String Description;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getProcurementCode() {
		return ProcurementCode;
	}
	public void setProcurementCode(String procurementCode) {
		ProcurementCode = procurementCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
