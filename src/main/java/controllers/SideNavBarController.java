package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.io.IOException;

public class SideNavBarController {

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

    private boolean sideBarVisible = true;
    @FXML
    void smallSide(MouseEvent event) {

    }

    @FXML

    public void OpenCard (javafx.scene.input.MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listCompteAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listCompteAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




