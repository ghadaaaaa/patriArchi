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
import org.python.core.PyFloat;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import com.ch.model.EltPatri;
import com.ch.model.Espace;
import com.ch.model.Maison;
import com.ch.model.Monument;
import com.ch.model.Region;
import com.ch.model.Site;

public class Recherches {
	
	public Recherches () {}
	
	
	//****************Liste elts patri Pour Map******************//

public List<EltPatri> ListeEltsPatriMap(Dataset dataset)

{
 dataset.begin(ReadWrite.READ) ;
 try

 {

      String qs1 = "Select ?idEltPatri ?altitude ?longitude  ?descEltPatri ?dateConstruction ?périodeConstruction where {graph ?g {"
    		+ "?EltPatri <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri ."
      		+ "?EltPatri<http://www.w3.org/ontologies/patriArchi/altitude> ?altitude."
      		+ "?EltPatri <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude."
            + "?EltPatri <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
            + "?EltPatri <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
            + "?EltPatri <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
      		+ "}}";

	   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
	    ResultSet rs = qExec.execSelect() ;
        List<EltPatri> elts= new ArrayList<EltPatri>();
        int id =0;
        List<String> appels;
        List<String> images;

		
		QuerySolution soln;
		RDFNode node;
	    while(rs.hasNext()){
		    soln = rs.nextSolution() ;
		    node = soln.get("idEltPatri"); id = (int)node.asNode().getLiteralValue();
		    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
		    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
		    appels = rechAppelEP(dataset,id);
		    images = rechImagesEP(dataset, id);	    
		    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
		    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
		    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
			EltPatri elt = new EltPatri(id, descEltPatri,altitude,longitude,dateConstruction,périodeConstruction, appels, images);
			elts.add(elt);		
		 }
        return elts;
	 }
 } 
 finally { dataset.end() ; }

}

