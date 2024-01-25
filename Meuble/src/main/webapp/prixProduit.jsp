<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Taille" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Taille> listTaille = (List<Taille>) request.getAttribute("listTaille");
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