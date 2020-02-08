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

import com.ch.model.Maison;

public class RechMaison {
	
	public RechMaison() {}
	
	
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
			   while(rs.hasNext()){
				    soln = rs.nextSolution() ;
				    node = soln.get("idEltPatri") ;int idEltPatri = (int)node.asNode().getLiteralValue();
				    node = soln.get("descEltPatri"); String descEltPatri = node.toString();
				    node = soln.get("altitude"); String altitude = node.toString();
				    node = soln.get("latitude"); String latitude = node.toString();
				    node = soln.get("longitude"); String longitude = node.toString();
				    node = soln.get("dateConstruction"); String dateConstruction = node.toString();
				    node = soln.get("périodeConstruction"); String périodeConstruction = node.toString();
				    node = soln.get("surfaceSol"); String surfaceSol = node.toString();
				    node = soln.get("surfaceMaison"); String surfaceMaison = node.toString();
				    mai= new Maison(idEltPatri,descEltPatri,altitude,latitude,longitude,
				    		dateConstruction,périodeConstruction,surfaceSol, surfaceMaison);
				  
				    mais.add(mai);
				    }
			   return mais;
			 }
		 } 
		 finally { dataset.end() ; }
	 }
	
	 
	 //public RechMaisonParCatégorie(){}
	//public RechEltPatriParId() {}
	//public RechEltPatriParNom() {}
	//public RechEltPatriParPeriode{}
	//public RechEltPatriParMotCle() {}

}
