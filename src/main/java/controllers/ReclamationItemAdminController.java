package controllers;

import Entities.Reclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationItemAdminController implements Initializable {

    @FXML
    private Label RecItemDateLabel;

    @FXML
    private Text RecItemDateText;

    @FXML
    private Text RecItemDepText;

    @FXML
    private ImageView RecItemImg;

    @FXML
    private Text RecItemMail;

    @FXML
    private Text RecItemObjet;

    @FXML
    private Label RecItemPieceJLabel;

    @FXML
    private Text RecItemPieceJText;

    @FXML
    private ImageView RecItemStateBtnImg;

    @FXML
    private Label RecItemStateLabel;

    @FXML
    private Text RecItemStateText;

    @FXML
    private Label RecItemUpdateBtn;

    @FXML
    private Text RecItemcontenu;

    @FXML
    private Label RecPieceJBtn;

    @FXML
    private ImageView RecReplyBtnImg;

    @FXML
    private ImageView RecdetailBtnImg;

    @FXML
    private Label RecrItemStateBtn;

    @FXML
    private Label ReplyBtn;
    @FXML
    private ImageView RecItemPieceJ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Reclamation reclamation) {
        Image image = new Image(getClass().getResource("/imagesAct/admin.png").toExternalForm());
        RecItemImg.setImage(image);
        Rectangle clip = new Rectangle(
                RecItemImg.getFitWidth(), RecItemImg.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        RecItemImg.setClip(clip);
//        RecItemPieceJ.setText(reclamation.getPiece_jrec());
        Image imageJ = new Image(getClass().getResource("/imagesAct/attach.png").toExternalForm());
        RecItemPieceJ.setImage(imageJ);
        RecItemDateText.setText(String.valueOf(reclamation.getDate_rec()));
        RecItemStateText.setText(reclamation.getStatut_rec());
        RecItemDepText.setText(reclamation.getDep_rec());
        RecItemObjet.setText(reclamation.getObjet_rec());
        RecItemMail.setText(reclamation.getAdr_rec());

    }
}
