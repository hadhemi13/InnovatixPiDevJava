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
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.ServiceReponse;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReponseItemAdminController implements Initializable {

    @FXML
    private Text ContenuRep;

    @FXML
    private Text dateRep;

    @FXML
    private ImageView deleteRep;

    @FXML
    private Text mailRep;

    @FXML
    private Text dateRec;

    @FXML
    private ImageView piecejrep;
    public static int reponseId;
    Reponse reponse ;



    public void initData(Reponse reponse) {
        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
        piecejrep.setImage(imageJ);
        dateRep.setText(String.valueOf(reponse.getDate_rep()));
        ContenuRep.setText(reponse.getContenu_rep());
        dateRec.setText(String.valueOf(reponse.getReclamation().getDate_rec()));
        mailRep.setText(reponse.getReclamation().getAdr_rec());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void deleteRep(MouseEvent event) {
        initData(reponse);
        try {
            if (reponse != null) {
                ServiceReponse serviceReponse = new ServiceReponse();
                serviceReponse.supprimer(reponse);
                System.out.println("Réponse supprimée avec succès.");
            } else {
                System.err.println("La réponse est null. Impossible de la supprimer.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @FXML
    void editRep(MouseEvent event) {

    }
}
