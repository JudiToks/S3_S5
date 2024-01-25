<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
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

<%--      modal     --%>
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