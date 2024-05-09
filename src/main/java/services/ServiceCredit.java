package services;

import Entities.Credit;
import utils.MyDatabase;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ServiceCredit implements  IServiceCredit <Credit> {
    private Connection connection;
    public Statement statement;

    public ServiceCredit() {
        connection = MyDatabase.getInstance().getConnection();
    }


    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    public Map<Integer, Integer> countCreditsByYear(List<Credit> credits) {
        Map<Integer, Integer> creditCountByYear = new HashMap<>();

        // Iterate through the list of credits
        for (Credit credit : credits) {
            // Extract the year from the credit's start date
            int year = getYearFromDate((Date) credit.getDateDebut());

            // Increment the count for the corresponding year in the map
            creditCountByYear.put(year, creditCountByYear.getOrDefault(year, 0) + 1);
        }

        return creditCountByYear;
    }



    @Override
    public void supprimer(int id) throws SQLException {


    }

    @Override
    public  List<Credit> afficher() throws SQLException {

        List<Credit> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM credit";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Credit
                        (rs.getInt("id"),
                        rs.getInt("id_client"), rs.getInt("montant"), rs.getString("statusclient"), rs.getDouble("taux"),
                                rs.getObject("datedebut", Date.class),rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            System.out.println("zz"+
                    list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List<Credit> affichercreditparmontant() throws SQLException {
        List<Credit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM credit  ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getObject("datedebut", Date.class),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            // Tri de la liste par montant en utilisant Java Stream
            list = list.stream()
                    .sorted(Comparator.comparingDouble(Credit::getMontant))
                    .toList();
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public List<Credit> affichercreditpardatedebut() throws SQLException {
        List<Credit> list = new ArrayList<>();
        System.out.println("aaaaaaaaaaaaaaaaz");
        try {
            String req = "SELECT * FROM credit ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getObject("datedebut", Date.class),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            // Tri de la liste par date de début en utilisant Java Stream
            list = list.stream()
                    .sorted(Comparator.comparing(Credit::getDatedebut))
                    .toList();
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

}


