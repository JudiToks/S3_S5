<%@ page import="mg.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Matiere_premiere" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Insertion matiere premiere </h3><hr>
      <form action="insert-matprem-servlet" method="post">
        <label for="matierePremName">Matiere premiere :</label>
        <input id="matierePremName" class="form-control" type="text" name="matiere" required><br>
        <button class="btn btn-success">Valider</button>
      </form><hr>
      <h4>Liste des matieres premieres :</h4><hr>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Numero</th>
          <th>Meuble</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < listMatPrem.size(); i++) { %>
        <tr>
          <td><%=listMatPrem.get(i).getId_matiere_premiere()%></td>
          <td><%=listMatPrem.get(i).getNom()%></td>
        </tr>
        <% } %>
        </tbody>
      </table>
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

<%@include file="layout/footer.jsp"%>
