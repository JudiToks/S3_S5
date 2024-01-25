<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
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
  <form method="get" action="details-produit-servlet">
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Choix du produit</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <select class="form-select" name="produit">
              <option>Choose product</option>
              <% if (listProduit != null) { %>
              <% for (int i = 0; i < listProduit.size(); i++) { %>
              <option value="<%=listProduit.get(i).getId_produit()%>"><%=listProduit.get(i).getNom()%></option>
              <% } %>
              <% } %>
            </select>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button class="btn btn-primary">Valider</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</main>

<%@include file="./layout/footer.jsp"%>