<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Dept" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Dept> listDept = (List<Dept>) request.getAttribute("listDept");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion salaire d'un departement : </h3><hr>
            <form action="insert-dept-salaire-servlet" method="post">
                <div class="row">

                    <div class="col">
                        <label>Departement : </label>
                        <select class="form-select" name="dept" required>
                            <option selected>Choose employee</option>
                            <% for (int i = 0; i < listDept.size(); i++) { %>
                            <option value="<%=listDept.get(i).getId_dept()%>"><%=listDept.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label>Salaire : </label>
                        <input class="form-control" type="number" name="salaire" required>
                    </div>

                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>