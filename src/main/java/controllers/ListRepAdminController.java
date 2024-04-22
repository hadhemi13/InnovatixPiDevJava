package controllers;

import Entities.Reclamation;
import Entities.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceReclamation;
import services.ServiceReponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListRepAdminController implements Initializable {


    @FXML
    private ImageView backBtn;

    @FXML
    private Pane content_area;

    @FXML
    private VBox repListContainer;

    @FXML
    private Text repListTitle;

    @FXML
    private HBox repTableHead;

    @FXML
    private ComboBox<?> trierepInput;
    @FXML
    void returnBack(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRecAdmin.fxml"));
            Pane listRecAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRecAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceReponse sr = new ServiceReponse() ;
        List<Reponse> list = new ArrayList<>();
        try {
            list = sr.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (Reponse reponse : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reponseItemAdmin.fxml"));
                Parent offreItem = loader.load();
                ReponseItemAdminController RepItem = loader.getController();
                RepItem.initData(reponse);
                repListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
