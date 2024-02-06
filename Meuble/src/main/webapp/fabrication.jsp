<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Pres fabriquer :</h3><hr>
            <form method="post" action="fabriquer-servlet">
                <div class="row">
                    <div class="col">
                        <label>Produit : </label>
                        <select class="form-select" name="produit" required>
                            <option selected>Choose fournisseur</option>
                            <% for (int i = 0; i < listProduit.size(); i++) { %>
                            <option value="<%=listProduit.get(i).getId_produit()%>"><%=listProduit.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <label>Quantite : </label>
                        <input class="form-control" type="number" placeholder="Quantite" name="qte" required><br>
                    </div>
                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>