<%@ page import="mg.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Meuble" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Meuble> listMeuble = (List<Meuble>) request.getAttribute("listMeuble");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion meuble</h3><hr>
            <form method="post" action="insertion-meuble-servlet">
                <input class="form-control" type="text" placeholder="Nom" name="nom" required><br>
                <button class="btn btn-success">Valider</button>
            </form><hr>
            <h4>Liste des meubles :</h4><hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Numero</th>
                    <th>Meuble</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < listMeuble.size(); i++) { %>
                <tr>
                    <td><%=listMeuble.get(i).getId_meuble()%></td>
                    <td><%=listMeuble.get(i).getNom()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </main>

<%@include file="layout/footer.jsp"%>