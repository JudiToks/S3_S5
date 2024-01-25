<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.TempStatusEmp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<TempStatusEmp> listStatus = (List<TempStatusEmp>) request.getAttribute("listStatus");
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

<%@include file="./layout/footer.jsp"%>