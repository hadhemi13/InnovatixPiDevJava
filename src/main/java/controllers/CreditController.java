package controllers;

import Entities.Credit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;
import services.ICredit;
import utils.MyDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditController implements ICredit<Credit> {

    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    @FXML
    private Button btnclear;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private DatePicker datedebutlabel;

    @FXML
    private TextField dureelabel;

    @FXML
    private TextField fraisretardlabel;

    @FXML
    private TextField identifiantlabel;

    @FXML
    private TextField mensualitelabel;

    @FXML
    private TextField montantlabel;
    @FXML
    private TableColumn<Credit, String> coldatedebut;

    @FXML
    private TableColumn<Credit, Integer> colduree;

    @FXML
    private TableColumn<Credit, Double> colfraisretard;

    @FXML
    private TableColumn<Credit,Integer> colid;

    @FXML
    private TableColumn<Credit,Integer> colidentifiant;

    @FXML
    private TableColumn<Credit, Double> colmensualite;

    @FXML
    private TableColumn<Credit, Double> colmontant;

    @FXML
    private TableColumn<Credit, Double>coltaux;

    @FXML
    private TextField tauxlabel;

    @FXML
    private TableView<Credit> tablecredit;
    int id=0;
    Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = MyDatabase.getInstance().getConnection();


    }

    @FXML
    void smallSide(MouseEvent event) {

    }
    @FXML
    private Button calculfraisretardbtn;

    @Override
    public void savecredit(Credit credit) throws SQLException {
        String insert = "insert into credit (user_id, id_client, montant, taux, datedebut, mensualite, duree, fraisretard) values (?, ?, ?, ?, ?, ?, ?, ?)";
        connection = MyDatabase.getInstance().getConnection();
        try {
            st = con.prepareStatement(insert);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, Integer.parseInt(identifiantlabel.getText())); // id_client
            st.setDouble(3, Double.parseDouble(montantlabel.getText())); // montant
            st.setDouble(4, Double.parseDouble(tauxlabel.getText())); // taux
            st.setDate(5, java.sql.Date.valueOf(datedebutlabel.getValue())); // datedebut
            st.setDouble(6, Double.parseDouble(mensualitelabel.getText())); // mensualite
            st.setInt(7, Integer.parseInt(dureelabel.getText())); // duree
            st.setDouble(8, Double.parseDouble(fraisretardlabel.getText())); // fraisretard

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void savecredit(ActionEvent actionEvent) {
        String insert = "insert into credit (user_id, id_client, montant, taux, datedebut, mensualite, duree, fraisretard) values (?, ?, ?, ?, ?, ?, ?, ?)";
        connection = MyDatabase.getInstance().getConnection();
        try {
            st = connection.prepareStatement(insert);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, Integer.parseInt(identifiantlabel.getText())); // id_client
            st.setDouble(3, Double.parseDouble(montantlabel.getText())); // montant
            st.setDouble(4, Double.parseDouble(tauxlabel.getText())); // taux
            st.setDate(5, java.sql.Date.valueOf(datedebutlabel.getValue())); // datedebut
            st.setDouble(6, Double.parseDouble(mensualitelabel.getText())); // mensualite
            st.setInt(7, Integer.parseInt(dureelabel.getText())); // duree
            st.setDouble(8, Double.parseDouble(fraisretardlabel.getText())); // fraisretard

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void calculertaux(ActionEvent event) {
        Double montant=Double.parseDouble(montantlabel.getText());
        Double mensualite=Double.parseDouble(mensualitelabel.getText());
       Double duree=Double.parseDouble(mensualitelabel.getText());
        var interestRate = (((mensualite * duree - montant) / montant) * 12) / duree;
        tauxlabel.setText(String.valueOf(interestRate));


    }
    @FXML
    void calculfraisretard(ActionEvent event) {

        Double montant=Double.parseDouble(montantlabel.getText());
        var fraisretard= montant*0.01;
        fraisretardlabel.setText(String.valueOf(fraisretard));


    }
}
