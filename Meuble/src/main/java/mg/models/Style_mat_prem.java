package mg.models;

import java.sql.*;

public class Style_mat_prem
{
    int id_style;
    int id_mat_premiere;

    public int getId_style() {
        return id_style;
    }
    public void setId_style(int id_style) {
        this.id_style = id_style;
    }
    public int getId_mat_premiere() {
        return id_mat_premiere;
    }
    public void setId_mat_premiere(int id_mat_premiere) {
        this.id_mat_premiere = id_mat_premiere;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, "+this.getId_style()+", "+this.getId_mat_premiere()+")";
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

    public static int getIdStyleMatPremByIdStyleIdMatPrem(Connection connection, int id_style, int id_mat_premiere)
    {
        int valiny = 0;
        boolean isOuvert = false;
        String query = "select * from style_mat_prem where id_style = "+id_style+" and id_matiere_premiere = "+id_mat_premiere+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
            {
                valiny = resultSet.getInt("id_style_mat_prem");
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
            System.out.println("Style getStyleById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
