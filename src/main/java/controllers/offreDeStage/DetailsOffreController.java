package controllers.offreDeStage;

import Entities.OffreDeStage;
import controllers.demandeStage.DemandeStageController;
import controllers.demandeStage.DemandeStageParOffreController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsOffreController {

    @FXML
    private Text Description;

    @FXML
    private Text typeOffre;

    @FXML
    private Text Niveau;

    @FXML
    private Text Exigence;

    @FXML
    private Text poste;

    @FXML
    private Text langue;

    @FXML
    private Text experience;

    @FXML
    private Button Postuler;

    @FXML
    private Text MotCle;

    @FXML
    private Text datet1;

    @FXML
    private HBox DatePost;

    @FXML
    private Text productName;


    public void initData(OffreDeStage offreDeStage) {
        Description.setText(offreDeStage.getDescription());
        typeOffre.setText(offreDeStage.getTypeOffre());
        Niveau.setText(String.valueOf(offreDeStage.getNiveau()));
        Exigence.setText(offreDeStage.getExigenceOffre());
        poste.setText(String.valueOf(offreDeStage.getPostePropose()));
        datet1.setText(String.valueOf(offreDeStage.getDatePostu()));
        Postuler.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeStageParOffre.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            primaryStage.setTitle("E-Flex Bank");
            primaryStage.setScene(scene);
            primaryStage.show();
            DemandeStageParOffreController demandeStageController = new DemandeStageParOffreController();
//            System.out.println(offreDeStage);
            demandeStageController.initData(offreDeStage);
            demandeStageController.yy=offreDeStage.getPostePropose();

        });


    }
}
