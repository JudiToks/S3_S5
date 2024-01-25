package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Emp
{
    int id_emp;
    String nom;

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
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

    public static List<Emp> getAllEmp(Connection connection)
    {
        boolean isOuvert = false;
        List<Emp> valiny = new ArrayList<>();
        String query = "select * from emp;";
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
                Emp temp = new Emp();
                temp.setId_emp(resultSet.getInt("id_emp"));
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
            System.out.println("Emp getAllEmp issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Emp getEmpById(Connection connection, int id_emp)
    {
        boolean isOuvert = false;
        Emp valiny = new Emp();
        String query = "select * from emp where id_emp = "+id_emp+";";
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
                Emp temp = new Emp();
                temp.setId_emp(resultSet.getInt(1));
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
            System.out.println("Emp getEmpById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
