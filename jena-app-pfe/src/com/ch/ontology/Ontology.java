package com.ch.ontology;

import java.io.File;
import java.io.IOException;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class Ontology {
	 
	private static String tdbPath= "E:\\TDB";
	private static String fileTtl = "E:\\newPatriArchi.ttl";
	private static String fileOwl = "E:\\patriArchi.owl";
	private static String IRIOnt="http://www.w3.org/ontologies/patriArchi.owl";
    
	private OWLOntology ontology;
	private Dataset dataset;
	

	
   /*************************************/
	public Dataset getDataset()
	{return this.dataset;}
	
	public OWLOntology getOntology()
	{ return this.ontology;}
	/*************************************/
	public void loadOWLOntology() throws OWLOntologyCreationException
	{
		this.ontology = null;
		try 
		{
			OWLOntologyManager man = OWLManager.createOWLOntologyManager();
			File file = new File(fileOwl);
			this.ontology = man.loadOntologyFromOntologyDocument(file);
			 System.out.println(this.ontology);

		}catch (OWLOntologyCreationException e) {
			 e.printStackTrace();
		}
	}
	
	/**********************************************/
	
	public void saveOnt( OWLOntology ontology) throws OWLOntologyStorageException {
		File saveIn = new File(fileTtl);
		IRI fileIRI = IRI.create(saveIn.toURI());
		TurtleDocumentFormat turtleFormat = new TurtleDocumentFormat();
		turtleFormat.setDefaultPrefix(ontology.getOntologyID().getOntologyIRI().get().getIRIString() + "/");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		manager.saveOntology(ontology, turtleFormat, fileIRI);
	}
	/*****************************************/
   public Model convertOWLOntologytoModel(final OWLOntology ontology) 
   { 
	   Model model = ModelFactory.createDefaultModel();
	   
	    try (PipedInputStream is = new PipedInputStream(); 
	    PipedOutputStream os = new PipedOutputStream(is)) {
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	
	                    ontology.getOWLOntologyManager().saveOntology(ontology, new TurtleDocumentFormat(), os);
	                    os.close();
	                } catch (OWLOntologyStorageException | IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
	        model.read(is, null, "TURTLE");
	        
	         
	        
	        return model;
	    } catch (Exception e) {
	        throw new RuntimeException("Could not convert OWL API ontology to JENA API model.", e);
	    }
   }
   
   /****************************************************/
   
   public void loadTtlFileInTDB()
   {
	   this.dataset = TDBFactory.createDataset(tdbPath);
	   Model model = this.dataset.getNamedModel("patriArchi");
	    this.dataset.begin(ReadWrite.WRITE);
	   TDBLoader.loadModel(model, fileTtl);
	   this.dataset.commit();
	   this.dataset.end();
	}
   
   /****************************************************/
   
 /*  public void convertTDBtoOWLFile(Dataset ds)
   {
	
	   Model model = ds.getNamedModel("http://patriFile");
	   File file = new File("E:\\file.owl");
	           FileOutputStream os=null;
	           try {
	               os = new FileOutputStream(file);
	              // model.writeAll(os, "RDF/OWL",null);//To write model with import closure
	               model.write(os, "RDF/XML",null);//To write model without import closure
	               os.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
   }*/

}
