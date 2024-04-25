package controllers;

import controllers.user.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class SideNavBarUserController {

    @FXML
    private HBox Compte;

    @FXML
    private HBox CreditBtn;

    @FXML
    private HBox achatBtn;

    @FXML
    private HBox achatBtn1;

    @FXML
    private ImageView achatIcon;

    @FXML
    private ImageView achatIcon1;

    @FXML
    private HBox collectBtn;

    @FXML
    private ImageView collectIcon;

    @FXML
    private HBox commandsBtn;

    @FXML
    private ImageView commandsIcon;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox favBtn;

    @FXML
    private ImageView favIcon;

    @FXML
    private ImageView fundrisingIcon;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private ImageView productsIcon;

    @FXML
    private HBox profileBtn;

    @FXML
    private ImageView profileIcon;

    @FXML
    private Text totalNotif;

    public void openLisUser(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfileUser.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        UserSession.getInstance().cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        EventObject event;
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
