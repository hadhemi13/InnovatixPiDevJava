package controllers.Compte;

import Entities.Compte;
import Entities.Compte;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Cheque.updateChequeCard;
import controllers.Compte.CompteItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;
import services.ServiceCompte;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCompteAdmin  implements Initializable {

    @FXML
    private Text Cin;

    @FXML
    private VBox CompteContainer;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text DatedelCin;

    @FXML
    private Text Email;

    @FXML
    private Text Montant;

    @FXML
    private Text Nom;

    @FXML
    private Text NumTel;

    @FXML
    private Text Prenom;

    @FXML
    private Text Profession;

    @FXML
    private Text Sexe;

    @FXML
    private HBox ShowCompteDetails;

    @FXML
    private VBox ShowContent;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;

    @FXML
    private Pane content_area;

    @FXML
    private Text nameInputError;

    @FXML
    private HBox nameInputErrorHbox;

    @FXML
    private Button retour;
    @FXML
    private VBox ShowComteContent;


    @FXML
    private HBox updateBtnContainer;

    private static ListeCompteAdmin instance;
    public ListeCompteAdmin() {
        instance = this;
    }
    private static int compteDetailsModelShow=0;
    public static ListeCompteAdmin getInstance() {
        return instance;
    }



    public static void setcompteDetailsModelShow(int compteDetailsModelShow) {
        ListeCompteAdmin.compteDetailsModelShow = compteDetailsModelShow;
    }
    public static int getcompteDetailsModelShow() {
        return compteDetailsModelShow;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCompte serviceCompte = new ServiceCompte();
        List<Compte> allComptes = new ArrayList<>();
        List<Compte> filteredComptes = new ArrayList<>();
        try {
            allComptes = serviceCompte.afficher();
            for (Compte compte : allComptes) {
                if (!"Approuvé".equals(compte.getStatut())) {
                    filteredComptes.add(compte);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        for (Compte compte : filteredComptes) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent offreItem = loader.load();
                CompteItems offreStageItem = loader.getController();
                offreStageItem.initData(compte);
                CompteContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ListeCompteAdmin.getcompteDetailsModelShow() == 0) {
            ShowCompteDetails.setVisible(false);
        } else if (ListeCompteAdmin.getcompteDetailsModelShow() == 1) {
            ShowCompteDetails.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/ShowDetailsCompte.fxml.fxml"));
            VBox updateProjectform;
            try {
                updateProjectform = fxmlLoader1.load();
                updateChequeCard updateUserCardController = fxmlLoader1.getController();
                updateChequeCard.setFxmlToLoad("ListeCompteAdmin.fxml");

                ShowComteContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refreshCompteList() {
        CompteContainer.getChildren().clear();  // Clear the existing views
        List<Compte> filteredComptes = new ArrayList<>();
        try {
            List<Compte> allComptes = new ServiceCompte().afficher();
            for (Compte compte : allComptes) {
                if (!"Approuvé".equals(compte.getStatut())) {
                    filteredComptes.add(compte);
                }

            }

            for (Compte compte : filteredComptes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent item = loader.load();
                CompteItems controller = loader.getController();
                controller.initData(compte);
                CompteContainer.getChildren().add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    public void returnbackCard(MouseEvent mouseEvent) {
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

    public void CloseDetails(MouseEvent mouseEvent) {
        ShowCompteDetails.setVisible(false);
        compteDetailsModelShow = 0;
    }
}
