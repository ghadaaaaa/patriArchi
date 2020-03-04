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
 * Servlet implementation class MonumentMap
 */
@WebServlet("/MonumentMap")
public class MonumentMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Monument.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonumentMap() {
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
		  String appelMo=request.getParameter("appelMo");
		  List<com.ch.model.Monument> mons= rech.rechMonumentParAppel(ont.getDataset(), appelMo);
		
		  if (mons.isEmpty()) 
	        {System.out.println("Vide");
			  msg= "Aucun mon"; }
		  else if (mons.size()>1)
		  {System.out.println("Plusieurs mons ont le mm nom ");
		  msg= "Plusieurs mons"; }
		  
		  else
		  {
			  com.ch.model.Monument mon = mons.get(0);
		      request.setAttribute("mon", mon);
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
