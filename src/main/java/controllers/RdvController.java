package controllers;
import Entities.Credit;
import Entities.RDV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.IRDV;
import utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class RdvController implements IRDV <RDV> , Initializable {

    public ListView idcreditchoise;
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
    private ChoiceBox<Integer> pvr = new ChoiceBox<>();
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
    private Label rdvdata;
    @FXML
    private Button btnupdate;
    @FXML
    private Label dashboardText;
    @FXML
    private Button deletebtn;
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
    private ListView<Integer> idcreditchoisei = new ListView<>();
    private HBox stagesBtn;

    @FXML
    private ImageView stagesIcon;
    @FXML
    private ListView<Integer > datardv = new ListView<>();
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






    private Timer timer;

    @FXML
    public void saverdv(RDV rdv) throws SQLException {


    }




    public void showidrdv() {
        try {

            ObservableList<RDV> list = getrdv();
            ObservableList<Integer> idList = FXCollections.observableArrayList();

            // Extract IDs and add them to the idList
            for (RDV rdv : list) {
                idList.add(rdv.getId());
            }

            // Set the items of the ListView to the idList
            idcreditchoisei.setItems(idList);


            System.out.println("ffff"+idcreditchoisei.getItems());
            System.out.println(idList);

        }
        catch(Exception e )
        {

            System.out.println(e.getMessage());
        }
    }



    public ObservableList<RDV> getrdv(){
        ObservableList<RDV> rdvs= FXCollections.observableArrayList();
        String query="select id, credit_id, idclient, heure, daterdv, methode, employename from rdv";
        connection = MyDatabase.getInstance().getConnection();
        try{
            st=connection.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next()){
                RDV st=new RDV();
                st.setId(rs.getInt("id"));
                st.setCredit_id(rs.getInt("credit_id"));
                st.setIdclient(rs.getInt("idclient"));

                st.setHeure(rs.getTime("heure"));
                st.setDaterdv((rs.getDate("daterdv")));
                st.setMethode(rs.getString("methode"));
                st.setEmployename(rs.getString("employename"));

                rdvs.add(st);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return rdvs;
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


            }
            if (employename.getText().isEmpty()) {
                employename.setText("Erreur: employename ne peut pas être vide.");


            }
            if (methodelabel.getText().isEmpty()) {
                methodelabel.setText("Erreur: methode ne peut pas être vide.");


            }
            String heureFormat = heurelabel.getText();
            if (!heureFormat.matches("\\d{2}:\\d{2}")) {
                // Si le format est incorrect, afficher un message d'erreur
                heurelabel.setText("Erreur: heure doit être au format deuxnombre:deuxnombre");


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



            int selectedItemId = pvr.getSelectionModel().getSelectedItem();
            st.setInt(1, selectedItemId); // id_credit
            st.setInt(2, parseInt(idclientlabel.getText())); // id_client

            String timeString = heurelabel.getText();
            String[] parts = timeString.split(":");
            int hours = parseInt(parts[0]);
            int minutes = parseInt(parts[1]);
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

    public void switchtosceneajoutercredit(MouseEvent mouseEvent) {
    }

    public void smallSide(MouseEvent mouseEvent) {
    }
    @FXML
    void deleterdv(ActionEvent event) {

    }
Integer id=0;
    @FXML
    Integer getrdvdata(javafx.scene.input.MouseEvent mouseEvent) {
        Integer rdvid = idcreditchoisei.getSelectionModel().getSelectedItem(); // Change to Integer
        System.out.println("h");
        if (rdvid != null) {
            id = rdvid; // Remove this line if 'id' is an 'int'
            System.out.println(rdvid);
           id=rdvid;
            return rdvid; // Change return type to int
        } else {
            System.out.println("No RDV selected.");
            return -1; // Return a default value for no selection
        }
    }



    @FXML
    void updaterdv(ActionEvent event) {

    }
    @FXML
    void  switchtoscenupdaterdv(MouseEvent mouseEvent) {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updaterdv.fxml"));
        try {
            Parent root = loader.load();

            // Get the controller after loading the FXML file
            updaterdvcontroller updaterdvController = loader.getController();
          Integer selctedid=id;
            updaterdvController.initDatardv(selctedid);

            showidrdv();
            // Get the selected rdv ID (assuming you have a method to retrieve it)
            int rdvId = getrdvdata(mouseEvent);

            // Check if an rdv ID is selected
            if (rdvId != -1) {
                updaterdvController.initDatardv(rdvId);

                // Set the scene
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Handle the case where no rdv ID is selected
                System.out.println("No RDV selected.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showidrdv();

        connection = MyDatabase.getInstance().getConnection();
        String selectQuery = "SELECT credit.id\n" +
                "FROM credit\n" +
                "INNER JOIN user ON credit.user_id = user.id\n" +
                "WHERE user.id = 1;";

        List<Integer> idList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("credit.id");
                idList.add(id);
            }

            // Clear the existing items in the ComboBox before adding new ones
            pvr.getItems().clear();
            // Add all items from the list to the ComboBox
            pvr.getItems().addAll(idList);
            idcreditchoisei.setCellFactory(param -> new ListCell<Integer>() {
                private final Button updateButton = new Button("Update");
                private final Button deleteButton = new Button("Delete");

                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(String.valueOf(item));
                        setGraphic(updateButton);
                        HBox buttons = new HBox(updateButton, deleteButton);
                        setGraphic(buttons);

                        // Add action event handler to the updateButton
                        updateButton.setOnAction(event -> handleUpdateAction());
                       deleteButton.setOnAction(event -> handledeleteAction());

                    }
                }

                private void handleUpdateAction() {
                    try {
                        // Open the Updaterdv scene and pass the selected ID to it
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/updaterdv.fxml"));
                        Parent root = loader.load(); // Load the FXML file and instantiate the controller

                        // Get the controller after loading the FXML file
                        updaterdvcontroller updaterdvcontroller = loader.getController();
                        updaterdvcontroller.initDatardv(id); // Initialize the controller with data

                        // Show the scene
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace(); // Handle the exception properly
                    }
                }
                private void handledeleteAction() {
                    String Delete="delete from rdv where id = ?";

                    connection = MyDatabase.getInstance().getConnection();
                    try {
                        st=connection.prepareStatement(Delete);
                        st.setInt(1,id);
                        st.executeUpdate();
                        showidrdv();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            });

            System.out.println("IDs from credit table: " + idList);
            showidrdv();
            // Populate the ComboBox here

        } catch (Exception e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
    }




}