package com.smartflow.model;

import com.smartflow.dto.EditInitializationStationDTO;

public class ReturnSelectedStationGroupForStationViewModel {
	private String Factory;
	private String SelectedStationGroup;
	private String TotalStationGroup;
	private EditInitializationStationDTO Station;
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	public String getSelectedStationGroup() {
		return SelectedStationGroup;
	}
	public void setSelectedStationGroup(String selectedStationGroup) {
		SelectedStationGroup = selectedStationGroup;
	}
	public String getTotalStationGroup() {
		return TotalStationGroup;
	}
	public void setTotalStationGroup(String totalStationGroup) {
		TotalStationGroup = totalStationGroup;
	}
	public EditInitializationStationDTO getStation() {
		return Station;
	}
	public void setStation(EditInitializationStationDTO station) {
		Station = station;
	}
	
	
	
}
