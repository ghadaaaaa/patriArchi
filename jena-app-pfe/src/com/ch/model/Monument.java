package com.ch.model;

import java.util.List;

public class Monument extends EltPatri
{
	private String typeMo;
	
	public Monument(int idEltPatri,String descEltPatri,float altitude,
			float longitude, String dateConstruction,String périodeConstruction, String typeMo, List<String> appels, 
			List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,longitude,
				dateConstruction,périodeConstruction, appels, images);
		this.typeMo=typeMo;
	
	}

}
