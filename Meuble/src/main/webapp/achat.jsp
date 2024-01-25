<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Fournisseur" %>
<%@ page import="mg.models.Matiere_premiere" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Fournisseur> listFournisseur = (List<Fournisseur>) request.getAttribute("listFournisseur");
    List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
%>

<%@include file="layout/header.jsp"%>

<main class="container">
    <h3>Achat de matiere premiere :</h3><hr>
    <form method="post" action="insert-achat-servlet">
        <div class="row">
            <div class="col">
                <label>Fournisseur : </label>
                <select class="form-select" name="fournisseur" required>
                    <option selected>Choose fournisseur</option>
                    <% for (int i = 0; i < listFournisseur.size(); i++) { %>
                    <option value="<%=listFournisseur.get(i).getId_fournisseur()%>"><%=listFournisseur.get(i).getNom()%></option>
                    <% } %>
                </select>
            </div>
            <div class="col">
                <label>Matiere premiere : </label>
                <select class="form-select" name="matprem" required>
                    <option selected>Choose matiere premiere</option>
                    <% for (int i = 0; i < listMatPrem.size(); i++) { %>
                    <option value="<%=listMatPrem.get(i).getId_matiere_premiere()%>"><%=listMatPrem.get(i).getNom()%></option>
                    <% } %>
                </select>
            </div>
            <div class="col">
                <label>Quantite : </label>
                <input class="form-control" placeholder="Quantite..." name="qte" type="number" required>
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