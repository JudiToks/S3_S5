<%@ page import="mg.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Insertion taille</h3><hr>
      <form action="insert-taille-servlet" method="post">
        <input class="form-control" type="text" name="taille" required><br>
        <button class="btn btn-success">Valider</button>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>