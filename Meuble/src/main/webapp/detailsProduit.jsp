<%@ page import="java.util.List" %>
<%@ page import="mg.models.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  Produit produit = (Produit) request.getAttribute("produit");
  List<Taille> listTaille = (List<Taille>) request.getAttribute("listTaille");
  List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

    <main class="container">
      <h3>Formule details</h3><hr>
      <form action="insert-details-produit-servlet" method="post">
        <div class="row">

          <div class="col">
            <label>Meuble : </label>
            <input class="form-control" type="text" value="<%=produit.getNom()%>">
            <input type="hidden" value="<%=produit.getId_produit()%>" name="id_produit">
            <br>
            <label>Matiere Premiere : </label>
            <% for (int i = 0; i < listMatPrem.size(); i++) { %>
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="<%=listMatPrem.get(i).getId_matiere_premiere()%>" id="flexCheckDefault" name="matPrem">
              <label class="form-check-label" for="flexCheckDefault">
                <%=listMatPrem.get(i).getNom()%>
              </label>
            </div>
            <% } %>
          </div>

          <div class="col">
            <label>Taille : </label>
            <select class="form-select" name="taille" required>
              <option selected>Choose taille</option>
              <% for (int i = 0; i < listTaille.size(); i++) { %>
              <option value="<%=listTaille.get(i).getId_taille()%>"><%=listTaille.get(i).getNom()%></option>
              <% } %>
            </select>
            <br>
            <label>Quantite : </label>
            <% for (int i = 0; i < listMatPrem.size(); i++) { %>
              <div class="row">
                <div class="col-3">
                  <%=listMatPrem.get(i).getNom()%>:
                </div>
                <div class="col">
                  <input class="form-control" type="number" name="qte"><br>
                </div>
              </div>
            <% } %>
          </div>

        </div>
        <br>
        <button class="btn btn-success">Valider</button>
      </form>
    </main>

<%@include file="layout/footer.jsp"%>