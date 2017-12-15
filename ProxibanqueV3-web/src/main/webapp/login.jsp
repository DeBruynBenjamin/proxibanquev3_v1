<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Authentification</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/main.css">
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
  

</head>
<body>

  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar2">
          <span class="sr-only">Navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand"><img src="img/logo.png" alt="Proxibanque">
        </a>
      </div>
      <!--<div id="navbar2" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="#">Accueil</a></li>
          <li><a href="DeconnecterServlet">Se déconnecter</a></li>	 
        </ul>
      </div>-->
      <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
  </nav>


<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-2 sidenav">
	<br><br>
	<br><br>
	<img src="img/logo2.png" alt="Proxibanque" width="100%">
    
    </div>

    <div class="col-sm-10 tomove" >
    
    
		<br><br>
      <h3><strong>Bienvenue sur ProxibanqueSI</strong></h3>
	  <br>
	  Cette application a pour objectif de simplifier les tâches de gestion de vos clients et de leur patrimoine.
      <br>	
	  <hr>
		<p>Pour continuer, veuillez vous authentifier:	
		<br>
	  <c:if test="${erreur == 'Erreur'}">
	  	<div class="alert alert-danger">
  			<strong>Erreur !</strong> Le login et/ou mot de passe rentrés ne sont pas reconnus.
		</div>
	 </c:if>
	 <br>
	<center><form action="j_security_check" method="POST">
	  <div class="form-group">
		<input type="text" class="form-control" name="j_username" id="logincons" placeholder="Entrez votre identifiant...">
	  </div>
	  <div class="form-group">
		<input type="password" class="form-control" name="j_password" id="mdpcons" maxlength="4" placeholder="Entrez votre de passe...">
	  </div>
	  <button type="submit" class="btn btn-default">Se connecter <span class="glyphicon glyphicon-log-in"></span></button>
	</form></center>
    </div>
  </div>	
</div>


</body>
</html>
