<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
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
      <h3>Insertion prix d'une matiere premiere :</h3><hr>
      <form method="post" action="insert-prix-matPrem-servlet">
        <div class="row">
          <div class="col">
            <label>Matiere premiere : </label>
            <select class="form-select" name="matPrem" required>
              <option selected>Choose meuble</option>
              <% for (int i = 0; i < listMatPrem.size(); i++) { %>
                <option value="<%=listMatPrem.get(i).getId_matiere_premiere()%>"><%=listMatPrem.get(i).getNom()%></option>
              <% } %>
            </select>
          </div>
          <div class="col">
            <label>Prix : </label>
            <input class="form-control" placeholder="Prix unitaire..." name="prix" type="number" required>
          </div>
        </div>
        <br>
        <button class="btn btn-success">Valider</button>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>