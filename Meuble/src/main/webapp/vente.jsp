<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Genre" %>
<%@ page import="mg.models.Client" %>
<%@ page import="mg.models.Details_panier" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
    List<Details_panier> listDp = (List<Details_panier>) request.getAttribute("listDp");
    int id_client = (int) session.getAttribute("id_client");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
    <h3>Insertion Vente : </h3><hr>
    <form method="post" action="insert-vente-servlet">
        <div class="row">
            <div class="col">
                <label>Produit :</label>
                <select class="form-select" name="produit" required>
                    <option selected>Choose produit</option>
                    <% for (int i = 0; i < listProduit.size(); i++) { %>
                    <option value="<%=listProduit.get(i).getId_produit()%>"><%=listProduit.get(i).getNom()%></option>
                    <% } %>
                </select>
            </div>
            <input type="hidden" name="client" class="form-control" value="<%=id_client%>">
            <div class="col">
                <label>Quantite :</label>
                <input class="form-control" type="number" placeholder="Nombre..." name="nombre" required>
            </div>

        </div><br>
        <button class="btn btn-success">Valider</button>
    </form><hr>
    <div class="row">
        <div class="col">
            <h4>Mon panier :</h4>
        </div>
        <div class="col">
            <div style="float: right">
                <form method="post" action="validation-vente-servlet">
                    <input type="hidden" name="client" class="form-control" value="<%=id_client%>">
                    <button class="btn btn-primary">Valider mon panier</button>
                </form>
            </div>
        </div>
    </div>
    <hr>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Produit</th>
            <th>Quantite</th>
        </tr>
        </thead>
        <tbody>
        <% if (listDp != null) { for (int i = 0; i < listDp.size(); i++) { %>
        <tr>
            <td><%=listDp.get(i).getProduit()%></td>
            <td><%=listDp.get(i).getQuantite()%></td>
        </tr>
        <% } } %>
        </tbody>
    </table>
</main>

<%@include file="./layout/footer.jsp"%>