package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import services.ServiceOffreDeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Recrutement implements Initializable {

    @FXML
    private VBox ListeOffre;

    @FXML
    private ComboBox<String> filtrage;

    @FXML
    private Button search;

    @FXML
    private Button DemandeSansOffre;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceOffreDeStage projectService = new ServiceOffreDeStage();
        List<OffreDeStage> list = new ArrayList<>();
        try {
            list = projectService.afficherId();
            for (OffreDeStage i : list){
//                    System.out.println(i.getId());
            }
//                System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (OffreDeStage offre : list) {
            try {
//                ListeOffre.setSpacing(0.5);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/offreDeStage/OffreStageVisiteurItem.fxml"));
                Parent offreItem = loader.load();
                OffreStageVisiteurItemController offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                ListeOffre.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void searsh(ActionEvent event) {

    }

    @FXML
    void demandeSansOffre(ActionEvent event) {

    }

    @FXML
    void filtrage(ActionEvent event) {

    }

}
