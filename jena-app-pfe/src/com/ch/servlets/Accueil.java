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

import com.ch.controller.RechRegion;
import com.ch.controller.Recherches;
import com.ch.model.EltPatri;
import com.ch.model.Monument;
import com.ch.model.Region;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
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
		Recherches rech= new Recherches();
		List<com.ch.model.Maison> mais = rech.listeMaison(ont.getDataset());	
		List<com.ch.model.Site> sites = rech.listeSite(ont.getDataset());
		List<com.ch.model.Monument> mons = rech.listeMonument(ont.getDataset());
		List<com.ch.model.Espace> esps = rech.listeEspace(ont.getDataset());
	
		request.setAttribute("mais", mais);
		request.setAttribute("sites", sites);
		request.setAttribute("mons", mons);
		request.setAttribute("esps", esps);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}


}
