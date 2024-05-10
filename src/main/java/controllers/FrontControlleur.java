package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/project/ShowEvenementCardFront.fxml"));
        Scene scene = new Scene(fxml);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}

