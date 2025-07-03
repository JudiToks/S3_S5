<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.ResultRechercheMatPrem" %>
<%@ page import="mg.models.ResultRechercheEntreDeuxPrix" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<ResultRechercheEntreDeuxPrix> searchList = (List<ResultRechercheEntreDeuxPrix>) request.getAttribute("listSearch");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h4>Resultats du recherche :</h4><hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Produit</th>
                    <th>Prix</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < searchList.size(); i++) { %>
                <tr>
                    <td><%=searchList.get(i).getNom()%></td>
                    <td><%=searchList.get(i).getPrix()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </main>

<%@include file="layout/footer.jsp"%>