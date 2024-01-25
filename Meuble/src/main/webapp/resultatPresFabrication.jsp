<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.Fabrication" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<Fabrication> listFabrication = (List<Fabrication>) request.getAttribute("listFabrication");
%>

<%@include file="layout/header.jsp"%>

        <main class="container">
            <h3>Pres fabrication :</h3><hr>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Matiere premiere</th>
                    <th>Demande</th>
                    <th>Stock actuel</th>
                    <th>Disponibilite</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < listFabrication.size(); i++) { %>
                <tr>
                    <td><%=listFabrication.get(i).getMatiere_premiere()%></td>
                    <td><%=listFabrication.get(i).getDemande()%></td>
                    <td><%=listFabrication.get(i).getStock_actuel()%></td>
                    <td><%=listFabrication.get(i).getDisponibilite()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
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