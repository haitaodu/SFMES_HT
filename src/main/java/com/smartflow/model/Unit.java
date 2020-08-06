package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
@Entity
@Table(name="core.Unit")
public class Unit {
	private Integer Id;
	private String UnitCode;
	private String Name;
	private boolean State;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUnitCode() {
		return UnitCode;
	}
	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public boolean isState() {
		return State;
	}
	public void setState(boolean state) {
		State = state;
	}
	
}
