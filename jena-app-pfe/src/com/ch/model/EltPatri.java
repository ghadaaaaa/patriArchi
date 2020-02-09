package com.ch.model;

import java.util.List;

public class EltPatri 
{
	private int idEltPatri;
	private String descEltPatri;
	private String altitude;
	private String latitude;
	private String longitude;
	private String dateConstruction;
	private String périodeConstruction;
	private List<String> appels;
	private List<String> images;
	
	public EltPatri(int idEltPatri,String descEltPatri,String altitude,String latitude,
			String longitude, String dateConstruction,String périodeConstruction, List<String> appels, 
			List<String> images)
	{
		this.idEltPatri=idEltPatri;
		this.descEltPatri= descEltPatri;
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateConstruction = dateConstruction;
		this.périodeConstruction = périodeConstruction;
		this.appels =appels;
		this.images = images;
	}
	

	public List<String> getAppels() {
		return appels;
	}


	public void setAppels(List<String> appels) {
		this.appels = appels;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public int getIdEltPatri() {
		return idEltPatri;
	}

	public void setIdEltPatri(int idEltPatri) {
		this.idEltPatri = idEltPatri;
	}

	public String getDescEltPatri() {
		return descEltPatri;
	}

	public void setDescEltPatri(String descEltPatri) {
		this.descEltPatri = descEltPatri;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDateConstruction() {
		return dateConstruction;
	}

	public void setDateConstruction(String dateConstruction) {
		this.dateConstruction = dateConstruction;
	}

	public String getPériodeConstruction() {
		return périodeConstruction;
	}

	public void setPériodeConstruction(String périodeConstruction) {
		this.périodeConstruction = périodeConstruction;
	}
	
	
}
