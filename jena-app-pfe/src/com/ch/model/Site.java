package com.ch.model;

import java.util.List;

public class Site extends EltPatri{
	private String surfaceSite;
	
	public Site(int idEltPatri,String descEltPatri,String altitude,String latitude,
			String longitude, String dateConstruction,String périodeConstruction, String surfaceSite, 
			List<String> appels, List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,latitude,longitude,
				dateConstruction,périodeConstruction,appels,images);
		this.surfaceSite= surfaceSite;
	
	}
}
