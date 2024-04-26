package controllers.Credit;
import Entities.RDV;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.IRDV;
import utils.MyDatabase;

import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RdvController implements IRDV <RDV> , Initializable {

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
    private ChoiceBox<Integer> idcreditchoise;
    @FXML
    private HBox stagesBtn;

    @FXML
    private ImageView stagesIcon;
    @FXML
    private Text dateerror;


    @FXML
    private Text employenameerror;

    @FXML
    private Text heureerror;



    @FXML
    private Text idclienterror;



    @FXML
    private Text methodeerror;




    @FXML
    private Label stagesText;

    @FXML
    private HBox usersBtn;

    @FXML
    private ImageView usersIcon;

    @FXML
    private Label usersText;
    Connection connection;
    Integer[] numbers = {1, 2, 5};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = MyDatabase.getInstance().getConnection();

        Connection connection2 = MyDatabase.getInstance().getConnection();
        String selectQuery = "SELECT id FROM credit";

        List<Integer> idList = new ArrayList<>();

        try (Statement statement = connection2.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                idList.add(id);
            }
            System.out.println("IDs from credit table: " + idList);

            // Populate the ComboBox here
            idcreditchoise.getItems().addAll(idList);

        } catch (SQLException e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
    }

    private Timer timer;

    @FXML
    public void saverdv(RDV rdv) throws SQLException {


    }

    @FXML
    void saverdv(ActionEvent event) throws SQLException {
        String insert = "insert into rdv (credit_id,idclient,heure,daterdv,methode,employename) values(?,?,?,?,?,?)";
        connection = MyDatabase.getInstance().getConnection();
        Boolean result;
        try {
            st = connection.prepareStatement(insert);


            if (idclientlabel.getText().length() != 8) {
                idclientlabel.setText("Erreur: idclient doit avoir une longueur de 8 caractères.");
                idclientlabel.setStyle("-fx-text-fill: red;");



            }


            if (employename.getText().isEmpty()) {
                employename.setText("Erreur: employename ne peut pas être vide.");
               employename.setStyle("-fx-text-fill: red;");



            }
            if (methodelabel.getText().isEmpty()) {
                methodelabel.setText("Erreur: methode ne peut pas être vide.");
                methodelabel.setStyle("-fx-text-fill: red;");



            }
            String heureFormat = heurelabel.getText();
            if (!heureFormat.matches("\\d{2}:\\d{2}")) {
                // Si le format est incorrect, afficher un message d'erreur
                heurelabel.setText("Erreur: heure doit être au format deuxnombre:deuxnombre");
                heurelabel.setStyle("-fx-text-fill: red;");



            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    heurelabel.setText("");
                    employename.setText("");
                    idclientlabel.setText("");
                    methodelabel.setText("");
                    timer.cancel();
                }
            }, 6000);



            st.setInt(1, Integer.parseInt(String.valueOf(idcreditchoise.getValue()))); // id_credit
            st.setInt(2, Integer.parseInt(idclientlabel.getText())); // id_client

            String timeString = heurelabel.getText();
            String[] parts = timeString.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
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