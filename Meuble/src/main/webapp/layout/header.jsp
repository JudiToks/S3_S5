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
              <a class="nav-link" href="fournisseur-servlet">Fournisseur</a>
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
              <a class="nav-link" href="prix-produit-servlet">Prix de vente produit</a>
              <a class="nav-link" href="achat-servlet">Achat matiere premiere</a>
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
              <a class="nav-link" href="benefice-produit-taille-servlet">Recherche benefice entre deux prix</a>
              <a class="nav-link" href="fabrication-servlet">Fabrication</a>
            </nav>
          </div>
          <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages3" aria-expanded="false" aria-controls="collapsePages">
            <div class="sb-nav-link-icon"><i class="fa-solid fa-people-group"></i></div>
            Fonctionnaire
            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
          </a>
          <div class="collapse" id="collapsePages3" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages3">
              <a class="nav-link" href="emp-servlet">Employee</a>
              <a class="nav-link" href="dept-servlet">Departement</a>
              <a class="nav-link" href="emp-dept-horaire-servlet">Employee departement</a>
              <a class="nav-link" href="profil-servlet">Profil</a>
              <a class="nav-link" href="dept-salaire-servlet">Salaire d'un departement</a>
              <a class="nav-link" href="emp-dept-embauche-servlet">Embaucher</a>
              <a class="nav-link" href="heure-travail-style-servlet">Heure de travail par style</a>
              <a class="nav-link" href="taille-style-dept-servlet">Nombre de dept par taille et style</a>
              <a class="nav-link" href="emp-dept-profil-status-servlet">Status employee</a>
            </nav>
          </div>
          <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages4" aria-expanded="false" aria-controls="collapsePages">
            <div class="sb-nav-link-icon"><i class="fa-solid fa-money-bill"></i></div>
            Vente
            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
          </a>
          <div class="collapse" id="collapsePages4" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages4">
              <a class="nav-link" href="genre-servlet">Genre</a>
              <a class="nav-link" href="client-servlet">Client</a>
              <a class="nav-link" href="vente-servlet">Vente</a>
              <a class="nav-link" href="statistique-vente-servlet">Statistique de vente</a>
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
