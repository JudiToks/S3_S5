<%@ page import="java.util.List" %>
<%@ page import="mg.models.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Fournisseur> listFournisseur = (List<Fournisseur>) request.getAttribute("listFournisseur");
    List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
    List<Achat> stock = (List<Achat>) request.getAttribute("stock");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
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
    </form><hr>
    <h4>Etat de stock :</h4><hr>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Matiere premiere</th>
            <th>Quantite</th>
        </tr>
        </thead>
        <tbody>
        <% if (stock != null) { for (int i = 0; i < stock.size(); i++) { %>
        <tr>
            <td><%=stock.get(i).getMat_prem()%></td>
            <td><%=stock.get(i).getQte()%></td>
        </tr>
        <% } } %>
        </tbody>
    </table>
</main>

<%@include file="layout/footer.jsp"%>