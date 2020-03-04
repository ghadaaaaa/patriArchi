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
 * Servlet implementation class EspaceMap
 */
@WebServlet("/EspaceMap")
public class EspaceMap extends HttpServlet {
	public static final String VUE ="/WEB-INF/Espace.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EspaceMap() {
        super();
        // TODO Auto-generated constructor stub
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
		
		
		  Recherches rech = new Recherches();
		  String msg ="";
		  String appelEsp= request.getParameter("appelEsp");
		  List<com.ch.model.Espace> esps= rech.rechEspaceParAppel(ont.getDataset(), appelEsp);
		
		  if (esps.isEmpty()) 
	        {System.out.println("Vide");
			  msg= "Aucun espace"; }
		  else if (esps.size()>1)
		  {System.out.println("Plusieurs espaces ont le mm nom ");
		  msg= "Plusieurs espaces"; }
		  
		  else
		  {
			  com.ch.model.Espace esp = esps.get(0);
		      request.setAttribute("esp", esp);
		  }
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
