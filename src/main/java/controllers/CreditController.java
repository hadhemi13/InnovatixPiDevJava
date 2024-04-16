package controllers;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Entities.Credit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.events.MouseEvent;
import services.ICredit;
import utils.MyDatabase;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CreditController implements ICredit<Credit> , Initializable {

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
    private Button btnswitchlist;

    @FXML
    private TableColumn<Credit, Double> colmontant;

    @FXML
    private TableColumn<Credit, Double>coltaux;
    @FXML
    private Label idlabel;

    @FXML
    private RadioButton refused;
    @FXML
    private RadioButton accepted;
    @FXML
    private VBox creditContainer;

    @FXML
    private Label fraisretardshow;
    @FXML
    private Label dateshow;

    @FXML
    private Label dureeshow;
    @FXML
    private Label identifiantshow;
    @FXML
    private TextField tauxlabel;
    @FXML
    private TableView<Credit> tablecredit;
    @FXML
    private Label mensualiteshow;
    @FXML
    private Label tauxshow;

    @FXML
    private Label montantshow;

    @FXML
    private Label idshow;
    int id=0;
    Connection connection;




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
    public ObservableList<Credit> getCredits(){
        ObservableList<Credit> credits= FXCollections.observableArrayList();
        String query="select id, id_client, montant, taux, datedebut, mensualite, duree, fraisretard from credit";
        connection = MyDatabase.getInstance().getConnection();
        try{
            st=connection.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next()){
                Credit st=new Credit();
                st.setId(rs.getInt("id"));
                st.setId_client(rs.getInt("id_client"));
                st.setMontant(rs.getDouble("montant"));
                st.setMensualite(rs.getDouble("mensualite"));
                st.setDateDebut((rs.getDate("datedebut")));
                st.setDuree(rs.getInt("duree"));
                st.setTaux(rs.getDouble("taux"));
                st.setFraisRetard((rs.getDouble("fraisretard")));
                credits.add(st);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return credits;
    }
    public void showcrdit(){
        ObservableList<Credit> list= getCredits();
        tablecredit.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Credit,Integer>("id"));
        colidentifiant.setCellValueFactory(new PropertyValueFactory<Credit,Integer>("id_client"));
        colmontant.setCellValueFactory(new PropertyValueFactory<Credit,Double>("montant"));
        coltaux.setCellValueFactory(new PropertyValueFactory<Credit,Double>("taux"));
        coldatedebut.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Date date = cellData.getValue().getDateDebut();
            if (date != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                property.setValue(dateFormat.format(date));
            }
            return property;
        });        colmensualite.setCellValueFactory(new PropertyValueFactory<Credit,Double>("mensualite"));
        colduree.setCellValueFactory(new PropertyValueFactory<Credit,Integer>("duree"));
        colfraisretard.setCellValueFactory(new PropertyValueFactory<Credit, Double>("fraisretard"));

    }
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    void closescene(MouseEvent event) {
   

    }



    @FXML
    void calculfraisretard(ActionEvent event) {

        Double montant=Double.parseDouble(montantlabel.getText());
        var fraisretard= montant*0.01;
        fraisretardlabel.setText(String.valueOf(fraisretard));


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (url != null && url.getPath().contains("listecredit.fxml")) {
            connection = MyDatabase.getInstance().getConnection();
            System.out.println("g");
            showcrdit();
        }
    }



    public Credit getData(javafx.scene.input.MouseEvent mouseEvent) {
        Credit credit=tablecredit.getSelectionModel().getSelectedItem();
        System.out.println(credit);
        id=credit.getId();
        return credit;

    }

    @FXML
    void deletecredit(ActionEvent event) {
        String Delete="delete from credit where id = ?";

        connection = MyDatabase.getInstance().getConnection();
        try {
            st=connection.prepareStatement(Delete);
            st.setInt(1,id);
            st.executeUpdate();
            showcrdit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void switchtoscenecredit(MouseEvent event) {

    }

    public void switchtoscenupdatecredit(javafx.scene.input.MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updatecredit.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the controller after loading the FXML file
        updatecreditcontroller updateCreditController = loader.getController();

        // Get the selected credit
        Credit credit = getData(mouseEvent);

        // Check if a credit is selected
        if (credit != null) {
            // Initialize the data in the updateCreditController
            updateCreditController.initData(credit);

            // Set the scene
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // Handle the case where no credit is selected
            System.out.println("No credit selected.");
        }
    }

    public void closescene(javafx.scene.input.MouseEvent mouseEvent) {
        // Récupérer la liste des fenêtres (stages) actives
        List<Window> windows = Window.getWindows();

        // Parcourir les fenêtres et fermer celles qui correspondent à ajoutercredit.fxml
        for (Window window : windows) {
            if (window instanceof Stage) {
                Scene scene = ((Stage) window).getScene();
                if (scene != null && scene.getRoot().getId().equals("/FXML/ajoutercredit.fxml")) {
                    ((Stage) window).close();
                    break; // Sortir de la boucle une fois que la scène est fermée
                }
            }
        }
    }

    public void switchtoscenecredit(javafx.scene.input.MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listecredit.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
