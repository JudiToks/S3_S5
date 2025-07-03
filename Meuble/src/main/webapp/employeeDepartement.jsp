<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Emp" %>
<%@ page import="mg.models.Dept" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Emp> listEmp = (List<Emp>) request.getAttribute("listEmp");
    List<Dept> listDept = (List<Dept>) request.getAttribute("listDept");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion prix horaire d'un employee par departement </h3><hr>
            <form action="insert-emp-dept-servlet" method="post">
                <div class="row">

                    <div class="col">
                        <label>Employee : </label>
                        <select class="form-select" name="emp" required>
                            <option selected>Choose employee</option>
                            <% for (int i = 0; i < listEmp.size(); i++) { %>
                            <option value="<%=listEmp.get(i).getId_emp()%>"><%=listEmp.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label>Departement : </label>
                        <select class="form-select" name="dept" required>
                            <option selected>Choose departement</option>
                            <% for (int i = 0; i < listDept.size(); i++) { %>
                            <option value="<%=listDept.get(i).getId_dept()%>"><%=listDept.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>