package mg.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TempStatusEmp
{
    String nom_emp;
    String nom_dept;
    double salaire_base;
    String profil;
    double annee_travail;
    double salaire_calcul;

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

    public double getSalaire_base() {
        return salaire_base;
    }

    public void setSalaire_base(double salaire_base) {
        this.salaire_base = salaire_base;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public double getAnnee_travail() {
        return annee_travail;
    }

    public void setAnnee_travail(double annee_travail) {
        this.annee_travail = annee_travail;
    }

    public double getSalaire_calcul() {
        return salaire_calcul;
    }

    public void setSalaire_calcul(double salaire_calcul) {
        this.salaire_calcul = salaire_calcul;
    }

    public static List<TempStatusEmp> getAllTempStatus(Connection connection, Date date, String text)
    {
        boolean isOuvert = false;
        List<TempStatusEmp> valiny = new ArrayList<>();
        String query = "SELECT\n" +
                "    ede.id_emp_dept,\n" +
                "    e.nom AS nom_employe,\n" +
                "    d.nom AS nom_departement,\n" +
                "    ds.salaire AS salaire_base,\n" +
                "    p.nom AS profil,\n" +
                "    p.coeff AS coefficient,\n" +
                "    EXTRACT(YEAR FROM AGE('"+date+"', ede.date_embauche)) AS annee_travail,\n" +
                "    ds.salaire * p.coeff AS salaire_calcule\n" +
                "FROM\n" +
                "    emp_dept_embauche ede\n" +
                "        JOIN emp_dept ed ON ed.id_emp_dept_horaire = ede.id_emp_dept\n" +
                "        JOIN emp e ON ed.id_emp = e.id_emp\n" +
                "        JOIN dept d ON ed.id_dept = d.id_dept\n" +
                "        JOIN dept_salaire ds ON ed.id_dept = ds.id_dept\n" +
                "        JOIN profil p ON ede.id_profil = p.id_profil\n" +
                "where LOWER(CONCAT(e.nom, ' ', d.nom, ' ', p.nom, ' ')) LIKE LOWER('%"+text+"%');";
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
                TempStatusEmp temp = new TempStatusEmp();
                temp.setNom_emp(resultSet.getString("nom_employe"));
                temp.setNom_dept(resultSet.getString("nom_departement"));
                temp.setSalaire_base(resultSet.getDouble("salaire_base"));
                temp.setProfil(resultSet.getString("profil"));
                temp.setAnnee_travail(resultSet.getDouble("annee_travail"));
                temp.setSalaire_calcul(resultSet.getDouble("salaire_calcule"));
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
            System.out.println("TempStatusEmp getAllTempStatus issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<TempStatusEmp> getAllStatus(Connection connection, List<TempStatusEmp> listTempStatus)
    {
        boolean isOuvert = false;
        List<TempStatusEmp> valiny = new ArrayList<>();
        for (int i = 0; i < listTempStatus.size(); i++)
        {
            String query = "select * from profil where annee_travail <= "+listTempStatus.get(i).getAnnee_travail()+" order by annee_travail desc limit 1;";
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
                    Profil temp = new Profil();
                    temp.setId_profil(resultSet.getInt(1));
                    temp.setNom(resultSet.getString(2));
                    temp.setAnnee(resultSet.getDouble(3));
                    temp.setCoeff(resultSet.getDouble(4));
                    listTempStatus.get(i).setProfil(temp.getNom());
                    listTempStatus.get(i).setSalaire_calcul(listTempStatus.get(i).getSalaire_base() * temp.getCoeff());
                }
                resultSet.close();
                statement.close();
                if (isOuvert)
                {
                    connection.close();
                }
                valiny.add(listTempStatus.get(i));
            }
            catch (Exception e)
            {
                System.out.println("TempStatusEmp getAllStatus issues");
                e.printStackTrace();
            }
        }
        return valiny;
    }
}
