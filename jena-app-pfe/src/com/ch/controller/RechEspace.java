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

import com.ch.model.Espace;
import com.ch.model.Site;

public class RechEspace {

	public RechEspace() {}
	/******************************ESPACE*************************************/
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
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("typeEspace"); String typeEspace = node.toString();
				    espace= new Espace(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction, typeEspace);
				  
				    espaces.add(espace);
				    }
		
			   return espaces;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
}
