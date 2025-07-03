<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Emp_dept" %>
<%@ page import="mg.models.Profil" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Emp_dept> listEmpDept = (List<Emp_dept>) request.getAttribute("listEmpDept");
    List<Profil> listProfil = (List<Profil>) request.getAttribute("listProfil");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
    <h3>Insertion date d'embauche : </h3><hr>
    <form method="post" action="insert-emp-dept-embauche-servlet">
        <div class="row">
            <div class="col">
                <label>Personne :</label>
                <select class="form-select" name="emp_dept">
                    <option>Choose personne</option>
                    <% if (listEmpDept != null) { %>
                    <% for (int i = 0; i < listEmpDept.size(); i++) { %>
                    <option value="<%=listEmpDept.get(i).getId_emp_dept()%>"><%=listEmpDept.get(i).getNom_emp()%> | <%=listEmpDept.get(i).getNom_dept()%></option>
                    <% } %>
                    <% } %>
                </select>
            </div>
            <div class="col">
                <label>Profil :</label>
                <select class="form-select" name="profil">
                    <option>Choose profil</option>
                    <% if (listProfil != null) { %>
                    <% for (int i = 0; i < listProfil.size(); i++) { %>
                    <option value="<%=listProfil.get(i).getId_profil()%>"><%=listProfil.get(i).getNom()%></option>
                    <% } %>
                    <% } %>
                </select>
            </div>
            <div class="col">
                <label>Date d'embauche :</label>
                <input class="form-control" type="date" placeholder="Date d'embauche..." name="date" required>
            </div>
        </div><br>
        <button class="btn btn-success">Valider</button>
    </form>
</main>

<%@include file="./layout/footer.jsp"%>