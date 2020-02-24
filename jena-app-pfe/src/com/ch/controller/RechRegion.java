package com.ch.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

import com.ch.model.Region;

public class RechRegion 

{
	public RechRegion() {}
	
        	//****************Liste Regions Pour Map******************//
	
	public List<Region> ListeRegionsMap(Dataset dataset)
	 
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
		
	          String qs1 = "Select ?idRegion ?nomRegion ?descRegion ?situationRegion ?altitudeR ?longitudeR where {graph ?g {"
	        		+ "?Region <http://www.w3.org/ontologies/patriArchi/idRegion> ?idRegion ."
	          		+ "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> ?nomRegion ."
	          		+ "?Region <http://www.w3.org/ontologies/patriArchi/descRegion> ?descRegion ."
	          		+ "?Region <http://www.w3.org/ontologies/patriArchi/situationRegion> ?situationRegion."
	          		+ "?Region <http://www.w3.org/ontologies/patriArchi/altitudeR> ?altitudeR."
	          		+ "?Region <http://www.w3.org/ontologies/patriArchi/longitudeR> ?longitudeR."
	          		+ "}}";

			   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
			    ResultSet rs = qExec.execSelect() ;
                List< Region> regs= new ArrayList<Region>();
                int id =0;
			    String nom="";
				String desc ="";
				String situation ="";
				float altitude;
				float longitude;
				
				QuerySolution soln;
				RDFNode node;
			    while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idRegion"); id = (int)node.asNode().getLiteralValue();
				    node = soln.get("nomRegion") ; nom = node.toString();
				    node = soln.get("descRegion") ; desc = node.toString();
				    node = soln.get("altitudeR") ; altitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("longitudeR") ; longitude = Float.parseFloat(node.toString()) ;
				    node = soln.get("situationRegion") ; situation = node.toString();
					Region reg = new Region(id, nom, desc, situation,altitude, longitude);
					regs.add(reg);
					
				 }
                return regs;
			 }
		 } 
		 finally { dataset.end() ; }

	 }
	
	
	
	      //***********************RECHERCHE REGION PAR ID*********************************//
		 
		 public Region rechRegionParId (Dataset dataset, int idRegion)
		 
		 {
			 dataset.begin(ReadWrite.READ) ;
			 try
			
			 {
			
		          String qs1 = "Select  ?nomRegion ?descRegion ?situationRegion where {graph ?g {"
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/idRegion> "+ idRegion+" . "
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/nomRegion> ?nomRegion ."
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/descRegion> ?descRegion ."
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/situationRegion> ?situationRegion."
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/altitudeR> ?altitudeR."
		          		+ "?Region <http://www.w3.org/ontologies/patriArchi/longitudeR> ?longitudeR."
		          		+ "}}";

				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;

				    String nom="";
					String desc ="";
					String situation ="";
					float altitude;
					float longitude;
					QuerySolution soln;
					RDFNode node;
				    if(rs.hasNext()){
					    soln = rs.nextSolution() ;
					    node = soln.get("nomRegion") ; nom = node.toString();
					    node = soln.get("descRegion") ; desc = node.toString();
					    node = soln.get("altitudeR") ; altitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("longitudeR") ; longitude = Float.parseFloat(node.toString()) ;
					    node = soln.get("situationRegion") ; situation = node.toString();
						Region reg = new Region(idRegion, nom, desc, situation,altitude, longitude);
						return reg;
						
					 }
				    else {  return null;}

				 }
			 } 
			 finally { dataset.end() ; }

		 }
		
 
	}

