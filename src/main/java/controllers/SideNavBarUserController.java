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

public class SideNavBarUserController {

    @FXML
    private HBox InvestBtn;

    @FXML
    private HBox actualitesBtn;

    @FXML
    private ImageView actualitesIcon;

    @FXML
    private Label actualitesText;

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



   // public void openDashboardClient( MouseEvent mouseEvent) {
     //   try {
            // Charger le fichier FXML de listArticleAdmin
       //     FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
         //   Pane listArticleAdminPane = loader.load();

//            // Remplacer le contenu de content_area par le contenu
  //          content_area.getChildren().setAll(listArticleAdminPane);
    //    } catch (IOException e) {
      //      e.printStackTrace();
        //}
    //}

    public void openDashboardClient(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void open_profile(MouseEvent mouseEvent) {
    }

    public void open_fundrisingList(MouseEvent mouseEvent) {
    }

    public void open_productsList(MouseEvent mouseEvent) {
    }

    public void open_favList(MouseEvent mouseEvent) {
    }

    public void open_collectList(MouseEvent mouseEvent) {
    }

    public void open_commandsList(MouseEvent mouseEvent) {
    }

    public void open_achatList(MouseEvent mouseEvent) {
    }

    public void open_actualite(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) {
    }

    public void open_notifModel(MouseEvent mouseEvent) {
    }
}
