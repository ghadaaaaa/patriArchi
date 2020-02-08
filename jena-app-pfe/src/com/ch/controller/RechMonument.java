package com.ch.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import com.ch.model.Monument;

public class RechMonument {
	
	public RechMonument() {}
	
	
	/******************************MONUMENT*************************************/
	 public List<Monument> rechMonumentParRegion (Dataset dataset, String nomRegion)
	 {
		 dataset.begin(ReadWrite.READ) ;
		 try
		
		 {
			  String qs1 = "Select ?idEltPatri ?descEltPatri ?altitude ?latitude"
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
			   List<Monument> mons = new ArrayList<Monument>();
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    mon= new Monument(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction);
				  
				    mons.add(mon);
				    }
		
			   return mons;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	

}
