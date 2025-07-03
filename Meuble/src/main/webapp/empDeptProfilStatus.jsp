<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.TempStatusEmp" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<TempStatusEmp> listStatus = (List<TempStatusEmp>) request.getAttribute("listStatus");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
    <h3>Status personne avec taux horaire :</h3><hr>
    <form method="get" action="emp-dept-profil-status-servlet">
        <div class="row">
            <div class="col">
                <input type="date" name="recherche_date">
            </div>
            <div class="col">
                <input type="text" name="recherche_text">
            </div>
            <div class="col">
                <button class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Employee</th>
            <th>Departement</th>
            <th>Status</th>
            <th>Salaire</th>
        </tr>
        </thead>
        <tbody>
        <% if (listStatus.size() != 0) { %>
        <% for (int i = 0; i < listStatus.size(); i++) { %>
        <tr>
            <td><%=listStatus.get(i).getNom_emp()%></td>
            <td><%=listStatus.get(i).getNom_dept()%></td>
            <td><%=listStatus.get(i).getProfil()%></td>
            <td><%=listStatus.get(i).getSalaire_calcul()%></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
</main>

<%@include file="./layout/footer.jsp"%>