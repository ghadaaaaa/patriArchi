package com.ch.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

import de.linguatools.disco.CorruptConfigFileException;
import de.linguatools.disco.DISCO;
import de.linguatools.disco.DISCO.SimilarityMeasure;

public class SimilarityCalculationDemo {
	
	
	public static void main (String[] args) throws CorruptIndexException, FileNotFoundException, IOException, CorruptConfigFileException
	{
		DISCO disco = DISCO.load("cc.de.300-COL.denseMatrix");
		float sim = disco.semanticSimilarity("Haus", "Häuschen", 
		      	    	DISCO.getVectorSimilarity(SimilarityMeasure.COSINE));
		System.out.println("similarity between 'Haus' and 'Häuschen': "+sim);
		

	}
}
		



