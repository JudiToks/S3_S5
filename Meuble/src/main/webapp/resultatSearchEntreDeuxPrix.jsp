<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.ResultRechercheMatPrem" %>
<%@ page import="mg.models.ResultRechercheEntreDeuxPrix" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<ResultRechercheEntreDeuxPrix> searchList = (List<ResultRechercheEntreDeuxPrix>) request.getAttribute("listSearch");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Meuble</title>
    <link href="assets/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand ps-3" href="index.html">Meuble</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
        </div>
    </form>
    <%--  <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">--%>
    <%--    <li class="nav-item dropdown">--%>
    <%--      <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>--%>
    <%--      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">--%>
    <%--        <li><a class="dropdown-item" href="#!">Settings</a></li>--%>
    <%--        <li><a class="dropdown-item" href="#!">Activity Log</a></li>--%>
    <%--        <li><hr class="dropdown-divider" /></li>--%>
    <%--        <li><a class="dropdown-item" href="#!">Logout</a></li>--%>
    <%--      </ul>--%>
    <%--    </li>--%>
    <%--  </ul>--%>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">Core</div>
                    <a class="nav-link" href="index-servlet">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-house"></i></div>
                        Home
                    </a>
                    <div class="sb-sidenav-menu-heading">Insertion</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-bed"></i></div>
                        Meuble
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="meuble-servlet">Type meuble</a>
                            <a class="nav-link" href="matPremiere-servlet">Matiere premiere</a>
                            <a class="nav-link" href="style-servlet">Style de meuble</a>
                            <a class="nav-link" href="taille-servlet">Taille</a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-plus"></i></div>
                        Details meuble
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                            <a class="nav-link" href="styleMatPremiere-servlet">Style matiere premiere</a>
                            <a class="nav-link" href="prix-matPrem-servlet">Prix matiere premiere</a>
                            <a class="nav-link" href="produit-servlet">Produit</a>
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">Details du produit</a>
                        </nav>
                    </div>
                    <div class="sb-sidenav-menu-heading">Features</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages2" aria-expanded="false" aria-controls="collapsePages2">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-gears"></i></div>
                        Fonctionnalite
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapsePages2" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages2">
                            <a class="nav-link" href="composition-style-servlet">Composition style</a>
                            <a class="nav-link" href="recherche-mat-prem-servlet">Recherche produit mat prem</a>
                            <a class="nav-link" href="recherche_entre_deux_prix_servlet">Recherche entre deux prix</a>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <br>
        <main class="container">
            <h4>Resultats du recherche :</h4><hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Produit</th>
                    <th>Prix</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < searchList.size(); i++) { %>
                <tr>
                    <td><%=searchList.get(i).getNom()%></td>
                    <td><%=searchList.get(i).getPrix()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <%--  modal  --%>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>