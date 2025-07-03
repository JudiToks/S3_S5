<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Taille" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Taille> listTaille = (List<Taille>) request.getAttribute("listTaille");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion prix horaire d'un employee par departement </h3><hr>
            <form action="insert-prix-produit-servlet" method="post">
                <div class="row">

                    <div class="col">
                        <label>Produit : </label>
                        <select class="form-select" name="produit" required>
                            <option selected>Choose produit</option>
                            <% for (int i = 0; i < listProduit.size(); i++) { %>
                            <option value="<%=listProduit.get(i).getId_produit()%>"><%=listProduit.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label>Taille : </label>
                        <select class="form-select" name="taille" required>
                            <option selected>Choose taille</option>
                            <% for (int i = 0; i < listTaille.size(); i++) { %>
                            <option value="<%=listTaille.get(i).getId_taille()%>"><%=listTaille.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label>Prix du produit : </label>
                        <input class="form-control" type="number" name="prix" required>
                    </div>

                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>