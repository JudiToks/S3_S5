<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Genre" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Genre> listGenre = (List<Genre>) request.getAttribute("listGenre");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
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
</main>

<%@include file="./layout/footer.jsp"%>