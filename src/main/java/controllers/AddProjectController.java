package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddProjectController implements Initializable {

    @FXML
    private GridPane AddProjectContainer;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/AddProjectCard.fxml"));
            VBox ProjectContainer1 = fxmlLoader1.load();
            AddProjectContainer.add(ProjectContainer1, 0, 1);
            GridPane.setMargin(ProjectContainer1, new Insets(0, 10, 25, 10));
            ProjectContainer1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
