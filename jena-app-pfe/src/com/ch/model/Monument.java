package com.ch.model;

import java.util.List;

public class Monument extends EltPatri
{
	
	public Monument(int idEltPatri,String descEltPatri,String altitude,String latitude,
			String longitude, String dateConstruction,String périodeConstruction, List<String> appels, 
			List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,latitude,longitude,
				dateConstruction,périodeConstruction, appels, images);
	
	}

}
