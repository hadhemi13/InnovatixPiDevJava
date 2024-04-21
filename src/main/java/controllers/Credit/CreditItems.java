package controllers.Credit;
import java.sql.*;

import Entities.Cheque;
import Entities.Credit;
import controllers.Cheque.ModifierCheque;
import controllers.SideNavBarUserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceCheque;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditItems implements Initializable {
    @FXML
    private Text datedebut;

    @FXML
    private Text duree;

    @FXML
    private Text fraisretard;

    @FXML
    private Text idclient;

    @FXML
    private Text mensualite;

    @FXML
    private Text montant;

    @FXML
    private Text statutclient;

    @FXML
    private Text taux;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Text userItemStateText;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;

    @FXML
    private Text userid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

        // Ne rien faire dans l'initialisation par défaut
    public void initData(Credit i) {
        ServiceCheque serviceCheque = new ServiceCheque();

        userid.setText(String.valueOf(i.getId()));
        idclient.setText(String.valueOf(i.getId_client()));
        montant.setText(String.valueOf(i.getMontant()));
        mensualite.setText(String.valueOf(i.getMensualite()));
        datedebut.setText(String.valueOf(i.getDateDebut()));
        duree.setText(String.valueOf(i.getDuree()));
        taux.setText(String.valueOf(i.getTaux()));
        fraisretard.setText(String.valueOf(i.getFraisretard()));


    }

    @FXML
    public void DeleteCheque(MouseEvent mouseEvent) {

    }
    @FXML
    private HBox creditrow;

    @FXML
    private Pane content_area;

    @FXML
    private DatePicker dateupdatelabel;

    @FXML
    private TextField dureelabdureeupdatelabelel;

    @FXML
    private TextField fraisretardupdatlabel;

    @FXML
    private TextField idupdate;

    @FXML
    private TextField mensualitelabmensulaiteupdatelabelel;

    @FXML
    private TextField montantupdatelabel;

    @FXML
    private TextField tauxupdatelabel;

    @FXML
    public void UpdateCheque(MouseEvent mouseEvent) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updaterdv.fxml"));
       
    }
    public Connection con=  con = MyDatabase.getInstance().getConnection();;
    PreparedStatement st=null;
    ResultSet rs=null;






    @FXML
    int getdata(MouseEvent event) throws IOException {
        Integer rdvid = null;
        Connection con = null;

        try {
            con = MyDatabase.getInstance().getConnection();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updatecredit.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateCreditController updaterdvController = loader.getController();
            String idvalue = userid.getText();

            Credit c11 = getCreditByUserId(con, idvalue);

            updaterdvController.initData(c11);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            // Get the value of the user ID
            System.out.println("User ID: " + idvalue); // Debug statement

            // Retrieve Credit object by user ID
            System.out.println("Credit object: " + c11); // Debug statement

            // Access controller and pass Credit object
            updaterdvController.initData(c11);

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions...
        }

        return rdvid != null ? rdvid : -1;
    }

    // Retrieve Credit object by user ID using an existing connection
    private Credit getCreditByUserId(Connection con, String userId) throws SQLException {
        Credit credit = null;
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM credit WHERE id = ?")) {
            int idClient = Integer.parseInt(userId);
            stmt.setInt(1, idClient);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int id_client = rs.getInt("id_client");
                    double montant = rs.getDouble("montant");
                    double taux = rs.getDouble("taux");
                    Date datedebut = rs.getDate("datedebut");
                    double mensualite = rs.getDouble("mensualite");
                    int duree = rs.getInt("duree");
                    double fraisretard = rs.getDouble("fraisretard");
                    credit = new Credit(id, id_client, montant, taux, datedebut, mensualite, duree, fraisretard);
                }
            }
        }
        return credit;
    }




    public void updatecredit(MouseEvent mouseEvent) throws IOException {
}}
