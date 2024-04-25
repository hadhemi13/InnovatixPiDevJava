package controllers.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceUser;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class UsersListController implements Initializable {
    @FXML
    private HBox content_area;

    @FXML
    private ComboBox<String> roleInput;

    @FXML
    private VBox userListContainer;


    @FXML
    private Text userListTitle;

    @FXML
    private Pane userPane;

    @FXML
    private HBox userTableHead;
    private static int updateUserModelShow = 0;
    private static String userEmailToUpdate = "";
    private static int filter = 0;
    public static int getupdateUserModelShow() {
        return updateUserModelShow;
    }

    public static void setupdateUserModelShow(int updateUserModelShow) {
        UsersListController.updateUserModelShow = updateUserModelShow;
    }

    public static String getuserToUpdate() {
        return userEmailToUpdate;
    }

    public static void setuserEmailToUpdate(String userEmailToUpdate) {
        UsersListController.userEmailToUpdate = userEmailToUpdate;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUser projectService = new ServiceUser();
        List<User> list = new ArrayList<>();
        try {
            list = projectService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (User offre : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/TEST.fxml"));
                Parent offreItem = loader.load();
                TESTCONTROLLER offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void toAdduser(ActionEvent actionEvent)  throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AjoutUser.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjoutUserController ajouterUSER = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        userPane.getChildren().clear();
        userPane.getChildren().add(addArticleParent);
    }


}
