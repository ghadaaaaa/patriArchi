package com.ch.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.ch.controller.Recherches;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class MaisonMap
 */
@WebServlet("/MaisonMap")
public class MaisonMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Maison.jsp";
       
 
    public MaisonMap() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Ontology ont = new Ontology();
		try {
			ont.loadOWLOntology();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ont.saveOnt(ont.getOntology());
		} catch (OWLOntologyStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ont.loadTtlFileInTDB();
		String appelMaison = request.getParameter("appelMa");
		  Recherches rech = new Recherches();
		  String msg ="";
		  List<com.ch.model.Maison> mais= rech.rechMaisonParAppel(ont.getDataset(), appelMaison);
		
		  if (mais.isEmpty()|| mais.size()>1) 
	        {System.out.println("ERROR OF SIZE ");
			  msg= "Aucune maison"; }
		  else
		  {
			  com.ch.model.Maison mai = mais.get(0);
			request.setAttribute("mai", mai);}
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
