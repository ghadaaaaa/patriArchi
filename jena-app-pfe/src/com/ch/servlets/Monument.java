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
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class Monument
 */
@WebServlet("/Monument")
public class Monument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Monument.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monument() {
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
		int idMonument =Integer.parseInt( request.getParameter("idMonument"));
		  Recherches rech = new Recherches();
		  String msg ="";
		  com.ch.model.Monument mon= rech.rechMonumentParId (ont.getDataset(),idMonument);
		
		  
		  if (mon == null) 
	        {
			  msg= "Aucun monument"; }
		  else
		  {System.out.println(mon.getAltitude());
			request.setAttribute("mon", mon);}
		  request.setAttribute("msg", msg);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

	/**
	 * @see Httpervlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
