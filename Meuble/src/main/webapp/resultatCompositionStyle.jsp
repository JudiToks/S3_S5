<%@ page import="java.util.List" %>
<%@ page import="mg.models.Style" %>
<%@ page import="mg.models.CompositionStyle" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    CompositionStyle compoStyle = (CompositionStyle) request.getAttribute("compoStyle");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Composition d'un style de meuble</h3><hr>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Style</th>
                        <th>Matiere premiere</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int i = 0; i < compoStyle.getListMatPrem().size(); i++) { %>
                        <tr>
                            <td><%=compoStyle.getStyle()%></td>
                            <td><%=compoStyle.getListMatPrem().get(i).getNom()%></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </main>

<%@include file="layout/footer.jsp"%>