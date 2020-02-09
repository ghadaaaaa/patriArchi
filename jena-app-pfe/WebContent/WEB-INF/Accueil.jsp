<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Turath</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">

</head>

<body>



  <h1 class="site-heading text-center text-white d-none d-lg-block">
     <img src="img/Turath.png" alt="">
    <span class="site-heading-upper text-primary mb-3">Une plateforme pour </span>
    <span class="site-heading-upper text-primary mb-3">Le patrimoine architectural algérien</span>
  </h1>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
 
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
      aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item active px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Accueil
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="Region">Les Espaces </a>
           
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Maisons traditionnelles</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Monuments historiques</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Sites archéologiques</a>
          </li>
           <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Contact</a>
          </li>
          
          
        </ul>
      </div>
    </div>
  
  </nav>
 
       
      <div class=" page-section text-center ">
        <div class="col-xl-6xl-auto text-center">
          <div class="cta-inner rounded text-center">
            <label></label>
                <input class="rounded champ text-center" id="keyWord" name="keyWord" placeholder="Recherche par mot clé" >
           
          </div>
       </div>
        </div>

          <div class="  row   ">

         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les maisons<br> traditionnelles</span>
        
          </h2>
            <img class=" rounded" src="img/Timgad.jpeg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
 
        
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper"><br> Les espaces</span>
        
          </h2>
            <img class=" rounded" src="img/Timgad.jpeg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
   
       
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les sites<br> archéologiques</span>
        
          </h2>
            <img class=" rounded" src="img/Timgad.jpeg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
    
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les monuments<br> historiques</span>
        
          </h2>
            <img class=" rounded" src="img/Martyrs.jpg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
       
 </div>

  
  <section class="page-section cta text-center">

     <div class="container text-center">
     
            <div class="maping">
      <div class="container ">
      <div class="col-xl-4mx-auto">
  
            <div class="bg-faded rounded">
            
                  <img src="img/carte.png" usemap="#image-map">

                      <map name="image-map">
   		             
   		                 <area target="_self" alt="Alger" title="Alger" href="Region?nom_reg=Alger" coords="384,60,391,56,397,55,403,57,398,63,390,64" shape="poly">
                       <area target="_self" alt="Oran" title="Oran" href="Region?nom_reg=Oran" coords="263,101,272,92,283,92,291,90,279,105" shape="poly">
   					   <area  target="_self" alt="Tlemcen" title="Tlemcen" href="Region?nom_reg=Tlemcen" coords="230,114,251,109,259,113,265,121,265,130,265,138,261,145,248,149,245,140,243,132,241,125,235,121" shape="poly">
		
					  </map>
         
     
       </div>
         </div>
        </div>
      </div>

    </div>
    

  </section>
  


  



  <footer class="footer text-faded text-center py-5">
    <div class="container">

           
                  <img src="img/Turath.png" alt="">
                   
                        <div class="footer_widget">
                            <h3 class="footer_title">
                                 
                            </h3>
                            <p class="footer_text"> Un travail conçu pour la préservation du patrimoine architectural Algérien.
                              <br>
                               Développé par: FLISSI Ghada et SLATNIA Chahinez  
                              <p class="footer_text"> Contactez-nous: Turath@gmail.dz</p>
                             
                               </div>
                   
               
                        <div class="socail_links">
                           
                                
                                    <a href="#">
                                        <i class="fa fa-facebook-square"></i>
                                    </a>
                                
                                    <a href="#">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                               
                              
                                    <a href="#">
                                        <i class="fa fa-instagram"></i>
                                    </a>
                              
                        </div>
                   
                </div>
  
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
    