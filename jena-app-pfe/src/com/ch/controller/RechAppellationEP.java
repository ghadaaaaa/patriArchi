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

public class RechAppellationEP {
	
	public RechAppellationEP() {}
	
	public List<String> rechAppelEP(Dataset dataset, int idEltPatri )
	{
		 dataset.begin(ReadWrite.READ) ;
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
	  
	 finally { dataset.end() ; }
	}

}
