<%@ page import="mg.models.Style" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.models.Meuble" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Style> listStyle = (List<Style>) request.getAttribute("listStyle");
    List<Meuble> listMeuble = (List<Meuble>) request.getAttribute("listMeuble");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Creation d'un nouveau produit</h3><hr>
            <form method="post" action="insert-produit-servlet">
                <div class="row">
                    <div class="col">
                        <label>Nom du produit : </label>
                        <input class="form-control" placeholder="Nom du produit..." name="nom" type="text" required>
                    </div>
                    <div class="col">
                        <label>Meuble : </label>
                        <select class="form-select" name="meuble" required>
                            <option selected>Choose meuble</option>
                            <% for (int i = 0; i < listMeuble.size(); i++) { %>
                                <option value="<%=listMeuble.get(i).getId_meuble()%>"><%=listMeuble.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col">
                        <label>Style : </label>
                        <select class="form-select" name="style" required>
                            <option selected>Choose style</option>
                            <% for (int i = 0; i < listStyle.size(); i++) { %>
                                <option value="<%=listStyle.get(i).getId_style()%>"><%=listStyle.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>