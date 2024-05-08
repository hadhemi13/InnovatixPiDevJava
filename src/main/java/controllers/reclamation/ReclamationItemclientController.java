package controllers.reclamation;

import Entities.actualites.Reclamation;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.ServiceReclamation;

public class ReclamationItemclientController {

    @FXML
    private Text ObjetRec;

    @FXML
    private Text contenuArt;

    @FXML
    private HBox contenuRec;

    @FXML
    private Text dateRec;

    @FXML
    private ImageView deleteRecBtn;

    @FXML
    private Text depRec;

    @FXML
    private ImageView editRecBtn;

    @FXML
    private HBox objetrec;

    @FXML
    private ImageView statRec;

    @FXML
    private ImageView telechargerPieceBtn;

    @FXML
    private ImageView voirPieceBtn;


    public void initData(Reclamation reclamation) {
        ServiceReclamation serviceReclamation =new ServiceReclamation() ;
        Image image = new Image(getClass().getResource("/imagesAct/admin.png").toExternalForm());
        statRec.setImage(image);
        Rectangle clip = new Rectangle(
                statRec.getFitWidth(), statRec.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        statRec.setClip(clip);
//        RecItemPieceJ.setText(reclamation.getPiece_jrec());
        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
        voirPieceBtn.setImage(imageJ);
        dateRec.setText(String.valueOf(reclamation.getDate_rec()));
      //  RecItemStateText.setText(reclamation.getStatut_rec());
        depRec.setText(reclamation.getDep_rec());
        ObjetRec.setText(reclamation.getObjet_rec());
        //RecItemMail.setText(reclamation.getAdr_rec());
        //RecItemPieceJ.setOnMouseClicked(this::openArticleImage);




    }


    private Reclamation reclamation;

    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

}
