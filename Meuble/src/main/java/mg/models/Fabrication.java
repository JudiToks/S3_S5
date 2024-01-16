package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Fabrication
{
    int id_matiere_premiere;
    String matiere_premiere;
    double demande;
    double stock_actuel;
    String disponibilite;

    public String getMatiere_premiere() {
        return matiere_premiere;
    }

    public void setMatiere_premiere(String matiere_premiere) {
        this.matiere_premiere = matiere_premiere;
    }

    public int getId_matiere_premiere() {
        return id_matiere_premiere;
    }

    public void setId_matiere_premiere(int id_matiere_premiere) {
        this.id_matiere_premiere = id_matiere_premiere;
    }

    public double getDemande() {
        return demande;
    }

    public void setDemande(double demande) {
        this.demande = demande;
    }

    public double getStock_actuel() {
        return stock_actuel;
    }

    public void setStock_actuel(double stock_actuel) {
        this.stock_actuel = stock_actuel;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public static List<Fabrication> getPresFabrication(Connection connection, int id_produit, double qte)
    {
        boolean isOuvert = false;
        List<Fabrication> valiny = new ArrayList<>();
        String query = "-- Calculer la demande pour chaque matière première nécessaire pour fabriquer 5 unités du produit 1\n" +
                "WITH DemandeMatiere AS (\n" +
                "    SELECT\n" +
                "        id_matiere_premiere,\n" +
                "        SUM(qte * "+qte+") AS demande\n" +
                "    FROM details_produit\n" +
                "    WHERE id_produit = "+id_produit+"\n" +
                "    GROUP BY id_matiere_premiere\n" +
                ")\n" +
                "\n" +
                "-- Vérifier la disponibilité de matière première\n" +
                "SELECT\n" +
                "    e.id_matiere_premiere,\n" +
                "    e.demande,\n" +
                "    s.stock_actuel,\n" +
                "    CASE\n" +
                "        WHEN e.demande <= s.stock_actuel THEN 'Ampy ny matiere premiere'\n" +
                "        ELSE 'Tsy ampy - Manque ' || (e.demande - s.stock_actuel) || ' unité(s)'\n" +
                "        END AS disponibilite\n" +
                "FROM DemandeMatiere e\n" +
                "         JOIN v_etat_stock s ON e.id_matiere_premiere = s.id_matiere_premiere;";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Fabrication temp = new Fabrication();
                temp.setId_matiere_premiere(resultSet.getInt(1));
                temp.setMatiere_premiere(Matiere_premiere.getMatierePremiereById(connection, temp.getId_matiere_premiere()).getNom());
                temp.setDemande(resultSet.getDouble(2));
                temp.setStock_actuel(resultSet.getDouble(3));
                temp.setDisponibilite(resultSet.getString(4));
                valiny.add(temp);
            }
            resultSet.close();
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("Fabrication getPresFabrication issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
