<%@ page import="java.util.List" %>
<%@ page import="mg.models.Style" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Style> listStyle = (List<Style>) request.getAttribute("listStyle");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Composition d'un style de meuble</h3><hr>
      <form method="post" action="result-composition-style-servlet">
        <label>Style : </label>
        <select class="form-select" name="style" required>
          <option selected>Choose style</option>
          <% for (int i = 0; i < listStyle.size(); i++) { %>
            <option value="<%=listStyle.get(i).getId_style()%>"><%=listStyle.get(i).getNom()%></option>
          <% } %>
        </select><br>
        <button class="btn btn-success">Valider</button>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>