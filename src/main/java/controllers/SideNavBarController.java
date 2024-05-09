package controllers;

import Entities.User;
import controllers.user.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;

import java.io.IOException;

public class SideNavBarController {

    public Label evenementsText;
    public ImageView evenementsIcon;
    public HBox evenementsBtn;
    @FXML
    private Pane content_area;

    @FXML
    private Text navFullname;

    @FXML
    private PieChart pieChart;

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
    void openUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UsersList.fxml"));
            Pane userListPane = loader.load();
            content_area.getChildren().setAll(userListPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void opencredit(ActionEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeCreditListClientUser.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openDashboardClientCredit(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/dashboardClientcreditredv.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logout(MouseEvent event) throws IOException {
        UserSession.getInstance().cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initData(User user) {
        // Implémentez cette méthode pour initialiser des données spécifiques à l'utilisateur
    }

    public void initializeDashboard() {
        try {
            ServiceUser serviceUser = new ServiceUser();
            int activeNB = serviceUser.getActiveNB();
            int unActiveNB = serviceUser.getunActiveNB();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Actifs", activeNB),
                    new PieChart.Data("Inactifs", unActiveNB)
            );

            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openArticleList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/listArticleAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openRecList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listRecAdmin.fxml"));
            Pane listRecAdminPane = loader.load();
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OpenCard(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listCompteAdminPane = loader.load();
            content_area.getChildren().setAll(listCompteAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ProjectsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

        // set active class
    }
    @FXML
    private void open_evenementsList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/EvenementsList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }


    public void CardStage(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/YesserTest/CardNavBar.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
