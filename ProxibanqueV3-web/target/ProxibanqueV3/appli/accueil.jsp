 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Accueil</title>
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
          <li><a href="../deconnexion">Se déconnecter <span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
      		<li style="padding-right:34em;"><a href="accueil.jsp"><span class="glyphicon glyphicon-user"></span> <strong><c:out value= "${cons.prenomCons}"/> <c:out value= "${cons.nomCons}"/></strong></a></li>
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
	  <c:if test="${connecte == 'ConnexionOK'}">
	    <div class="alert alert-success">
  			<strong>Bonjour <c:out value= "${cons.prenomCons}"/> <c:out value= "${cons.nomCons}"/></strong>, vous êtes connecté à votre liste de clients.
		</div>
	  </c:if>
	  <c:if test="${erreur == 'NoSelection'}">
	  	<div class="alert alert-danger">
  			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> Vous n'avez pas sélectionné de client.
		</div>
	 </c:if>
	 <c:if test="${validvirement == 'Le virement a bien été pris en compte.'}">
	  	<div class="alert alert-success">
  			<span class="glyphicon glyphicon glyphicon-ok"></span> Le virement a bien été pris en compte.
		</div>
	 </c:if>
	 <c:if test="${validmodifs == 'Les modifications ont bien été prises en compte.'}">
	  	<div class="alert alert-success">
  			<span class="glyphicon glyphicon glyphicon-ok"></span> Les modifications ont bien été prises en compte.
		</div>
	 </c:if>
	  <br>	
	  <form action="accueil" method = "POST" name="form1">
	  		<a role="button" class="list-group-item sub-item btn btn-block btn-default" data-toggle="collapse" href="#menu1">   Sélectionnez le client sur lequel réaliser les démarches de gestion, puis choisissez une des opérations suivantes  <span class="caret"></span></a>
	  			<div class="collapse list-group panel" id="menu1">
					<input type="submit" class="list-group-item btn btn-block btn-default" name="action" value="Modifier les informations du client"></input>
					<input type="submit" class="list-group-item btn btn-block btn-default" name="action" value="Voir les comptes du client"></input>
					<input type="submit" class="list-group-item btn btn-block btn-default" name="action" value="Effectuer un virement"></input>
				</div>
	  <br> <br>
	  <p> Pour rechercher un client, veuillez utiliser la barre de recherche ci-dessous. </p>
	  <input class="form-control" id="myInput" type="text" placeholder="Rechercher..">
	  <br>	<br>
      
		  <table class="table table-hover">
			<thead>
			  <tr>
				<th></th>
				<th style="width:20%;" class="text-left">Nom</th>
				<th style="width:20%;" class="text-left">Prénom</th>
				<th style="width:30%;" class="text-left">Adresse</th>
				<th style="width:10%;" class="text-left">Téléphone</th>
				<th style="width:20%;" class="text-left">Email</th>
			  </tr>
			</thead>
			<tbody id="myTable">
			  <c:forEach items="${cons.listeCli}" var="cli">
			  <c:if test="${cli.typeClient==9}">
				  <tr>
					<td class="vcenter"><input type="radio" name="optradio" value="P${cli.idCli}"></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli.nomCli}"/></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli.prenomCli}"/></td>
					<td style="width:30%;" class="text-left"><c:out value= "${cli.adresse}"/> <c:out value= "${cli.codePostal}"/> <c:out value= "${cli.ville}"/></td>
					<td style="width:10%;" class="text-left"><c:out value= "${cli.telephone}"/></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli.email}"/></td>
				  </tr>	
				</c:if>		  
			  </c:forEach>
			</tbody>
		  </table>
		   <br>	   
		  <table class="table table-hover">
			<thead>
			  <tr>
				<th></th>
				<th style="width:20%;" class="text-left">Raison Sociale</th>
				<th style="width:20%;" class="text-left">SIRET</th>
				<th style="width:30%;" class="text-left">Adresse</th>
				<th style="width:10%;" class="text-left">Téléphone</th>
				<th style="width:20%;" class="text-left">Email</th>
			  </tr>
			</thead>
			<tbody id="myTable">
			  <c:forEach items="${cons.listeCli}" var="cli2">
			  <c:if test="${cli2.typeClient==7}">
				  <tr>
					<td class="vcenter"><input type="radio" name="optradio" value="E${cli2.idCli}"></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli2.raisonSociale}"/></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli2.nSiret}"/></td>
					<td style="width:30%;" class="text-left"><c:out value= "${cli2.adresse}"/> <c:out value= "${cli2.codePostal}"/> <c:out value= "${cli2.ville}"/></td>
					<td style="width:10%;" class="text-left"><c:out value= "${cli2.telephone}"/></td>
					<td style="width:20%;" class="text-left"><c:out value= "${cli2.email}"/></td>
				  </tr>
				  </c:if>				  
			  </c:forEach>
			</tbody>
		  </table>
		</form>
	</div>
  </div>	
</div>

</body>
</html>
