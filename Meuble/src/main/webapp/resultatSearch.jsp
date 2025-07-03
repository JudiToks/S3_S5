<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.ResultRechercheMatPrem" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<ResultRechercheMatPrem> searchList = (List<ResultRechercheMatPrem>) request.getAttribute("listSearch");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h4>Resultats du recherche :</h4><hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Produit</th>
                    <th>Taille</th>
                    <th>Matiere premiere</th>
                    <th>Quantite</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < searchList.size(); i++) { %>
                <tr>
                    <td><%=searchList.get(i).getNom_produit()%></td>
                    <td><%=searchList.get(i).getTaille()%></td>
                    <td><%=searchList.get(i).getMat_prem()%></td>
                    <td><%=searchList.get(i).getQte()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </main>

<%@include file="layout/footer.jsp"%>