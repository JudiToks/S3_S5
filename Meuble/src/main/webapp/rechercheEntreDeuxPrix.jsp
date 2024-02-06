<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Recherche entre deux prix : </h3><hr>
      <form action="search_entre_deux_prix_servlet" method="get">
        <div class="row">
          <div class="col">
            <label for="prixUn">Prix 1 :</label>
            <input id="prixUn" class="form-control" type="text" name="prixun" required>
          </div>
          <div class="col">
            <label for="prixDeux">Prix 2 :</label>
            <input id="prixDeux" class="form-control" type="number" name="prixdeux" required>
          </div>
        </div><br>
        <button class="btn btn-success">Search</button>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>