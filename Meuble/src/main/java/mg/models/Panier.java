package mg.models;

import java.sql.*;

public class Panier
{
    int id_panier;
    int id_client;
    int etat;
    String Client;

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

//    function
    public void insert(Connection connection) throws SQLException {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_client()+", 0);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("Insertion "+this.getClass().getSimpleName()+" issues");
            e.printStackTrace();
        }
        finally
        {
            if (isOuvert)
            {
                connection.close();
            }
        }
    }

    public static Panier getPanierByIdClient(Connection connection, int id_client)
    {
        boolean isOuvert = false;
        Panier valiny = new Panier();
        String query = "select * from panier where id_client = "+id_client+" and etat = 0;";
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
                Panier temp = new Panier();
                temp.setId_panier(resultSet.getInt(1));
                temp.setId_client(resultSet.getInt(2));
                temp.setEtat(resultSet.getInt(3));
                valiny = temp;
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
            System.out.println("Panier getPanierByIdClient issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static void updateEtat(Connection connection, int id_panier)
    {
        boolean isOuvert = false;
        String query = "update panier set etat = 10 where id_panier = "+id_panier+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            int valiny = statement.executeUpdate(query);
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("Panier updateEtat issues");
            e.printStackTrace();
        }
    }
}
