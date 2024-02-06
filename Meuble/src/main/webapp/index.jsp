<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
  List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

    <main class="container">

    </main>

<%@include file="./layout/footer.jsp"%>