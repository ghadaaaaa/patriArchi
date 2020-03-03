package com.ch.servlets;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class test {
	
	 public static void resize(File imageFile, int height, int width) throws IOException {
		 
		    BufferedImage img = ImageIO.read(imageFile);
	        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resized.createGraphics();
	        g2d.drawImage(tmp, 0, 0, null);
	        g2d.dispose();
	        ImageIO.write(resized,"jpg", imageFile);
	     
	    }
	
	public static void main (String[] args)
	{
		
		      try {
		    	  File file = new File("<%= request.getContextPath() %>/img/Hassan_Bacha.jpg");
		    	  resize(file,259,194);
		    	  System.out.println("end");
		      }catch(Exception e)
		      { System.out.println("catch");}
		    
		 
	}
		 

}