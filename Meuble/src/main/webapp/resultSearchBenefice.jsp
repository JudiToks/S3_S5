<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.BeneficeProduitTaille" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<BeneficeProduitTaille> listSearch = (List<BeneficeProduitTaille>) request.getAttribute("listSearch");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
  <h4>Resultats du recherche :</h4><hr>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Produit</th>
      <th>Taille</th>
      <th>Benefice</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < listSearch.size(); i++) { %>
    <tr>
      <td><%=listSearch.get(i).getProduit()%></td>
      <td><%=listSearch.get(i).getTaille()%></td>
      <td><%=listSearch.get(i).getBenefice()%></td>
    </tr>
    <% } %>
    </tbody>
  </table>
</main>

<%@include file="./layout/footer.jsp"%>