package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ListRepAdminController {


    @FXML
    private ImageView backBtn;

    @FXML
    private Pane content_area;

    @FXML
    private VBox repListContainer;

    @FXML
    private Text repListTitle;

    @FXML
    private HBox repTableHead;

    @FXML
    private ComboBox<?> trierepInput;
    @FXML
    void returnBack(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRecAdmin.fxml"));
            Pane listRecAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
