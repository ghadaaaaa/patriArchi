package com.ch.model;

public class Maison  extends EltPatri
{
	
	private String surfaceSol;
	private String surfaceMaison;

	public Maison(int idEltPatri,String descEltPatri,String altitude,String latitude,
			String longitude, String dateConstruction,String périodeConstruction, 
			String surfaceSol, String surfaceMaison)
	{
		super(idEltPatri,descEltPatri,altitude,latitude,longitude,
				dateConstruction,périodeConstruction);
		
		this.surfaceSol= surfaceSol;
		this.surfaceMaison = surfaceMaison;
	}

	
	
	public String getSurfaceSol() {
		return surfaceSol;
	}

	public void setSurfaceSol(String surfaceSol) {
		this.surfaceSol = surfaceSol;
	}

	public String getSurfaceMaison() {
		return surfaceMaison;
	}

	public void setSurfaceMaison(String surfaceMaison) {
		this.surfaceMaison = surfaceMaison;
	}
	
	

}
