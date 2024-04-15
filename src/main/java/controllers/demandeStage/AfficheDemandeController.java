package controllers.demandeStage;

import Entities.DemandeStage;
//import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.ServiceDemandeStage;

import java.sql.SQLException;
import java.util.List;

public class AfficheDemandeController {

    @FXML
    private ListView<String> listViewNom;
    @FXML
    private Button refresh;
    @FXML
    private ListView<String> listViewPrenom;
    private ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();


    @FXML
    void refreshList(ActionEvent event) {
        try {
            List<DemandeStage> demandeStages = serviceDemandeStage.afficher();
            listViewNom.getItems().clear();
            for (DemandeStage i : demandeStages){
                StringBuilder nom = new StringBuilder();
                StringBuilder prenom = new StringBuilder();

                nom.append("Nom : ").append(i.getNom()).append("\n");
                prenom.append("Prenom : ").append(i.getPrenom()).append("\n");

                listViewNom.getItems().add(nom.toString());
                listViewPrenom.getItems().add(prenom.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
