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
import com.ch.model.Espace;
import com.ch.model.Maison;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class Espaces
 */
@WebServlet("/Espaces")
public class Espaces extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Espaces.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Espaces() {
        super();
        // TODO Auto-generated constructor stub
    }

	
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
		   List<Espace> esps = rech.listeEspace(ont.getDataset());
		   request.setAttribute("esps", esps);

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
