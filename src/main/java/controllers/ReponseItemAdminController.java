//package controllers;
//
//import Entities.Reclamation;
//import Entities.Reponse;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Text;
//import services.ServiceReclamation;
//import services.ServiceReponse;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class ReponseItemAdminController implements Initializable {
//
//
//    @FXML
//    private Text ContenuRep;
//
//    @FXML
//    private Text dateRep;
//
//    @FXML
//    private ImageView deleteRep;
//
//    @FXML
//    private HBox deleterep;
//
//    @FXML
//    private ImageView editRep;
//
//    @FXML
//    private HBox editrep;
//
//    @FXML
//    private HBox itemRep;
//
//    @FXML
//    private Text mailRep;
//    @FXML
//    private Text dateRec;
//    @FXML
//    private ImageView piecejrep;
//    private Reponse reponse;
//
//    @FXML
//    void deleteRep(MouseEvent event) {
//        try {
//            if (reponse != null) {
//                ServiceReponse serviceReponse = new ServiceReponse();
//                serviceReponse.supprimer(reponse);
//
//
//            } else {
//                // Affichez un message d'erreur ou faites une action appropriée si la réclamation est null
//                System.err.println("La réponse est null. Impossible de la supprimer.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @FXML
//    void editRep(MouseEvent event) {
//
//    }
//
//    public void initData(Reponse reponse) {
//
////        RecItemPieceJ.setText(reclamation.getPiece_jrec());
//        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
//        piecejrep.setImage(imageJ);
//        dateRep.setText(String.valueOf(reponse.getDate_rep()));
////        mailRep.setText(reponse.getAdr_rep());
//        ContenuRep.setText(reponse.getContenu_rep());
//        dateRec.setText(String.valueOf(reponse.getReclamation().getDate_rec()));
//        mailRep.setText(reponse.getReclamation().getAdr_rec());
//
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//
//    }
//}
package controllers;

import Entities.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceReponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReponseItemAdminController{


    @FXML
    private Text ContenuRep;

    @FXML
    private Text dateRec;

    @FXML
    private Text dateRep;

    @FXML
    private ImageView deleterep;

    @FXML
    private HBox deleteRep;

    @FXML
    private ImageView editRep;

    @FXML
    private HBox editrep;

    @FXML
    private HBox itemRep;

    @FXML
    private Text mailRep;

    @FXML
    private ImageView piecejrep;
    private ReponseItemAdminController reponse;

    public void setreponse(ReponseItemAdminController reponse) {
        this.reponse = reponse;
    }

    public void initData(Reponse reponse) {
        ServiceReponse serviceReponse = new ServiceReponse();

        if (reponse != null) {
            Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
            piecejrep.setImage(imageJ);
            dateRep.setText(String.valueOf(reponse.getDate_rep()));
            ContenuRep.setText(reponse.getContenu_rep());
            dateRec.setText(String.valueOf(reponse.getReclamation().getDate_rec()));
            mailRep.setText(reponse.getReclamation().getAdr_rec());
            editRep.setId(String.valueOf(reponse.getId()));
            editRep.setOnMouseClicked(event -> {try {
                // Charger le fichier FXML du formulaire de modification d'article
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierRéponse.fxml"));
                Parent editArticlePopupParent = loader.load();

                // Récupérer le contrôleur du formulaire de modification d'article
                ModifierRéponseController modifierRéponseController = loader.getController();

                // Passer l'article à modifier au contrôleur
                modifierRéponseController.initData(reponse);

                // Créer une nouvelle fenêtre modale pour le formulaire de modification
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier Article");
                stage.setScene(new Scene(editArticlePopupParent));
                stage.showAndWait(); // Attendre que la fenêtre se ferme avant de continuer

            } catch (IOException e) {
                e.printStackTrace();
            }
            });
            deleterep.setId(String.valueOf(reponse.getId()));
            deleterep.setOnMouseClicked(event -> {
            System.out.println("ID de l'article à supprimer : " + reponse.getId());
            try {
                serviceReponse.supprimerParId(reponse.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRepAdmin.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        }
    }
    @FXML
    void deleteRep(MouseEvent event) {
//        if (reponse != null && reponse.getId() != null) {
//            try {
//                ServiceReponse serviceReponse = new ServiceReponse();
//                serviceReponse.supprimer(reponse);
//                System.out.println("Réponse supprimée avec succès.");
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listCommentAdmin.fxml"));
//                Parent root = loader.load();
//                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
//                contentArea.getChildren().clear();
//                contentArea.getChildren().add(root);
//            } catch (SQLException | IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.err.println("La réponse est null ou son ID est null. Impossible de la supprimer.");
//        }

    }

        @FXML
    void editRep(MouseEvent event) {

    }
}
