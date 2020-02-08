package com.ch.ontology;



import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.ch.controller.RechAppellationEP;
import com.ch.controller.RechEspace;
import com.ch.controller.RechMaison;
import com.ch.controller.RechMonument;
import com.ch.controller.RechRegion;
import com.ch.controller.RechSite;
import com.ch.model.Espace;
import com.ch.model.Maison;
import com.ch.model.Monument;
import com.ch.model.Region;
import com.ch.model.Site;

public class test {
	
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
		
		Ontology ont = new Ontology();
		ont.loadOWLOntology();
		ont.saveOnt(ont.getOntology());
		ont.loadTtlFileInTDB();
		RechRegion rechReg = new RechRegion();
		List<Region> regs= rechReg.listerRegion(ont.getDataset());
		Region reg= rechReg.rechRegionParId(ont.getDataset(),2); 
		//System.out.println(reg.getSituationRegion());
	
		//System.out.println(regs.get(0).getNomRegion());
		//System.out.println(regs.get(1).getNomRegion());
        RechMaison rechMai = new RechMaison();
        RechMonument rechMon= new RechMonument();
        RechSite rechSite= new RechSite();
        RechEspace rechEsp = new RechEspace();
        RechAppellationEP rechApp = new RechAppellationEP();
         List<Monument> mons = rechMon.rechMonumentParRegion(ont.getDataset(),"Alger");   
        List<Maison> mais = rechMai.rechMaisonParRegion(ont.getDataset(),"Alger");
        List<Site> sites = rechSite.rechSiteParRegion(ont.getDataset(),"Alger");
        List<Espace> espaces = rechEsp.rechEspaceParRegion(ont.getDataset(),"Alger");
        System.out.println(mons.get(0).getIdEltPatri());
        List<String> appels = rechApp.rechAppelEP(ont.getDataset(),mons.get(0).getIdEltPatri());
        if (!appels.isEmpty()) System.out.println(appels.get(0));

         if(mais.isEmpty()) System.out.println("empty maison");
         if(sites.isEmpty()) System.out.println("empty site");
         if(espaces.isEmpty()) System.out.println("empty espace");
		}
	
}
		
	