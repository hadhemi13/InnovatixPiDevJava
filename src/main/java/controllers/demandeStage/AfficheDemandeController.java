package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.ServiceDemandeStage;

import java.sql.SQLException;
import java.util.List;

public class AfficheDemandeController {

    @FXML
    private ListView<String> listView;
    @FXML
    private Button refresh;
    private ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();

    @FXML
    void refreshList(ActionEvent event) {
        try {
            List<DemandeStage> demandeStages = serviceDemandeStage.afficher();
            listView.getItems().clear();
            for (DemandeStage i : demandeStages){
                StringBuilder sb = new StringBuilder();
                sb.append("Nom : ").append(i.getNom()).append("\n");
                sb.append("Prenom : ").append(i.getPrenom()).append("\n");

                listView.getItems().add(sb.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
