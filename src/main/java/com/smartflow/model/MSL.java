package com.smartflow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="core.MSL")
public class MSL {
	private Integer Id;
	private String Name;
	private Integer ExposureTimeInMinutes;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getExposureTimeInMinutes() {
		return ExposureTimeInMinutes;
	}
	public void setExposureTimeInMinutes(Integer exposureTimeInMinutes) {
		ExposureTimeInMinutes = exposureTimeInMinutes;
	}
	
}
