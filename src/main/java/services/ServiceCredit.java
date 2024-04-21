package services;

import Entities.Cheque;
import Entities.Credit;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCredit implements  IServiceCredit <Credit> {
    private Connection connection;
    public Statement statement;

    public ServiceCredit() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Cheque cheque) throws SQLException {


    }


    @Override
    public void modifier(Cheque cheque ) throws SQLException {


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
                        rs.getInt("id_client"), rs.getInt("montant"), rs.getDouble("taux"),
                                rs.getObject("datedebut", Date.class),rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}


