 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page import="fr.proxibanque.proxibanquev3.domaine.Client" %>	
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.Compte" %>
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.CompteCourant" %>
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.CompteEpargne" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Voir les comptes</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
          <li><a href="modifClient.jsp">Modifier les informations</a></li>
          <li class="active"><a href="comptesClient.jsp">Voir les comptes</a></li>
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
		<br><br><br>
	    <div class="alert alert-info">
  			<span class="glyphicon glyphicon-info-sign"></span> Vous souhaitez visualiser les comptes de 
  			<c:if test="${cli.typeClient == 9}">
  				<strong><c:out value= "${cli.prenomCli}"/> <c:out value= "${cli.nomCli}"/></strong>.
  			</c:if>
	  		<c:if test="${cli.typeClient == 7}">
	  			<strong><c:out value= "${cli.raisonSociale}"/></strong>.
  			</c:if>
		</div>
      <br><br>
      <p><strong>Informations du client:</strong></p>	
      <c:if test="${cli.typeClient == 9}">
	  <p><c:out value="${cli.prenomCli}"/> <c:out value="${cli.nomCli}"/> <br><c:out value="${cli.adresse}"/> <br><c:out value="${cli.codePostal}"/> <c:out value="${cli.ville}"/>.</p>
	  </c:if>
	  <c:if test="${cli.typeClient == 7}">
	  <p>Entreprise <c:out value="${cli.raisonSociale}"/><br>n°SIRET <c:out value="${cli.nSiret}"/><br><c:out value="${cli.adresse}"/> <br><c:out value="${cli.codePostal}"/> <c:out value="${cli.ville}"/>.</p>
	  </c:if>
	  <br>
		  <a href="javascript:history.go(-1)" class="btn btn-info btn-lg retour pull-right">
			  <span class="glyphicon glyphicon-arrow-left"></span> Retour
		  </a>
	  <br><br><br>
		<div class="table-responsive">          
		  <table class="table table-hover">
			<thead>
			  <tr align="left">
				<th>Type de compte</th>
				<th>Numéro de compte</th>
				<th>Solde disponible</th>
				<th>Détails</th>
			  </tr>
			</thead>
			<tbody id="myTable">
						<c:if test="${InfoCompte1 == 'compteCourant'}">
			  				<tr><td>Compte Courant</td>
			  				<td><c:out value= "${compte1.numCompte}"/></td>
			  				<td><c:out value= "${compte1.solde}"/> €</td>
			  				<td>Découvert autorisé : <c:out value= "${compte1.decouvert}"/> €</td></tr>
		  				</c:if>
		  				<c:if test="${InfoCompte2 == 'compteEpargne'}">
			  				<tr><td>Compte Epargne</td>
			  				<td><c:out value= "${compte2.numCompte}"/></td>
			  				<td><c:out value= "${compte2.solde}"/> €</td>
			  				<td>Taux de Rémunération : <c:out value= "${compte2.tauxRem}"/> %</td></tr>
		  				</c:if>
			</tbody>
		  </table>
		  </div>
    </div>
  </div>	
</div>


</body>
</html>
