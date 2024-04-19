package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class SideNavBarController implements Initializable {

    @FXML
    private HBox InvestBtn;

    @FXML
    private HBox actualitesBtn;

    @FXML
    private ImageView actualitesIcon;

    @FXML
    private Label actualitesText;

    @FXML
    private HBox chartContainer;

    @FXML
    private HBox compteBtn;

    @FXML
    private ImageView comptesIcon;

    @FXML
    private Label comptesText;

    @FXML
    private Pane content_area;

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
    private ImageView investissementsIcon;

    @FXML
    private Label investissementsText;

    @FXML
    private ImageView logo;

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
    @FXML
    private ChoiceBox<String> choiceCredit;
    private String [] choiceCredits={"ajouter credit ","modifier credit","afficher credit"};

    public void initialize(URL url, ResourceBundle rb){

    }



    private boolean sideBarVisible = true;
    @FXML
    void smallSide(MouseEvent event) {

    }

    @FXML
    void switchtosceneajoutercredit(MouseEvent event) throws IOException {
        // Load the ajoutercredit.fxml scene
        FXMLLoader creditLoader = new FXMLLoader(getClass().getResource("/FXML/ajoutercredit.fxml"));
        Parent creditRoot = creditLoader.load();

        // Create a new stage for the ajoutercredit.fxml scene
        Stage creditStage = new Stage();
        Scene creditScene = new Scene(creditRoot);
        creditStage.setScene(creditScene);
        creditStage.show();

        // Load the sidenavbar.fxml scene
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/FXML/sidenavbar.fxml"));
        Parent sidebarRoot = sidebarLoader.load();

        // Get the stage of the parent scene
        Stage parentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the sidenavbar.fxml scene as the main scene
        Scene sidebarScene = new Scene(sidebarRoot);
        parentStage.setScene(sidebarScene);
    }


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchlisterdv(MouseEvent mouseEvent) throws IOException {
        // Load the ajoutercredit.fxml scene
        FXMLLoader creditLoader = new FXMLLoader(getClass().getResource("/FXML/listerdv.fxml"));
        Parent creditRoot = creditLoader.load();

        // Create a new stage for the ajoutercredit.fxml scene
        Stage creditStage = new Stage();
        Scene creditScene = new Scene(creditRoot);
        creditStage.setScene(creditScene);
        creditStage.show();

        // Load the sidenavbar.fxml scene
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/FXML/sidenavbar.fxml"));
        Parent sidebarRoot = sidebarLoader.load();

        // Get the stage of the parent scene
        EventObject event;
        Stage parentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        // Set the sidenavbar.fxml scene as the main scene
        Scene sidebarScene = new Scene(sidebarRoot);
        parentStage.setScene(sidebarScene);


    }}

