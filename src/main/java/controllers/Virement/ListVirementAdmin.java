package controllers.Virement;

import Entities.Cheque;
import Entities.Compte;
import Entities.Virement;
import controllers.Cheque.ChequeItemsAdmin;
import controllers.Cheque.ListeChequeAdmin;
import controllers.Compte.CompteItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceCompte;
import services.ServiceVirement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListVirementAdmin  implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private Button retourVir;

    @FXML
    private VBox VireContainer;
    private static ListVirementAdmin instance;

    public ListVirementAdmin() {
        instance = this;
    }
    public static ListVirementAdmin getInstance(){return  instance ; }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> allVirements = new ArrayList<>();
        List<Virement> filtredVirements = new ArrayList<>();

        try {
            allVirements = serviceVirement.afficher(); // Fetch all cheques from the database
            // Filter out cheques that are "Approuvé"
            for (Virement virement : allVirements) {
                if (!"Approuvé".equals(virement.getDecision_v())) {
                    filtredVirements.add(virement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Virement virement : filtredVirements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                VirementItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(virement);
                VireContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refreshVirementList() {
        VireContainer.getChildren().clear();  // Clear the existing views
        List<Virement> filtredVirements = new ArrayList<>();
        try {
            List<Virement> allVirements = new ServiceVirement().afficher();
            for (Virement virement : allVirements) {
                if (!"Approuvé".equals(virement.getDecision_v())) {
                    filtredVirements.add(virement);
                }

            }

            for (Virement virement : filtredVirements) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent item = loader.load();
                VirementItemsAdmin controller = loader.getController();
                controller.initData(virement);
                VireContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }



        @FXML
        void returnBackVir (MouseEvent event){

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

