<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
  <%@ page import="fr.proxibanque.proxibanquev2.domaine.ClientEntreprise" %>
  <%@ page import="fr.proxibanque.proxibanquev2.domaine.ClientParticulier" %>
  <%= ClientEntreprise.TYPE_CLIENT_ENTREPRISE %>
  <%= ClientParticulier.TYPE_CLIENT_PARTICULIER %>
  
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Modifier les données client</title>
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
        <a class="navbar-brand"><img src="img/logo.png" alt="Proxibanque">
        </a>
      </div>
      <div id="navbar2" class="navbar-collapse collapse">
      	
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="modifClient.jsp">Modifier les informations</a></li>
          <li><a href="comptesClient.jsp">Voir les comptes</a></li>
          <li><a href="virement.jsp">Effectuer un virement</a></li>
          <li><a href="DeconnecterServlet">Se déconnecter <span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
      		<li><a href="accueil.jsp"><span class="glyphicon glyphicon-user"></span> <strong><c:out value= "${cons.prenomCons}"/> <c:out value= "${cons.nomCons}"/></strong></a></li>
      	</ul>
      </div>
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
	<br><br><br>
	<br><br>
    </div>

    <div class="col-sm-10 tomove">
		<br> <br> <br>
	    <div class="alert alert-info">
  			<span class="glyphicon glyphicon-info-sign"></span> Vous souhaitez modifier les informations de 
  			<c:if test="${cli.typeClient == 9}">
  				<strong><c:out value= "${cli.prenomCli}"/> <c:out value= "${cli.nomCli}"/></strong>.
  			</c:if>
	  		<c:if test="${cli.typeClient == 7}">
	  			<strong><c:out value= "${cli.raisonSociale}"/></strong>.
  			</c:if>
		</div>
      <br>	
	  Veuillez modifier les informations souhaitées, puis validez.  
		<br><br>
		  <a href="javascript:history.go(-1)" class="btn btn-info btn-lg retour pull-right">
			  <span class="glyphicon glyphicon-arrow-left"></span> Retour
			</a>
	  <br><br>
	  	<c:if test="${cli.typeClient == 9}">
		  <form method = "POST" action="ModifServlet">
			<div class="form-group">
			  <label for="nom">Nom:</label>
			  <input type="text" class="form-control" placeholder="Entrer le nom" name="nom" value="${cli.nomCli}">
			</div>
			<div class="form-group">
			  <label for="prenom">Prénom:</label>
			  <input type="text" class="form-control" placeholder="Entrer le prÃ©nom" name="prenom" value="${cli.prenomCli}">
			</div>
			<div class="form-group">
			  <label for="adresse">Adresse:</label>
			  <input type="text" class="form-control" placeholder="Entrer l'adresse" name="adresse" value="${cli.adresse}">
			</div>
			<div class="form-group">
			  <label for="codepostal">Code postal:</label>
			  <input type="text" class="form-control" placeholder="Entrer le code postal" name="codepostal" value="${cli.codePostal}">
			</div>
			<div class="form-group">
			  <label for="ville">Ville:</label>
			  <input type="text" class="form-control" placeholder="Entrer la ville" name="ville" value="${cli.ville}">
			</div>
			<div class="form-group">
			  <label for="telephone">Téléphone:</label>
			  <input type="tel" class="form-control" placeholder="Entrer le numÃ©ro de tÃ©lÃ©phone" name="telephone" value="${cli.telephone}" maxlength="10">
			</div>
			<div class="form-group">
			  <label for="email">Email:</label>
			  <input type="email" class="form-control" placeholder="Entrer l'email" name="email" value="${cli.email}">
			</div>
			<button type="submit" class="btn btn-default">Valider</button>
		  </form>
		 </c:if>
		 
		<c:if test="${cli.typeClient == 7}">
		  <form method = "POST" action="ModifServlet">
			<div class="form-group">
			  <label for="raisonsociale">RaisonSociale:</label>
			  <input type="text" class="form-control" placeholder="Entrer la raison sociale" name="raisonsociale" value="${cli.raisonSociale}">
			</div>
			<div class="form-group">
			  <label for="siret">SIRET:</label>
			  <input type="text" class="form-control" placeholder="Entrer le siret" name="siret" value="${cli.nSiret}" maxlength="14">
			</div>
			<div class="form-group">
			  <label for="adresse">Adresse:</label>
			  <input type="text" class="form-control" placeholder="Entrer l'adresse" name="adresse" value="${cli.adresse}">
			</div>
			<div class="form-group">
			  <label for="codepostal">Code postal:</label>
			  <input type="text" class="form-control" placeholder="Entrer le code postal" name="codepostal" value="${cli.codePostal}">
			</div>
			<div class="form-group">
			  <label for="ville">Ville:</label>
			  <input type="text" class="form-control" placeholder="Entrer la ville" name="ville" value="${cli.ville}">
			</div>
			<div class="form-group">
			  <label for="telephone">Téléphone:</label>
			  <input type="tel" class="form-control" placeholder="Entrer le numÃ©ro de tÃ©lÃ©phone" name="telephone" value="${cli.telephone}" maxlength="10">
			</div>
			<div class="form-group">
			  <label for="email">Email:</label>
			  <input type="email" class="form-control" placeholder="Entrer l'email" name="email" value="${cli.email}">
			</div>
			<button type="submit" class="btn btn-default">Valider</button>
		  </form>
		 </c:if>
		 
    </div>
  </div>	
</div>




</body>
</html>
