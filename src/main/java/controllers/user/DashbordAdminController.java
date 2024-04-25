package controllers.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class DashbordAdminController {

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
    private HBox evenementsBtn;

    @FXML
    private ImageView evenementsIcon;

    @FXML
    private Label evenementsText;

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
    void smallSide(MouseEvent event) {

    }



    @FXML
    private void openUsersList(MouseEvent event) {
        try {
            // Chargement de l'interface utilisateur de la liste des utilisateurs
            Parent fxml = FXMLLoader.load(getClass().getResource("src/main/resources/FXML/UsersList.fxml"));

            // Remplacement du contenu existant par la nouvelle interface utilisateur
            content_area.getChildren().setAll(fxml);

            // Réinitialisation de l'état des boutons et des icônes

            // Suppression de la classe de style "activeLink" de tous les boutons
            usersBtn.getStyleClass().remove("activeLink");
            dashboardBtn.getStyleClass().remove("activeLink");

            // Suppression de la classe de style "activeText" de tous les textes associés aux boutons
            usersText.getStyleClass().remove("activeText");
            dashboardText.getStyleClass().remove("activeText");


            // Définition de l'état actif pour le bouton "Users" et son texte associé
            usersBtn.getStyleClass().add("activeLink");
            usersText.getStyleClass().add("activeText");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

