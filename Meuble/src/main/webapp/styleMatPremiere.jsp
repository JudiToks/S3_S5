<%@ page import="java.util.List" %>
<%@ page import="mg.models.Style" %>
<%@ page import="mg.models.Matiere_premiere" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Style> listStyle = (List<Style>) request.getAttribute("listStyle");
    List<Matiere_premiere> listMatPrem = (List<Matiere_premiere>) request.getAttribute("listMatPrem");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Insertion style matiere premiere</h3><hr>
            <form action="insert-stylematprem-servlet" method="post">
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
                        <label>Matiere Premiere : </label>
                        <select class="form-select" name="matprem" required>
                            <option selected>Choose Matiere premiere</option>
                            <% for (int i = 0; i < listMatPrem.size(); i++) { %>
                                <option value="<%=listMatPrem.get(i).getId_matiere_premiere()%>"><%=listMatPrem.get(i).getNom()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <br>
                <button class="btn btn-success">Valider</button>
            </form>
        </main>

<%@include file="layout/footer.jsp"%>