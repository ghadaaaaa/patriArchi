package com.ch.model;

import java.util.List;

public class Espace extends EltPatri
{
	private String typeEspace;
	
	public Espace(int idEltPatri,String descEltPatri,float altitude,
			float longitude, String dateConstruction,String périodeConstruction, 
			String typeEspace, List<String> appels, List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,longitude,
				dateConstruction,périodeConstruction, appels, images);
		this.typeEspace=typeEspace;
	
	}
}
