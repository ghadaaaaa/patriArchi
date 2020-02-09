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
	
	
	      //********************************************************//
		 
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
		          		+ "}}";

				   try(QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset)) {
				   ResultSet rs = qExec.execSelect() ;

				    String nom="";
					String desc ="";
					String situation ="";
					QuerySolution soln;
					RDFNode node;
				    if(rs.hasNext()){
					    soln = rs.nextSolution() ;
					    node = soln.get("nomRegion") ; nom = node.toString();
					    node = soln.get("descRegion") ; desc = node.toString();
					    node = soln.get("situationRegion") ; situation = node.toString();
						Region reg = new Region(idRegion, nom, desc, situation);
						return reg;
						
					 }
				    else {  return null;}

				 }
			 } 
			 finally { dataset.end() ; }

		 }
		
	}

