<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Emp" %>
<%@ page import="mg.models.Dept" %>
<%@ page import="mg.models.Style" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Style> listStyle = (List<Style>) request.getAttribute("listStyle");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion prix horaire d'un employee par departement </h3><hr>
            <form action="insert-produit-emp-horaire-servlet" method="post">
                <div class="row">

                    <div class="col">
                        <label>Style : </label>
                        <select class="form-select" name="style" required>
                            <option selected>Choose style</option>
                            <% for (int i = 0; i < listStyle.size(); i++) { %>
                            <option value="<%=listStyle.get(i).getId_style()%>"><%=listStyle.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col">
                        <label>Heure de travail : </label>
                        <input class="form-control" type="time" name="heure" required>
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