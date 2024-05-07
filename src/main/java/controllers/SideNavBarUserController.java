package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private HBox projetsBtn;
    @FXML
    private Label usersText;

    @FXML
    private Label projetsText;
    @FXML
    private ImageView fundrisingIcon;
    @FXML
    private HBox collectBtn;

    @FXML
    private Label collectText;
    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/EvenementsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!evenementsBtn.getStyleClass().contains("activeLink")) {
            evenementsBtn.getStyleClass().add("activeLink");
            evenementsText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("assets/img/store-active.png");
            evenementsIcon.setImage(image);

            if (dashboardBtn.getStyleClass().contains("activeLink")) {
                dashboardBtn.getStyleClass().remove("activeLink");
                dashboardText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/menu.png");
                dashboardIcon.setImage(dashIcon);
            } else if (usersBtn.getStyleClass().contains("activeLink")) {
                usersBtn.getStyleClass().remove("activeLink");
                usersText.getStyleClass().remove("activeText");

                Image usersImg = new Image("assets/img/user.png");
                usersIcon.setImage(usersImg);
            } else if (evenementsBtn.getStyleClass().contains("activeLink")) {
                evenementsBtn.getStyleClass().remove("activeLink");
                evenementsText.getStyleClass().remove("activeText");

                Image fundrisingImg = new Image("assets/img/heart.png");
                fundrisingIcon.setImage(fundrisingImg);
            }

        }
    }


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClient.fxml"));
            Pane listArticleAdminPane = loader.load();
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/ProjectsListUser.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
        if (!projetsBtn.getStyleClass().contains("activeLink")) {
            projetsBtn.getStyleClass().add("activeLink");
            projetsText.getStyleClass().add("activeText");

            // Load the image
            Image image = new Image("../assets/img/project.png");
            investissementsIcon.setImage(image);

            if (projetsBtn.getStyleClass().contains("activeLink")) {
                projetsBtn.getStyleClass().remove("activeLink");
                projetsText.getStyleClass().remove("activeText");

                Image dashIcon = new Image("assets/img/project.png");
                investissementsIcon.setImage(dashIcon);
            } else if (projetsBtn.getStyleClass().contains("activeLink")) {
                projetsBtn.getStyleClass().remove("activeLink");
                projetsText.getStyleClass().remove("activeText");
            }
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