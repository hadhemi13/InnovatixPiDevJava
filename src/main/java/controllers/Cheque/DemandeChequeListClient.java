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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DemandeChequeListClient  implements  Initializable {


    @FXML
    private Button chequebtn;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> statusInput;

    @FXML
    private GridPane ChequeListContainer;
    @FXML
    private GridPane userListContainer;


    @FXML
    private HBox userTableHead;
    private Cheque cheque;
    private Throwable e;


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

//


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceCheque serviceCheque = new ServiceCheque();
//        List<Cheque> list = new ArrayList<>();
//        try {
//            list = serviceCheque.afficher(); // Notez le changement ici
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (Cheque cheque : list) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent offreItem = loader.load();
//                ChequeItems offreStageItem = loader.getController();
//                offreStageItem.initData(cheque);
//                userListContainer.getChildren().add(offreItem);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> list = new ArrayList<>();
        try {
            list = serviceCheque.afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadCheques(list);
    }

    private void loadCheques(List<Cheque> cheques) {
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
        for (Cheque cheque : cheques) {
            try {
                // Charger la carte de chèque à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
                Parent chequeItem = loader.load();

                // Récupérer le contrôleur de la carte de chèque
                ChequeItems chequeItemController = loader.getController();

                // Initialiser les données du chèque dans la carte de chèque
                chequeItemController.initData(cheque);

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


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ServiceCheque serviceCheque = new ServiceCheque();
//        List<Cheque> list = new ArrayList<>();
//        try {
//            list = serviceCheque.afficher(); // Notez le changement ici
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        loadCheques(list);
//
////        for (Cheque cheque : list) {
////            try {
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
////                Parent offreItem = loader.load();
////                ChequeItems offreStageItem = loader.getController();
////                offreStageItem.initData(cheque);
////                userListContainer.getChildren().add(offreItem);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//    }
//
//
//    private void loadCheques(List<Cheque> cheques) {
//        // Nettoyer le conteneur actuel
//        userListContainer.getChildren().clear();
//
//        // Réinitialiser les valeurs de la ligne et de la colonne
//        int row = 0;
//        int column = 0;
//        int maxColumns = 3; // Nombre maximum de colonnes par ligne
//
//        // Parcourir chaque chèque et charger sa carte dans le conteneur
//        for (Cheque cheque : cheques) {
//            try {
//                // Charger la carte de chèque à partir du fichier FXML
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent chequeItem = loader.load();
//
//                // Récupérer le contrôleur de la carte de chèque
//                ChequeItems chequeItemController = loader.getController();
//
//                // Initialiser les données du chèque dans la carte de chèque
//                chequeItemController.initData(cheque);
//
//                // Ajouter la carte de chèque au conteneur
//                userListContainer.getChildren().add(chequeItem);
//
//                // Incrémenter le nombre de colonnes
//                column++;
//
//                // Vérifier si nous avons atteint le nombre maximum de colonnes par ligne
//                if (column >= maxColumns) {
//                    // Réinitialiser la colonne à zéro et passer à la ligne suivante
//                    column = 0;
//                    row++;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Gérer l'exception ici, si nécessaire
//            }
//        }
//    }




//    private void loadCheques() {
//        userListContainer.getChildren().clear();
//
//        ServiceCheque serviceCheque = new ServiceCheque();
//        try {
//            List<Cheque> list = serviceCheque.afficher();
//            int columnCount = 0;
//            VBox column = new VBox();
//            for (Cheque cheque : list) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//                Parent chequeItem = loader.load();
//                ChequeItems chequeItemController = loader.getController();
//                chequeItemController.initData(cheque);
//                column.getChildren().add(chequeItem);
//                columnCount++;
//                if (columnCount == 3) {
//                    userListContainer.getChildren().add(column);
//                    column = new VBox();
//                    columnCount = 0;
//                }
//            }
//            // Ajouter la dernière colonne si elle contient moins de 3 cartes
//            if (columnCount > 0) {
//                userListContainer.getChildren().add(column);
//            }
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//private void loadCheques(List<Cheque> cheques) {
//    // Nettoyer le conteneur actuel
//    userListContainer.getChildren().clear();
//
//    // Réinitialiser les valeurs de la ligne et de la colonne
//    int row = 0;
//    int column = 0;
//    int maxColumns = 3; // Nombre maximum de colonnes par ligne
//
//    // Parcourir chaque chèque et charger sa carte dans le conteneur
//    for (Cheque cheque : cheques) {
//        try {
//            // Charger la carte de chèque à partir du fichier FXML
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItems.fxml"));
//            GridPane chequeItem = loader.load();
//
//            // Récupérer le contrôleur de la carte de chèque
//            ChequeItems chequeItemController = loader.getController();
//
//            // Initialiser les données du chèque dans la carte de chèque
//            chequeItemController.initData(cheque);
//
//            // Ajouter la carte de chèque au conteneur
//            userListContainer.add(chequeItem, column, row);
//
//            // Incrémenter la colonne
//            column++;
//
//            // Vérifier si nous devons passer à la ligne suivante
//            if (column >= maxColumns) {
//                column = 0;
//                row++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Gérer l'exception ici, si nécessaire
//        }
//    }
//}
//




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