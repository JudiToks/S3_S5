<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Dept" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Dept> listDept = (List<Dept>) request.getAttribute("listDept");
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

<%@include file="layout/footer.jsp"%>