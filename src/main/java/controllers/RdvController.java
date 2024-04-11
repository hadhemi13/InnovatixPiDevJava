package controllers;
import Entities.RDV;
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
import services.IRDV;
import utils.MyDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RdvController implements IRDV <RDV> {

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
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
    private TextField creditidlabel;

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
    private DatePicker datedebutlabel;

    @FXML
    private TextField employename;

    @FXML
    private HBox evenementsBtn;

    @FXML
    private ImageView evenementsIcon;

    @FXML
    private Label evenementsText;

    @FXML
    private TextField heurelabel;

    @FXML
    private TextField idclientlabel;

    @FXML
    private ImageView investissementsIcon;

    @FXML
    private Label investissementsText;

    @FXML
    private ImageView logo;

    @FXML
    private TextField methodelabel;

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
    private HBox usersBtn;

    @FXML
    private ImageView usersIcon;

    @FXML
    private Label usersText;
    Connection connection;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = MyDatabase.getInstance().getConnection();


    }

    @FXML
    public void saverdv(RDV rdv) throws SQLException {

        String insert = "insert into rdc (credit_id,idclient,heure,daterdv,methode,employename) values(?,?,?,?,?,?)";
        connection = MyDatabase.getInstance().getConnection();

        st = connection.prepareStatement(insert);
        st.setInt(1, Integer.parseInt(creditidlabel.getText())); // id_client
        st.setInt(2, Integer.parseInt(idclientlabel.getText())); // id_client

        String timeString = heurelabel.getText();

        java.sql.Time time = java.sql.Time.valueOf(timeString);
        st.setTime(3, time);
        st.setString(4, methodelabel.getText());
        st.setString(5, employename.getText());
        st.executeUpdate();

    }

    @FXML
    void saverdv(ActionEvent event) throws SQLException {
        String insert = "insert into rdv (credit_id,idclient,heure,daterdv,methode,employename) values(?,?,?,?,?,?)";
        connection = MyDatabase.getInstance().getConnection();

        try {
            st = connection.prepareStatement(insert);


            st.setInt(1, Integer.parseInt(creditidlabel.getText())); // id_client
            st.setInt(2, Integer.parseInt(idclientlabel.getText())); // id_client
            String timeString = heurelabel.getText();

// Split the string into hours and minutes
            String[] parts = timeString.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

// Create a java.sql.Time object
            java.sql.Time time = new java.sql.Time(hours, minutes, 0);

            st.setTime(3, time);
            st.setDate(4, java.sql.Date.valueOf(datedebutlabel.getValue())); // datedebut

            st.setString(5, methodelabel.getText());
            st.setString(6, employename.getText());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}