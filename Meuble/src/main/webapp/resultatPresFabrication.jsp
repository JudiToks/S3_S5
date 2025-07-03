<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Fabrication" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Fabrication> listFabrication = (List<Fabrication>) request.getAttribute("listFabrication");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Pres fabrication :</h3><hr>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Matiere premiere</th>
                    <th>Demande</th>
                    <th>Stock actuel</th>
                    <th>Disponibilite</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < listFabrication.size(); i++) { %>
                <tr>
                    <td><%=listFabrication.get(i).getMatiere_premiere()%></td>
                    <td><%=listFabrication.get(i).getDemande()%></td>
                    <td><%=listFabrication.get(i).getStock_actuel()%></td>
                    <td><%=listFabrication.get(i).getDisponibilite()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </main>

<%@include file="layout/footer.jsp"%>