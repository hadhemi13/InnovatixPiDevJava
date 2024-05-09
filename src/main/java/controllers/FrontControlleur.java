package controllers;

import controllers.Compte.AjouterCompte;
import controllers.Virement.DemandeVirementListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FrontControlleur {

    @FXML
    private Button CreerCompte;
    @FXML
    private AnchorPane content_area;



    @FXML
    private void initialize() {

        CreerCompte.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
                AnchorPane captchaPane = loader.load();
                Scene scene = new Scene(captchaPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Stage) CreerCompte.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    void CreateCompte(MouseEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
//        Parent addArticleParent = loader.load();
//
//        // Récupération du contrôleur de la vue d'ajout d'article
//        AjouterCompte ajouterCompte = loader.getController();
//
//        // Remplacer le contenu actuel par la vue d'ajout d'article
//        content_area.getChildren().clear();
//        content_area.getChildren().add(addArticleParent);

    }

}