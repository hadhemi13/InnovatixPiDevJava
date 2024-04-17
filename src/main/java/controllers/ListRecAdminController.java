package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ListRecAdminController {

    @FXML
    private VBox RecListContainer;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private HBox recTableHead;
    @FXML
    void openListRep(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRepAdmin.fxml"));
            Pane listRepAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRepAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
