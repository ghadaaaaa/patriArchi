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
import com.ch.model.EltPatri;
import com.ch.ontology.Ontology;

/**
 * Servlet implementation class MotCle
 */
@WebServlet("/MotCle")
public class MotCle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE ="/WEB-INF/MotCle.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotCle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String keyWord = request.getParameter( "keyWord");
	
	    byte[] temporaire = keyWord.getBytes("ISO-8859-1");
	    String motCle = new String(temporaire , 0, temporaire .length, "UTF-8");
	    System.out.println(motCle);
		  Recherches rech = new Recherches();
	
		//  if (rech.rechMonumentParAppel(ont.getDataset(), motCle).isEmpty()) System.out.println("emptyyyyyyyyyyyys");
		  List<EltPatri> elts= rech.rechParMotCle(ont.getDataset(), motCle);
		 if ( elts.isEmpty()) System.out.println("emptyyyyyyyyyyyys");
		  request.setAttribute("elts", elts);
		this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
	}

}
