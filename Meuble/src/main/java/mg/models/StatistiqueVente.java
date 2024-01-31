package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatistiqueVente
{
    String produit;
    String genre;
    int nombre;
    double statistique;

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getStatistique() {
        return statistique;
    }

    public void setStatistique(double statistique) {
        this.statistique = statistique;
    }

    public static List<StatistiqueVente> getAllStatistique(Connection connection)
    {
        boolean isOuvert = false;
        List<StatistiqueVente> valiny = new ArrayList<>();
        String query = "SELECT\n" +
                "    p.id_produit,\n" +
                "    p.nom AS nom_produit,\n" +
                "    g.nom AS nom_genre,\n" +
                "    sum(nbr) AS nombre_ventes\n" +
                "FROM\n" +
                "    vente v\n" +
                "        JOIN produit p ON v.id_produit = p.id_produit\n" +
                "        JOIN client c ON v.id_client = c.id_client\n" +
                "        JOIN genre g ON c.id_genre = g.id_genre\n" +
                "GROUP BY\n" +
                "    p.id_produit, g.id_genre, nbr\n" +
                "ORDER BY\n" +
                "    p.id_produit, g.id_genre;";
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
                StatistiqueVente temp = new StatistiqueVente();
                temp.setProduit(resultSet.getString(2));
                temp.setGenre(resultSet.getString(3));
                temp.setNombre(resultSet.getInt(4));
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
            System.out.println("StatistiqueVente getAllStatistique issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<StatistiqueVente> getAllStatistiqueVente(Connection connection, List<StatistiqueVente> statistiqueVenteList)
    {
        List<StatistiqueVente> valiny = new ArrayList<>();
        int nombre_total = 0;
        for (int i = 0; i < statistiqueVenteList.size(); i++)
        {
            nombre_total = nombre_total + statistiqueVenteList.get(i).getNombre();
        }
        for (int i = 0; i < statistiqueVenteList.size(); i++)
        {
            StatistiqueVente temp = new StatistiqueVente();
            temp.setProduit(statistiqueVenteList.get(i).getProduit());
            temp.setGenre(statistiqueVenteList.get(i).getGenre());
            temp.setNombre(statistiqueVenteList.get(i).getNombre());
            temp.setStatistique((statistiqueVenteList.get(i).getNombre() * 100 ) / nombre_total);
            valiny.add(temp);
        }
        return valiny;
    }

    public static List<StatistiqueVente> getAllStatistiquePieForm(Connection connection)
    {
        boolean isOuvert = false;
        List<StatistiqueVente> valiny = new ArrayList<>();
        String query = "with list_genre_nbr as (\n" +
                "    SELECT\n" +
                "        g.id_genre,\n" +
                "        g.nom,\n" +
                "        sum(nbr) AS nombre_ventes\n" +
                "    FROM\n" +
                "        vente v\n" +
                "            JOIN produit p ON v.id_produit = p.id_produit\n" +
                "            JOIN client c ON v.id_client = c.id_client\n" +
                "            JOIN genre g ON c.id_genre = g.id_genre\n" +
                "    GROUP BY\n" +
                "        g.id_genre, nbr\n" +
                "    ORDER BY\n" +
                "        g.id_genre\n" +
                ")\n" +
                "select\n" +
                "    id_genre,\n" +
                "    nom,\n" +
                "    sum(nombre_ventes)\n" +
                "from\n" +
                "    list_genre_nbr\n" +
                "group by id_genre, nom\n" +
                "order by id_genre;";
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
                StatistiqueVente temp = new StatistiqueVente();
                temp.setGenre(resultSet.getString(2));
                temp.setNombre(resultSet.getInt(3));
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
            System.out.println("StatistiqueVente getAllStatistiquePieForm issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
