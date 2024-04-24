package controllers.Credit;

import Entities.Cheque;
import Entities.Credit;
import controllers.Cheque.ChequeItems;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ServiceCheque;
import services.ServiceCredit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeCreditListClientUser  implements Initializable {

    @FXML
    private TextField ChequeSearch;

    @FXML
    private Button chequebtn;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> statusInput;

    @FXML
    private GridPane userListContainer;

    @FXML
    private HBox userTableHead;

    @FXML
    void AjouterC(MouseEvent event) {

    }

    @FXML
    void SearchCheque(KeyEvent event) {

    }

    @FXML
    void statusChange(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCredit serviceCredit = new ServiceCredit();
        List<Credit> list = new ArrayList<>();
        try {
            list = serviceCredit.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       loadcredit(list);
    }
    private void loadcredit(List<Credit> credits) {
        // Nettoyer le conteneur actuel
        userListContainer.getChildren().clear();

        // Réinitialiser les valeurs de la ligne et de la colonne
        int row = 0;
        int column = 0;
        int maxColumns = 3; // Nombre maximum de colonnes par ligne
        // Espacement entre les cartes
        double verticalGap = 12; // Environ 9 mm
        double horizontalGap = 12; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 10; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        userListContainer.setVgap(verticalGap);
        userListContainer.setHgap(horizontalGap);


        // Parcourir chaque chèque et charger sa carte dans le conteneur
        for (Credit credit : credits) {
            try {
                // Charger la carte de chèque à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/credititemsuser.fxml"));
                Parent chequeItem = loader.load();

                // Récupérer le contrôleur de la carte de chèque
                credititemsuser creditItems = loader.getController();

                // Initialiser les données du chèque dans la carte de chèque
                creditItems.initData(credit);

                // Ajouter la carte de chèque au conteneur
                userListContainer.add(chequeItem, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }
    }

}
