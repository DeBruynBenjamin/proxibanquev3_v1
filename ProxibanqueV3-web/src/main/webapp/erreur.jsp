<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Erreur</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
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
      <div id="navbar2" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="accueil.jsp">Accueil</a></li>
          <li><a href="DeconnecterServlet">Se déconnecter<span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
  </nav>


<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-2 sidenav">
	<img src="img/logo2.png" alt="Proxibanque" width="100%" margi>
	<center><h4>Bonjour, <c:out value= "${cons.prenomCons}"/> <c:out value= "${cons.nomCons}"/> </h4></	center>	
    </div>

    <div class="col-sm-10 tomove">
		<br>
	  <h2>Erreur</h2>
      <br>	
	  <p>L'opération que vous avez demandé a mené à  l'erreur suivante :</p>
	  <br>	
		<h4><c:out value="${erreur}"/> </h4>
		<br><br>
		<p>Pour revenir à  l'accueil, cliquez <a href="accueil.jsp">ici</a>.</p>
    </div>
  </div>	
</div>


</body>
</html>
