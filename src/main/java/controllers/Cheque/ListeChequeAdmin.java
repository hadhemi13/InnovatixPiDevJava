package controllers.Cheque;

import Entities.Cheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

public class ListeChequeAdmin implements Initializable {


    @FXML
    private VBox ChequeContainer;
    @FXML
    private Pane content_area;
    @FXML
    private TextField ChequeclientsfSearchInputAdmin;

    private static ListeChequeAdmin instance;

    public ListeChequeAdmin() {
        instance = this;
    }

    public static ListeChequeAdmin getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> allCheques = new ArrayList<>();
        List<Cheque> filteredCheques = new ArrayList<>();
        try {
            allCheques = serviceCheque.afficher(); // Fetch all cheques from the database
            // Filter out cheques that are "Approuvé"
            for (Cheque cheque : allCheques) {
                if (!"Approuvé".equals(cheque.getDecision())) {
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
    // ListeChequeAdmin.java

    public void refreshChequeList() {
        ChequeContainer.getChildren().clear();  // Clear the existing views
        List<Cheque> filteredCheques = new ArrayList<>();
        try {
            List<Cheque> allCheques = new ServiceCheque().afficher();
            for (Cheque cheque : allCheques) {
                if (!"Approuvé".equals(cheque.getDecision())) {
                    filteredCheques.add(cheque);
                }

            }

            for (Cheque cheque : filteredCheques) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent item = loader.load();
                ChequeItemsAdmin controller = loader.getController();
                controller.initData(cheque);
                ChequeContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    public void returnbackCardCH(MouseEvent mouseEvent) {

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

    public void ChequeclientsfSearchInputAdmin(KeyEvent keyEvent) {
    }
}
