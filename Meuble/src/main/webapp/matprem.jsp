<%@ page import="mg.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Matiere_premiere" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
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
    </main>

<%@include file="layout/footer.jsp"%>
