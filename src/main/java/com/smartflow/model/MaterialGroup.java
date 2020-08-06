package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="core.MaterialGroup")
public class MaterialGroup {
	private Integer Id;
	private String MaterialGroupCode;
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
	public String getMaterialGroupCode() {
		return MaterialGroupCode;
	}
	public void setMaterialGroupCode(String materialGroupCode) {
		MaterialGroupCode = materialGroupCode;
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
