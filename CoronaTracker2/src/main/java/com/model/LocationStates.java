package com.model;

public class LocationStates {
	private String state;
	private String country;
	private long latestTotalCases;
	private long diffFromPrevDay;
	
	public LocationStates() {
	}
	
	
	public LocationStates(String state, String country, long latestTotalCases, long diffFromPrevDay) {
		this.state = state;
		this.country = country;
		this.latestTotalCases = latestTotalCases;
		this.diffFromPrevDay = diffFromPrevDay;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(long latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public long getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(long diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	
	

}
