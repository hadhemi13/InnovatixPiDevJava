package controllers.offreDeStage;

import Entities.OffreDeStage;
import controllers.offreDeStage.DetailsOffreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.sql.SQLException;

public class OffreStageVisiteurItemController {

    @FXML
    private Text date;

    @FXML
    private Text Titre;

    @FXML
    private Text Description;

    @FXML
    private Button VoirDetail;

    public void initData(OffreDeStage offreDeStage){
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
        VoirDetail.setId(String.valueOf(offreDeStage.getPostePropose()));
        date.setText(String.valueOf(offreDeStage.getDatePostu()));
        Description.setText(offreDeStage.getDescription());
        Titre.setText(offreDeStage.getTitle());

        VoirDetail.setOnMouseClicked(mouseEvent -> {
            try {
                OffreDeStage offreDeStage1 = serviceOffreDeStage.afficheUne(Integer.parseInt(VoirDetail.getId()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/DetailsOffre.fxml"));
                Parent parent = loader.load();
                DetailsOffreController detailsOffreController = loader.getController();
                detailsOffreController.initData(offreDeStage1);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
