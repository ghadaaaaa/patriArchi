package com.ch.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import com.ch.model.EltPatri;
import com.ch.model.Espace;
import com.ch.model.Maison;
import com.ch.model.Monument;
import com.ch.model.Site;

public class Recherches {
	
	public Recherches () {}
	
	public List<String> rechAppelEP(Dataset dataset, int idEltPatri )
	{
	
		 try
		
		 {
		
		 String qs1 = "Select ?appelEP where {graph ?g {"
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/Appele> ?EltPatri."          
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> ?appelEP." 
	          + "?EltPatri <http://www.w3.org/ontologies/patriArchi/idEltPatri> "+idEltPatri+"."
	         + "}}";
		 
		  try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
	       ResultSet rs = qExec.execSelect() ;
		   //ResultSetFormatter.out(rs) ;
	       QuerySolution soln;
		   RDFNode node;
		   List<String> appels = new ArrayList<String>();
		   while(rs.hasNext()){
			    soln = rs.nextSolution() ;
			    node = soln.get("appelEP"); String appel = node.toString();
			    appels.add(appel);
			    }
	
		   return appels;
		    }
		 }
	  
	 finally {  }
	}
	
	
	/**********************************************************************************/
	
	public List<String> rechImagesEP(Dataset dataset, int idEltPatri )
	{
		try
		
		 {
		
		 String qs1 = "Select ?illustration where {graph ?g {"
	          + "?Illustration <http://www.w3.org/ontologies/patriArchi/IllustrerEP> ?EltPatri."          
	          + "?Illustration <http://www.w3.org/ontologies/patriArchi/illustration> ?illustration." 
	          + "?EltPatri <http://www.w3.org/ontologies/patriArchi/idEltPatri> "+idEltPatri+"."
	         + "}}";
		 
		  try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
	       ResultSet rs = qExec.execSelect() ;
		   //ResultSetFormatter.out(rs) ;
	       QuerySolution soln;
		   RDFNode node;
		   List<String> images = new ArrayList<String>();
		   while(rs.hasNext()){
			    soln = rs.nextSolution() ;
			    node = soln.get("illustration"); String appel = node.toString();
			    images.add(appel);
			    }
	
		   return images;
		    }
		 }
	  
	 finally {  }
		
	}
	/******************************ESPACE*********************************/
	 public List<Espace> rechEspaceParRegion (Dataset dataset, String nomRegion)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"
			 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/latitude> ?latitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/TypeEspace> ?TypeEspace." 
	          + "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> \""+nomRegion+"\" ."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Espace espace;
			   List<Espace> espaces = new ArrayList<Espace>();
			   List<String> appels;
			   List<String> images;
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images = rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction, typeEspace, appels, images);
				  
				    espaces.add(espace);
				    }
		
			   return espaces;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 
/************************************MAISON***********************************/
	 
	 public List<Maison> rechMaisonParRegion (Dataset dataset, String nomRegion)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
			  		+ "where {graph ?g {"
			 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/latitude> ?latitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceSol> ?surfaceSol."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceMaison> ?surfaceMaison."
	          + "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> \""+nomRegion+"\" ."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Maison mai;
			   List<Maison> mais = new ArrayList<Maison>();
			
			   List<String> appels;
			   List<String> images;
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images =rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
				  
				    mais.add(mai);
				    }
			   return mais;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 
	 
	 /******************************SITE*************************************/
	 public List<Site> rechSiteParRegion (Dataset dataset, String nomRegion)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction where {graph ?g {"
			 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
	          + "?Site <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/latitude> ?latitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> \""+nomRegion+"\" ."
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Site site;
			   List<Site> sites = new ArrayList<Site>();
			   List<String> appels;
			   List<String> images;
			
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    appels = rechAppelEP(dataset,idEltPatri);
				    images =rechImagesEP(dataset, idEltPatri);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    site= new Site(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction, appels,images);
				  
				    sites.add(site);
				    }
		
			   return sites;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 

		/******************************MONUMENT*************************************/
		

		
		 public List<Monument> rechMonumentParRegion (Dataset dataset, String nomRegion)
		 {
			 dataset.begin(ReadWrite.READ) ;
			 try
			
			 {
				  String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude ?latitude"
				  		+ "?longitude ?dateConstruction  ?périodeConstruction where {graph ?g {"
				 
		          + "?Monument<http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/latitude> ?latitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
		       
		          + "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> \""+nomRegion+"\" ."
		         + "}}";

				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;
				 //  ResultSetFormatter.out(rs) ;
				   QuerySolution soln;
				   RDFNode node;
				   Monument mon;
				   List<String> appels;
				   List<String> images;
				   List<Monument> mons = new ArrayList<Monument>();
				   
				   while(rs.hasNext()){
					    soln = rs.nextSolution() ;
					    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
					    appels = rechAppelEP(dataset,idEltPatri);
					    images = rechImagesEP(dataset, idEltPatri);
					    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
					    node = soln.get("altitude"); String altitude = node.toString();
					    node = soln.get("latitude"); String latitude = node.toString();
					    node = soln.get("longitude"); String longitude = node.toString();
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    mon= new Monument(idEltPatri,descEltPatri,altitude,latitude,longitude,
					    		dateConstruction,périodeConstruction,appels,images );
					  
					    mons.add(mon);
					    }
			
				   return mons;
				 }
			 } 
			 finally { dataset.end() ; }
		 }
		 	/*********************MOT_CLE*********************************/
		 
		 public List<String> listeNomsRegions(Dataset dataset)
			{

			   dataset.begin(ReadWrite.READ) ;
				 try
				
				 {
					   String qs1 = "Select  ?nomRegion  where {graph ?g {"
					 + "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> ?nomRegion.}}";

					   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
					   ResultSet rs = qExec.execSelect() ;
						List<String> nomsRegs = new ArrayList<String>();
						QuerySolution soln;
						RDFNode node;
					   while (rs.hasNext()){
						    soln = rs.nextSolution() ;	    
						    node = soln.get("nomRegion") ; String nom = node.toString();
						    nomsRegs.add(nom);
						 }		
						return nomsRegs;
					 }
				 } 
				 finally { dataset.end() ; }
			}
		 
	/*	 public List<EltPatri> rechParMotCle(Dataset dataset, String motCle)
		 {  
			 List<EltPatri> elts = new ArrayList<EltPatri>();
			 List<String> regs= listeNomsRegions(dataset);
			 Iterator<String> iter = regs.iterator();
			 
			 while (iter.hasNext()) {
			      MonType value = iter.next();
			      System.out.println( value );
			 }
			 return elts;
			 
		 }*/
}
