<footer class="py-4 bg-light mt-auto">
    <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">
            <div class="text-muted">Copyright &copy; Your Website 2023</div>
            <div>
                <a href="#">Privacy Policy</a>
                &middot;
                <a href="#">Terms &amp; Conditions</a>
            </div>
        </div>
    </div>
</footer>
</div>
</div>

<%--      produit modal     --%>
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

<%--      panier modal     --%>
<form method="get" action="vente-servlet">
    <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel1">Choix du client</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <select class="form-select" name="client" required>
                        <option selected>Choose client</option>
                        <% if (listClient != null) { for (int i = 0; i < listClient.size(); i++) { %>
                        <option value="<%=listClient.get(i).getId_client()%>"><%=listClient.get(i).getNom()%> | <%=listClient.get(i).getPrenom()%></option>
                        <% } } %>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="assets/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/js/chart.js"></script>
</body>
</html>
