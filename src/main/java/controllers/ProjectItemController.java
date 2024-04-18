package controllers;

import Entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import services.ProjectService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProjectItemController {

    @FXML
    private Text ItemnomProjet;

    @FXML
    private ImageView Itemimg;


  @FXML
  private HBox deleteProject;
    @FXML
    private Text Itemcategorie;

    @FXML
    private Text ItemdescriptionProjet;

    @FXML
    private Text ItemBudget;

    @FXML
    private Text ItemDateCreation;
    @FXML
    private Text ItemDureeProjet;
    @FXML
    private Text ItemStatutProjet;
    @FXML
    private Label ItembudgetProjet;

    @FXML
    private ImageView ItemdateCreation;

    @FXML
    private Label ItemdureeProjet;

    @FXML
    private Text ItemstatutProjet;

    @FXML
    private Text ItemTel;

    @FXML
    private Label ItemUpdateBtn;
    @FXML
    private Label ItemAddBtn;

    @FXML
    private ImageView projectItemUpdateBtnImg;

    public void setprojectData(Project project) {
        ProjectService projectService = new ProjectService();
        Image image = new Image(
                getClass().getResource("../assets/projectUploads/" + project.getImg()).toExternalForm());
        Itemimg.setImage(image);
        Rectangle clip = new Rectangle(
                Itemimg.getFitWidth(), Itemimg.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        Itemimg.setClip(clip);
        ItemnomProjet.setText(project.getNomProjet());
        Itemcategorie.setText(project.getCategorie());
        ItemdescriptionProjet.setText(project.getDescriptionProjet());
        ItemBudget.setText(String.valueOf(project.getBudgetProjet()));
        LocalDateTime dateCreation = project.getDateCreation();
        ItemDateCreation.setText(dateCreation != null ? dateCreation.toString() : "");
//      if (project.getState()) {
//
//            if (!projectItemStateLabel.getStyleClass().contains("projectItem__field-active")) {
//                projectItemStateLabel.getStyleClass().add("projectItem__field-active");
//            }
//
//            if (projectItemStateLabel.getStyleClass().contains("projectItem__field-unactive")) {
//                projectItemStateLabel.getStyleClass().remove("projectItem__field-unactive");
//            }
//
//            projectItemStateText.setText("active");
//
//            Image stateBtnImg = new Image(
//                    getClass().getResource("/assets/img/lock-icon.png").toExternalForm());
//            projectItemStateBtnImg.setImage(stateBtnImg);
//
//        } else {
//            if (!projectItemStateLabel.getStyleClass().contains("projectItem__field-unactive")) {
//                projectItemStateLabel.getStyleClass().add("projectItem__field-unactive");
//            }
//
//            if (projectItemStateLabel.getStyleClass().contains("projectItem__field-active")) {
//                projectItemStateLabel.getStyleClass().remove("projectItem__field-active");
//            }
//            projectItemStateText.setText("unactive");
//
//            Image stateBtnImg = new Image(
//                    getClass().getResource("/assets/img/unlock-icon.png").toExternalForm());
//            projectItemStateBtnImg.setImage(stateBtnImg);
//
//        }

      // deleteProject btn click
      deleteProject.setId(String.valueOf(project.getId()));

      deleteProject.setOnMouseClicked(event -> {
        System.out.println("ID du Evenement à supprimer : " + project.getId());
        try {
          projectService.supprimer(project.getId());
          TrayNotificationAlert.notif("Project", "project deleted successfully.",
            NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
        } catch (SQLException e) {
          e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProjectsList.fxml"));
        try {
          Parent root = loader.load();
          // Accéder à la pane content_area depuis le controller de
          // OneProductListCard.fxml
          Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

          // Vider la pane et afficher le contenu de ProductsList.fxml
          contentArea.getChildren().clear();
          contentArea.getChildren().add(root);
        } catch (IOException e) {
          e.printStackTrace();
        }
        // end
      });
       ItemUpdateBtn.setOnMouseClicked(event -> {
            System.out.println("project Name: " + project.getNomProjet());

            ProjectListController.setupdateProjectModelShow(1);
            ProjectListController.setprojectEmailToUpdate(project.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProjectsList.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
//      ItemAddBtn.setOnMouseClicked(event -> {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectsList.fxml"));
//        try {
//          Parent root = loader.load();
//          Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
//
//          // Vider la pane et afficher le contenu de ProjectsList.fxml
//          contentArea.getChildren().clear();
//          contentArea.getChildren().add(root);
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      });

    }

    public void updateStateLabel(Project project) {
//        if (project.getState()) {
//
//            if (!projectItemStateLabel.getStyleClass().contains("projectItem__field-active")) {
//                projectItemStateLabel.getStyleClass().add("projectItem__field-active");
//            }
//
//            if (projectItemStateLabel.getStyleClass().contains("projectItem__field-unactive")) {
//                projectItemStateLabel.getStyleClass().remove("projectItem__field-unactive");
//            }
//
//            projectItemStateText.setText("active");
//
//            Image stateBtnImg = new Image(
//                    getClass().getResource("/assets/img/lock-icon.png").toExternalForm());
//            projectItemStateBtnImg.setImage(stateBtnImg);
//
//        } else {
//            if (!projectItemStateLabel.getStyleClass().contains("projectItem__field-unactive")) {
//                projectItemStateLabel.getStyleClass().add("projectItem__field-unactive");
//            }
//
//            if (projectItemStateLabel.getStyleClass().contains("projectItem__field-active")) {
//                projectItemStateLabel.getStyleClass().remove("projectItem__field-active");
//            }
//            projectItemStateText.setText("unactive");
//
//            Image stateBtnImg = new Image(
//                    getClass().getResource("/assets/img/unlock-icon.png").toExternalForm());
//            projectItemStateBtnImg.setImage(stateBtnImg);
//
//        }
    }
}
