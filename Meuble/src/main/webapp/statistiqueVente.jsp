<%@ page import="java.util.List" %>
<%@ page import="mg.models.Produit" %>
<%@ page import="mg.models.StatistiqueVente" %>
<%@ page import="mg.models.Client" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Produit> listProduit = (List<Produit>) request.getAttribute("listProduit");
    List<StatistiqueVente> listStat = (List<StatistiqueVente>) request.getAttribute("listStat");
    List<StatistiqueVente> listStatPie = (List<StatistiqueVente>) request.getAttribute("listStatPie");
    List<Client> listClient = (List<Client>) request.getAttribute("listClient");
%>

<%@include file="./layout/header.jsp"%>

<main class="container">
    <h3>Statistique de vente :</h3><hr>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Produit</th>
            <th>Genre</th>
            <th>Quantite</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < listStat.size(); i++) { %>
        <tr>
            <td><%=listStat.get(i).getProduit()%></td>
            <td><%=listStat.get(i).getGenre()%></td>
            <td><%=listStat.get(i).getNombre()%></td>
        </tr>
        <% } %>
        </tbody>
    </table><br>
    <div class="col-lg-6">
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-chart-pie me-1"></i>
                Chart en fromage
            </div>
            <div class="card-body"><canvas id="myPieChart" width="100%" height="50"></canvas></div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
    </div>
</main>

<%@include file="./layout/footer.jsp"%>

<script>
    // Set new default font family and font color to mimic Bootstrap's default styling
    Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#292b2c';

    // Pie Chart Example
    var homme = <%=listStatPie.get(0).getStatistique()%>
    var femme = <%=listStatPie.get(1).getStatistique()%>
    var ctx = document.getElementById("myPieChart");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["Homme", "Femme"],
            datasets: [{
                data: [homme, femme],
                backgroundColor: ['#007bff', '#ffc107'],
            }],
        },
    });
</script>