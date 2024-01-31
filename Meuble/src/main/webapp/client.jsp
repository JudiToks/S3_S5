<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Genre" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Genre> listGenre = (List<Genre>) request.getAttribute("listGenre");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
    <h3>Insertion nouveau client : </h3><hr>
    <form method="post" action="insert-client-servlet">
        <div class="row">
            <div class="col">
                <label>Nom :</label>
                <input class="form-control" type="text" placeholder="Nom" name="nom" required><br>
                <label>Date de naissance :</label>
                <input class="form-control" type="date" name="date" required>
            </div>
            <div class="col">
                <label>Prenom :</label>
                <input class="form-control" type="text" placeholder="Prenom" name="prenom" required><br>
                <label>Genre :</label>
                <select class="form-select" name="genre" required>
                    <option selected>Choose genre</option>
                    <% for (int i = 0; i < listGenre.size(); i++) { %>
                    <option value="<%=listGenre.get(i).getId_genre()%>"><%=listGenre.get(i).getNom()%></option>
                    <% } %>
                </select>
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