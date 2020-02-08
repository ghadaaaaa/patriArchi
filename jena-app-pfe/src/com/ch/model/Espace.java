package com.ch.model;

public class Espace extends EltPatri
{
	private String typeEspace;
	
	public Espace(int idEltPatri,String descEltPatri,String altitude,String latitude,
			String longitude, String dateConstruction,String périodeConstruction, 
			String typeEspace)
	{
		super(idEltPatri,descEltPatri,altitude,latitude,longitude,
				dateConstruction,périodeConstruction);
		this.typeEspace=typeEspace;
	
	}
}
