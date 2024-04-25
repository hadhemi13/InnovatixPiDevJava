package controllers.Cheque;

import Entities.Cheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoriqueChequeAdmin implements Initializable {

    @FXML
    private VBox ChequeContainer;

    @FXML
    private Pane content_area;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> allCheques = new ArrayList<>();
        List<Cheque> filteredCheques = new ArrayList<>();
        try {
            allCheques = serviceCheque.afficher();
            // Filtering for only "Approuvé" cheques
            for (Cheque cheque : allCheques) {
                if ("Approuvé".equals(cheque.getDecision())) {
                    filteredCheques.add(cheque);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Cheque cheque : filteredCheques) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                ChequeItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(cheque);
                ChequeContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ReturnBackHisT(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
