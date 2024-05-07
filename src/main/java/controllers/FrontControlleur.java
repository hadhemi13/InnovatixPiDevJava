package controllers;

import controllers.offreDeStage.Recrutement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class FrontControlleur {

    @FXML
    private HBox Stage;
    @FXML
    private AnchorPane content_area;



    public void OpenStage(MouseEvent mouseEvent) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/Recrutement.fxml"));
       Parent recrutementParent = loader.load();
       content_area.getChildren().clear();
       content_area.getChildren().add(recrutementParent);
   }
}
