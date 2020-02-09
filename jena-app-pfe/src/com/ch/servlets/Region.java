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
import com.ch.model.Monument;
import com.ch.model.Site;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class Region
 */
@WebServlet("/Region")
public class Region extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/Region.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Region() {
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
		
		String nomReg =request.getParameter("nom_reg");
		System.out.println(nomReg);
	    Recherches rech = new Recherches();

	    List<Monument> mons= rech.rechMonumentParRegion(ont.getDataset(),nomReg);
	    List<Maison> mais = rech.rechMaisonParRegion(ont.getDataset(), nomReg);
	    List<Site>  sites = rech.rechSiteParRegion(ont.getDataset(), nomReg);
	    List<Espace> espaces = rech.rechEspaceParRegion(ont.getDataset(), nomReg);
	   
	    request.setAttribute("mons", mons);
	    request.setAttribute("mais", mais);
	    request.setAttribute("sites", sites);
	    request.setAttribute("espaces", espaces);
	    

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
