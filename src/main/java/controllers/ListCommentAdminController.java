package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.cert.PolicyNode;

public class ListCommentAdminController {

    @FXML
    private VBox commentListContainer;

    @FXML
    private Text commentListTitle;

    @FXML
    private Pane content_area;

    @FXML
    private HBox commentTableHead;

    @FXML
    private ComboBox<?> triecommentInput;

    @FXML
    void returnBackListArt(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
            Pane listComAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin

            content_area.getChildren().setAll(listComAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
