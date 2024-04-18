package controllers.Cheque;

import Entities.Cheque;
import controllers.Cheque.AjouterChequeCard;
import controllers.Virement.AjouterVirementCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeChequeListClient {


    @FXML
    private Button chequebtn;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> statusInput;

    @FXML
    private VBox userListContainer;

    @FXML
    private HBox userTableHead;

    @FXML
    void AjouterC(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormCradCheque.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterChequeCard ajouterChequeCard = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void statusChange(ActionEvent event) {
    }
}
    

    
   // @Override
    //public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  ServiceCheque serviceOffreDeStage = new ServiceCheque();
        Cheque offreDeStage;
        try {
            List<Cheque> lisOffre = serviceOffreDeStage.afficher();

            for (int i =0; i <lisOffre.size() ; i++){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FXML/ChequeItems.fxml"));
                VBox offreItem = loader.load();
                ChequeItems offreStageItem = loader.getController();
                offreStageItem.initData(lisOffre.get(i));
                userListContainer.getChildren().add(offreItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/