/************************/
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
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/typeEspace> ?typeEspace." 
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, typeEspace, appels, images);
				  
				    espaces.add(espace);
				    }
		
			   return espaces;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 
	 /******************************Liste des espaces*********************************/
	 public List<Espace> listeEspace (Dataset dataset)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/typeEspace> ?typeEspace." 
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, typeEspace, appels, images);
				  
				    espaces.add(espace);
				    }
		
			   return espaces;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 /************************RECHERCHE ESPACE PAR APPELLATION EP*****************/
	 public List<Espace> rechEspaceParAppel( Dataset dataset, String  appel)
	 {
		 
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeEspace where {graph ?g {"
			 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/SappelerEP> ?AppellationEP."          
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Espace <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Espace  <http://www.w3.org/ontologies/patriArchi/typeEspace> ?typeEspace." 
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> \""+appel+"\" ."
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,longitude,
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
				  
				    mais.add(mai);
				    }
			   return mais;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 /****************************Recherche Maison par Id******************/
	 public Maison rechMaisonParId (Dataset dataset, int idMaison)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?descEltPatri ?altitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
			  		+ "where {graph ?g {"
			  		
		      + "?Maison <http://www.w3.org/ontologies/patriArchi/idEltPatri> "+idMaison+"."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceSol> ?surfaceSol."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceMaison> ?surfaceMaison."
	          
	         + "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
			 //  ResultSetFormatter.out(rs) ;
			   QuerySolution soln;
			   RDFNode node;
			   Maison mai = null;
			
			   List<String> appels;
			   List<String> images;
			   if(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    appels = rechAppelEP(dataset,idMaison);
				    images =rechImagesEP(dataset, idMaison);
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idMaison,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
				  
				    }
			   return mai;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 
	 /***************************Recherche Maison Par Appellation******************************************/
	public List<Maison> rechMaisonParAppel(Dataset dataset, String appel)
	{
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 { String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
			  		+ "where {graph ?g {"
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/SappelerEP> ?AppellationEP."          
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceSol> ?surfaceSol."
	          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceMaison> ?surfaceMaison."
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> \""+appel+"\" ."
	         + "}}";
			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			   ResultSet rs = qExec.execSelect() ;
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison,appels, images );
				  
				    mais.add(mai);
				    }
			   return mais;
			 }
		 } 
		 finally { dataset.end() ; }
	}
	 /********************************Liste toutes les maisons**********************/
	 public List<Maison> listeMaison(Dataset dataset)
		{
		   dataset.begin(ReadWrite.READ) ;
			 try
			
			 {
				 String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude "
					  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSol ?surfaceMaison "
					  		+ "where {graph ?g {"        
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceSol> ?surfaceSol."
			          + "?Maison <http://www.w3.org/ontologies/patriArchi/surfaceMaison> ?surfaceMaison."
			      
			         + "}}";
				 try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
					   ResultSet rs = qExec.execSelect() ;
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
						    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
						    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
						    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
						    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
						    mai= new Maison(idEltPatri,descEltPatri,altitude,longitude,
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
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"
			 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
	          + "?Site <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/surfaceSite> ?surfaceSite."
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSite"); String surfaceSite = node.toString();
				    site= new Site(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, surfaceSite, appels,images);
				  
				    sites.add(site);
				    }
		
			   return sites;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 
	 
	 /******************************LISTE SITES*************************************/
	 public List<Site> listeSite (Dataset dataset)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"       
	          + "?Site <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/surfaceSite> ?surfaceSite."
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSite"); String surfaceSite = node.toString();
				    site= new Site(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, surfaceSite, appels,images);
				  
				    sites.add(site);
				    }
		
			   return sites;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	 
	 /********************Recherche Site Par Appellation EP***************************/
	 
	 public List<Site> rechSiteParAppel(Dataset dataset, String appel)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
			  		+ "?longitude ?dateConstruction  ?périodeConstruction ?surfaceSite where {graph ?g {"
			 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/SappelerEP> ?AppellationEP."          
	          + "?Site <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
	          + "?Site <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
	          + "?Site <http://www.w3.org/ontologies/patriArchi/surfaceSite> ?surfaceSite."
	          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> \""+appel+"\" ."
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
				    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSite"); String surfaceSite = node.toString();
				    site= new Site(idEltPatri,descEltPatri,altitude,longitude,
				    		dateConstruction,périodeConstruction, surfaceSite, appels,images);

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
				  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {"
				 
		          + "?Monument<http://www.w3.org/ontologies/patriArchi/SeSituerEP> ?Region."          
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude."  
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/typeMo> ?typeMo." 
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
					    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("typeMo") ;String typeMo  = node.toString();
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    mon= new Monument(idEltPatri,descEltPatri,altitude,longitude,
					    		dateConstruction,périodeConstruction,typeMo, appels,images );
					  
					    mons.add(mon);
					    }
			
				   return mons;
				 }
			 } 
			 finally { dataset.end() ; }
		 }
		 

			/******************************Lister tous les monuments*************************************/

			 public List<Monument> listeMonument (Dataset dataset)
			 {
				 dataset.begin(ReadWrite.READ) ;
				 try
				
				 {
					  String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude ?latitude "
					  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {"
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/typeMo> ?typeMo."
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
			          + "?Monument <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
			         + "}}";

					   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
					   ResultSet rs = qExec.execSelect() ;
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
						    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
						    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
						    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
						    node = soln.get("typeMo"); String typeMo = node.toString();
						    mon= new Monument(idEltPatri,descEltPatri,altitude,longitude,
						    		dateConstruction,périodeConstruction, typeMo,appels,images );			  
						    mons.add(mon);
						    }
					   return mons;
					 }
				 } 
				 finally { dataset.end() ; }
			 }
		 
	 /*****************************Recherche Monument Par Appellation*******************************/
		 
		 public List<Monument> rechMonumentParAppel(Dataset dataset,String appel)
		 {
			 dataset.begin(ReadWrite.READ) ;
			 try
			
			 {
				  String qs1 = "Select ?idEltPatri  ?descEltPatri ?altitude ?latitude"
				  		+ "?longitude ?dateConstruction  ?périodeConstruction ?typeMo where {graph ?g {"
				 
		          + "?Monument<http://www.w3.org/ontologies/patriArchi/SappelerEP> ?AppellationEP."          
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/idEltPatri> ?idEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/descEltPatri> ?descEltPatri." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/altitude> ?altitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/longitude> ?longitude." 
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/typeMo> ?typeMo."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/dateConstruction> ?dateConstruction."
		          + "?Monument <http://www.w3.org/ontologies/patriArchi/périodeConstruction> ?périodeConstruction."
		          + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> \""+appel+"\" ."
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
					    node = soln.get("altitude") ; float altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitude") ;float longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
					    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
					    node = soln.get("typeMo"); String typeMo = node.toString();
					    mon= new Monument(idEltPatri,descEltPatri,altitude,longitude,
					    		dateConstruction,périodeConstruction, typeMo, appels,images );
					  
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
				 }  finally { dataset.end() ; }}
		 
		 
		 public List<String> listeNomsAppels(Dataset dataset)
			{

			   dataset.begin(ReadWrite.READ) ;
				 try
				
				 {
					   String qs1 = "Select  ?appelEP  where {graph ?g {"
					 + "?AppellationEP <http://www.w3.org/ontologies/patriArchi/appelEP> ?appelEP.}}";

					   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
					   ResultSet rs = qExec.execSelect() ;
						List<String> appels = new ArrayList<String>();
						QuerySolution soln;
						RDFNode node;
					   while (rs.hasNext()){
						    soln = rs.nextSolution() ;	    
						    node = soln.get("appelEP") ; String appel = node.toString();
						    appels.add(appel);
						 }		
						return appels;
					 }
				 }  finally { dataset.end() ; }}
	   	 
	      /*****************Similarité syntaxique**********************/
		 private float syntaxicSimilarity(String mot, String motCle)
		 {
			 PythonInterpreter mypy = new PythonInterpreter();
			 mypy.set("motCle", new PyString(motCle));
			 mypy.set("region", new PyString(mot));
			 mypy.exec("import difflib \n"
		      		   + "syntaxSim= difflib.SequenceMatcher(a=motCle.lower(), b=region.lower()).ratio()");
		     PyObject syntaxSim =mypy.get("syntaxSim");
		     float sim= Float.parseFloat(syntaxSim.toString());
			 return sim;
		 }
		 
		  /**************************Similarité sémantique**************************/
		 /******************************Recherche par mot clé*******************************/
		 
		 
		 public List<EltPatri> rechParMotCle(Dataset dataset, String motCle)
		 {  
			 List<EltPatri> elts = new ArrayList<EltPatri>();
			 List<String> regs= listeNomsRegions(dataset);
			 Iterator<String> iter = regs.iterator();
			 String reg="";
			 while (iter.hasNext() && syntaxicSimilarity(reg, motCle)<0.8) 
				 {reg =iter.next();
				 System.out.println(reg); }
			
			 if(syntaxicSimilarity(reg, motCle)>=0.8) 
			 {      elts.addAll(rechMonumentParRegion(dataset,reg));
				    elts.addAll(rechMaisonParRegion(dataset, reg));
				    elts.addAll( rechSiteParRegion(dataset, reg));
				    elts.addAll(rechEspaceParRegion(dataset, reg));} 
			 else
			 {
				 List<String> appels= listeNomsAppels(dataset);
				  iter = appels.iterator();
				 String appel="";
				 while (iter.hasNext() && syntaxicSimilarity(appel, motCle)<0.8) 
					 {appel =iter.next();
					 System.out.println(appel); }
				 if(syntaxicSimilarity(appel, motCle)>=0.8) 
				 {      elts.addAll(rechMonumentParAppel(dataset,appel));
					     elts.addAll(rechMaisonParAppel(dataset, appel));
					    elts.addAll( rechSiteParAppel(dataset, appel));
					    elts.addAll(rechEspaceParAppel(dataset, appel));
			     } 
				 else
				 {
					 
					 
				 }
			 }
	    	 
			 return elts;
             }
}