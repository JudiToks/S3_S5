package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dept
{
    int id_dept;
    String nom;

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void insert(Connection connection) throws SQLException {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, '"+this.getNom()+"');";
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

    public static List<Dept> getAllDept(Connection connection)
    {
        boolean isOuvert = false;
        List<Dept> valiny = new ArrayList<>();
        String query = "select * from dept;";
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
                Dept temp = new Dept();
                temp.setId_dept(resultSet.getInt("id_dept"));
                temp.setNom(resultSet.getString("nom"));
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
            System.out.println("Dept getAllDept issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Dept getDeptById(Connection connection, int id_dept)
    {
        boolean isOuvert = false;
        Dept valiny = new Dept();
        String query = "select * from dept where id_dept = "+id_dept+";";
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
                Dept temp = new Dept();
                temp.setId_dept(resultSet.getInt(1));
                temp.setNom(resultSet.getString(2));
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
            System.out.println("Dept getDeptById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
