package com.ch.model;

public class Region {
	
	private int idRegion;
	private String nomRegion;
	private String descRegion;
	private String situationRegion;
	
	
	
	
	public Region(int idRegion, String nomRegion, String descRegion, String situationRegion  )
	{
	
		this.idRegion = idRegion;
		this.nomRegion = nomRegion;
		this.descRegion = descRegion;
		this.situationRegion = situationRegion;
		
	}
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	public String getNomRegion() {
		return nomRegion;
	}
	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
	public String getDescRegion() {
		return descRegion;
	}
	public void setDescRegion(String descRegion) {
		this.descRegion = descRegion;
	}
	public String getSituationRegion() {
		return situationRegion;
	}
	public void setSituationRegion(String situationRegion) {
		this.situationRegion = situationRegion;
	}
	
	

}
