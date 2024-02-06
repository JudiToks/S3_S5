<%@ page import="mg.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Style" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Style> listStyle = (List<Style>) request.getAttribute("listStyle");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Insertion style</h3><hr>
      <form action="insert-style-servlet" method="post">
        <input class="form-control" type="text" name="style" required><br>
        <button class="btn btn-success">Valider</button>
      </form><hr>
      <h4>Liste des styles :</h4><hr>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Numero</th>
            <th>Style</th>
          </tr>
        </thead>
        <tbody>
          <% for (int i = 0; i < listStyle.size(); i++) { %>
            <tr>
              <td><%=listStyle.get(i).getId_style()%></td>
              <td><%=listStyle.get(i).getNom()%></td>
            </tr>
          <% } %>
        </tbody>
      </table>
    </main>

<%@include file="layout/footer.jsp"%>