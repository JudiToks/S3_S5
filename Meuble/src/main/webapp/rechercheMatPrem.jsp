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
      <h3>Recherche des produits utilisant une matiere premiere :</h3><hr>
      <form method="get" action="search-servlet">
        <div class="row">
          <div class="col-10">
            <input class="form-control" type="text" placeholder="Matiere premiere a chercher" name="search" required><br>
          </div>
          <div class="col-2">
            <button style="width: 100%" class="btn btn-primary"><i class="fa-solid fa-magnifying-glass"></i> Search</button>
          </div>
        </div>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>