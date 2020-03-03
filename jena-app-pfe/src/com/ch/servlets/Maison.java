package com.ch.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.ch.controller.Recherches;
import com.ch.model.*;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class Maison
 */
@WebServlet("/Maison")
public class Maison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Maison.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Maison() {
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
		int idMaison =Integer.parseInt( request.getParameter("idMaison"));
		  Recherches rech = new Recherches();
		  String msg ="";
		  com.ch.model.Maison mai= rech.rechMaisonParId (ont.getDataset(),idMaison);
		
		  
		  if (mai == null) 
	        {System.out.println("nuuuuuuulll");
			  msg= "Aucune maison"; }
		  else
		  {System.out.println(mai.getAltitude());
			request.setAttribute("mai", mai);}
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
