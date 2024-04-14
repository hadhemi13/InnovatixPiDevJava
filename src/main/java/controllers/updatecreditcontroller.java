package controllers;

import Entities.Credit;
import javafx.scene.layout.HBox;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.w3c.dom.ls.LSOutput;
import utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class updatecreditcontroller {

    @FXML
    private HBox InvestBtn;

    @FXML
    private HBox actualitesBtn;

    @FXML
    private ImageView actualitesIcon;

    @FXML
    private Label actualitesText;

    @FXML
    private Button btnsave;

    @FXML
    private Button calculfraisretardbtn;

    @FXML
    private Button calcultauxbtn;

    @FXML
    private HBox chartContainer;

    @FXML
    private ChoiceBox<?> choiceCredit;

    @FXML
    private HBox compteBtn;

    @FXML
    private ImageView comptesIcon;

    @FXML
    private Label comptesText;

    @FXML
    private Pane content_area;

    @FXML
    private Pane content_area1;

    @FXML
    private HBox creditsBtn;

    @FXML
    private ImageView creditsIcon;

    @FXML
    private Label creditsText;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private Label dashboardText;

    @FXML
    private DatePicker dateupdatelabel;

    @FXML
    private TextField dureelabdureeupdatelabelel;

    @FXML
    private HBox evenementsBtn;

    @FXML
    private ImageView evenementsIcon;

    @FXML
    private Label evenementsText;

    @FXML
    private TextField fraisretardupdatlabel;

    @FXML
    private TextField idupdate;

    @FXML
    private ImageView investissementsIcon;

    @FXML
    private Label investissementsText;

    @FXML
    private ImageView logo;

    @FXML
    private TextField mensualitelabmensulaiteupdatelabelel;

    @FXML
    private TextField montantupdatelabel;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox recBtn;

    @FXML
    private Label reclamationText;

    @FXML
    private ImageView reclamationsIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private HBox stagesBtn;

    @FXML
    private ImageView stagesIcon;

    @FXML
    private Label stagesText;


    @FXML
    private TextField tauxupdatelabel;

    @FXML
    private HBox usersBtn;

    @FXML
    private ImageView usersIcon;

    @FXML
    private Label usersText;

    @FXML
    void calculertaux(ActionEvent event) {

    }

    @FXML
    void calculfraisretard(ActionEvent event) {

    }
    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    @FXML
    void updatecredit(ActionEvent event) {
        String update = "update credit set user_id = ?, id_client = ?, montant = ?, taux = ?, datedebut = ?, mensualite = ?, duree = ?, fraisretard =? where id = ?" ;
        con=  MyDatabase.getInstance().getConnection();

        try {
            st=con.prepareStatement(update);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, Integer.parseInt(idupdate.getText())); // id_client
            st.setDouble(3, Double.parseDouble(montantupdatelabel.getText())); // montant
            st.setDouble(4, Double.parseDouble(tauxupdatelabel.getText())); // taux
            st.setDate(5, java.sql.Date.valueOf(dateupdatelabel.getValue())); // datedebut
            st.setDouble(6, Double.parseDouble(mensualitelabmensulaiteupdatelabelel.getText())); // mensualite
            st.setInt(7, Integer.parseInt(dureelabdureeupdatelabelel.getText())); // duree
            st.setDouble(8, Double.parseDouble(fraisretardupdatlabel.getText())); // fraisretard
            st.setInt(9,id);
            st.executeUpdate();
            System.out.println(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int id;

    public void initData(Credit credit) {
         id=credit.getId();
        idupdate.setText(String.valueOf(credit.getId_client()));
        montantupdatelabel.setText(String.valueOf(credit.getMontant()));
        tauxupdatelabel.setText(String.valueOf(credit.getTaux()));
        Date dateDebut = credit.getDateDebut();
        dateupdatelabel.setValue(LocalDate.parse(dateDebut.toString()));
        mensualitelabmensulaiteupdatelabelel.setText(String.valueOf(credit.getMensualite()));
        dureelabdureeupdatelabelel.setText(String.valueOf(credit.getDuree()));
        fraisretardupdatlabel.setText(String.valueOf(credit.getFraisRetard()));
    }


}