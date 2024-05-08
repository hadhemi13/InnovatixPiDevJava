package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class FrontControlleur {
    @FXML
    private ImageView evenementsIcon;
    @FXML
    private Pane content_area;
    @FXML
    private Label evenementsText;
    @FXML
    private HBox evenementsBtn;

    @FXML
    private Label dashboardText;
    @FXML
    private HBox dashboardBtn;
    @FXML
    private ImageView dashboardIcon;

    @FXML
    private Label usersText;

    @FXML
    private ImageView usersIcon;
    @FXML
    private HBox usersBtn;

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
            }

        }
    }


}

