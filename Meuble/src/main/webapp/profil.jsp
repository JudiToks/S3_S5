<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
  <h3>Insertion profil : </h3><hr>
  <form method="post" action="insert-profil-servlet">
    <div class="row">
      <div class="col">
        <label>Nom :</label>
        <input class="form-control" type="text" placeholder="Nom" name="nom" required>
      </div>
      <div class="col">
        <label>Annee de travail :</label>
        <input class="form-control" type="number" placeholder="Annee de travail" name="annee" required>
      </div>
      <div class="col">
        <label>Coefficient :</label>
        <input class="form-control" type="number" placeholder="Coefficient" name="coeff" required>
      </div>
    </div><br>
    <button class="btn btn-success">Valider</button>
  </form>
</main>

<%@include file="./layout/footer.jsp"%>