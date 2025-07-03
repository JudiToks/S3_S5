package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Emp_dept
{
    int id_emp_dept;
    int id_emp;
    String nom_emp;
    int id_dept;
    String nom_dept;

    public String getNom_emp() {
        return nom_emp;
    }

    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public String getNom_dept() {
        return nom_dept;
    }

    public void setNom_dept(String nom_dept) {
        this.nom_dept = nom_dept;
    }

    public int getId_emp_dept() {
        return id_emp_dept;
    }

    public void setId_emp_dept(int id_emp_dept) {
        this.id_emp_dept = id_emp_dept;
    }

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_emp()+", "+this.getId_dept()+");";
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

    public static List<Emp_dept> getAllEmpDept(Connection connection)
    {
        boolean isOuvert = false;
        List<Emp_dept> valiny = new ArrayList<>();
        String query = "select * from emp_dept;";
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
                Emp_dept temp = new Emp_dept();
                temp.setId_emp_dept(resultSet.getInt(1));
                temp.setId_emp(resultSet.getInt(2));
                temp.setId_dept(resultSet.getInt(3));
                temp.setNom_emp(Emp.getEmpById(connection, temp.getId_emp()).getNom());
                temp.setNom_dept(Dept.getDeptById(connection, temp.getId_dept()).getNom());
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
            System.out.println("Meuble getAllProduit issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
