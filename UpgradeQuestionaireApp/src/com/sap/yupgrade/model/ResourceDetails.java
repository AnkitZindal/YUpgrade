package com.sap.yupgrade.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResourceDetails {
	private String name;
	private boolean fullTime;
	private boolean outofFactory;
	private Integer[] listOfActivities;
	private Map<Integer, List<String>> effortBreakup;
	
	public ResourceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFullTime() {
		return fullTime;
	}
	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}
	public boolean isOutofFactory() {
		return outofFactory;
	}
	public void setOutofFactory(boolean outofFactory) {
		this.outofFactory = outofFactory;
	}
	public Integer[] getListOfActivities() {
		return listOfActivities;
	}
	public void setListOfActivities(Integer[] listOfActivities) {
		this.listOfActivities = listOfActivities;
	}
	public ResourceDetails(String name, boolean fullTime, boolean outofFactory, Integer[] listOfActivities) {
		super();
		this.name = name;
		this.fullTime = fullTime;
		this.outofFactory = outofFactory;
		this.listOfActivities = listOfActivities;
	}
	@Override
	public String toString() {
		return "ResourceDetails [name=" + name + ", fullTime=" + fullTime + ", outofFactory=" + outofFactory
				+ ", listOfActivities=" + Arrays.toString(listOfActivities) + ", effortBreakup=" + effortBreakup + "]";
	}
	public Map<Integer, List<String>> getEffortBreakup() {
		return effortBreakup;
	}
	public void setEffortBreakup(Map<Integer, List<String>> effortBreakup) {
		this.effortBreakup = effortBreakup;
	}
	
	


}
