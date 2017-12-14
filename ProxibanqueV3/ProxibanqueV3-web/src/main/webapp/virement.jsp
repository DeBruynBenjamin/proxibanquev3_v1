<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.Client"%>
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.ClientParticulier"%>
   <%@ page import="fr.proxibanque.proxibanquev3.domaine.ClientEntreprise"%>
  <%@ page import="fr.proxibanque.proxibanquev3.domaine.Compte"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Virement</title>
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
          <li><a href="modifClient.jsp">Modifier les informations</a></li>
          <li><a href="comptesClient.jsp">Voir les comptes</a></li>
          <li class="active"><a href="virement.jsp">Effectuer un virement</a></li>
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
		<br> <br><br>
	    <div class="alert alert-info">
  			<span class="glyphicon glyphicon-info-sign"></span> Vous souhaitez réaliser un virement à partir des comptes de 
  			<c:if test="${cli.typeClient == 9}">
  				<strong><c:out value= "${cli.prenomCli}"/> <c:out value= "${cli.nomCli}"/></strong>.
  			</c:if>
	  		<c:if test="${cli.typeClient == 7}">
	  			<strong><c:out value= "${cli.raisonSociale}"/></strong>.
  			</c:if>
		</div>
      <br>	
      <c:if test="${mistake=='NotPossible'}">
	  	<div class="alert alert-danger">
  			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"> <c:out value="${erreur1}"/>.
		</div>
	 </c:if>
	  <c:if test="${cli.typeClient == 9}">
	  <p>Virement depuis les comptes de <strong><c:out value="${cli.prenomCli}"/> <c:out value="${cli.nomCli}"/></strong></p>
	  </c:if>
	  <c:if test="${cli.typeClient == 7}">
	  <p>Virement depuis les comptes de <strong><c:out value="${cli.raisonSociale}"/></strong>, SIRET n°<c:out value="${cli.nSiret}"/></p>
	  </c:if>
	  
	  <br>	
			<form action="ViremtServlet" method="POST">
			<div class="form-group">
					<select class="form-control" name="compteEmetteur" required> 
						  
						<c:if test="${InfoCompte1 == 'compteCourant'}">
							<option value= "${compte1.numCompte}">Compte Courant n°<c:out value= "${compte1.numCompte}"/>,
								<c:if test="${cli.typeClient == 7}">
									<strong><c:out value= "${cli.raisonSociale}"/></strong>, Solde : <c:out value= "${compte1.solde}"/> €
								</c:if>
								<c:if test="${cli.typeClient == 9}">
								<strong><c:out value= "${cli.prenomCli}"/> <c:out value= "${cli.nomCli}"/></strong>, Solde : <c:out value= "${compte1.solde}"/> €
								</c:if>
							</option>
						</c:if>
		  				<c:if test="${InfoCompte2 == 'compteEpargne'}">
							<option value= "${compte2.numCompte}">Compte Epargne n°<c:out value= "${compte2.numCompte}"/>,
								<c:if test="${cli.typeClient == 7}">
									<strong><c:out value= "${cli.raisonSociale}"/></strong>, Solde : <c:out value= "${compte2.solde}"/> €
								</c:if>
								<c:if test="${cli.typeClient == 9}">
								<strong><c:out value= "${cli.prenomCli}"/> <c:out value= "${cli.nomCli}"/></strong>, Solde : <c:out value= "${compte2.solde}"/> €
								</c:if> 
							</option>
		  				</c:if>   
					</select>
			 </div>
			  <p> vers le compte </p>
			   
			  <div class="form-group">
				<input type="search" id="searchBox" class="form-control" placeholder="Rechercher un compte...">
			  </div> 
			  <div class="form-group">
					<select id="comptesatrier" class="form-control" class="whatever" name="compteCible" required> 				
						  <c:forEach items="${allComptesEpargne}" var="compte">
								<option value= "${compte.numCompte}">
									  <c:if test="${compte.client.typeClient == 9}">
										  <c:out value="${compte.client.nomCli}"/> <c:out value="${compte.client.prenomCli}"/>
									  </c:if>
									  <c:if test="${compte.client.typeClient == 7}">
										  <c:out value="${compte.client.raisonSociale}"/>
									  </c:if>
								, Compte Epargne n°${compte.numCompte},  Solde : ${compte.solde} €
								</option>		  
						  </c:forEach>
						  <c:forEach items="${allComptesCourant}" var="compte">
								<option value= "${compte.numCompte}">
										<c:if test="${compte.client.typeClient == 9}">
										 <c:out value="${compte.client.nomCli}"/> <c:out value="${compte.client.prenomCli}"/>
										  </c:if>
										  <c:if test="${compte.client.typeClient == 7}">
										  <c:out value="${compte.client.raisonSociale}"/>
										  </c:if>
								, Compte Courant n°${compte.numCompte},  Solde : ${compte.solde} €
								</option>
						  </c:forEach>
				  </select>
			  </div>
			  <br>

				<div class="form-group">
				  <label for="montant">Montant :</label>
				  <input type="number" min="0" step="0.01" class="form-control" placeholder="Entrez le montant" name="montant" required>
				</div> 
				<div class="form-group">
				  <label for="libelle">Libellé:</label>
				  <input type="text" class="form-control" placeholder="Entrez le libelle" name="libelle">
				</div>
				<br>
				<input type="button" class="btn btn-default" value="Annuler" onclick='location.href="accueil.jsp"' />
				<button type="submit" class="btn btn-default btn-success">Valider</button>
			</form>
    </div>
  </div>	
</div>


</body>
</html>
