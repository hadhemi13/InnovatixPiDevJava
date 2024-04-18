package controllers;

import Entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceProjet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectListController implements Initializable {

    @FXML
    private HBox updateProjectModel;

    @FXML
    private VBox updateProjectModelContent;

  @FXML
  private HBox addProjectModel;

  @FXML
  private VBox addProjectModelContent;

    @FXML
    private VBox projectListContainer;

    @FXML
    private Text projectListTitle;

    @FXML
    private Pane projectPane;

    @FXML
    private HBox projectTableHead;
    private static int updateProjectModelShow = 0;
    private static int addProjectModelShow = 0;
    private static int projetIdToUpdate = 0;
    private static int filter = 0;

    @FXML
    private Pane content_area;
    public static int getupdateProjectModelShow() {
        return updateProjectModelShow;
    }

    public static void setupdateProjectModelShow(int updateProjectModelShow) {
        ProjectListController.updateProjectModelShow = updateProjectModelShow;
    }

  public static int getaddProjectModelShow() {
    return updateProjectModelShow;
  }



    @FXML
    private void open_ProjectList(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/ProjectsList.fxml"));

    }
  public static void setaddProjectModelShow(int addProjectModelShow) {
    ProjectListController.addProjectModelShow = addProjectModelShow;
  }


    public static void setprojectEmailToUpdate(int projetIdToUpdate) {
        ProjectListController.projetIdToUpdate = projetIdToUpdate;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProjet projectService = new ServiceProjet();
        Project project;

        // System.out.println(projectEmailToUpdate);
        if (ProjectListController.getupdateProjectModelShow() == 0) {
            updateProjectModel.setVisible(false);
        } else if (ProjectListController.getupdateProjectModelShow() == 1) {
            updateProjectModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/updateProjectCard.fxml"));
            VBox updateProjectform;
            try {
              updateProjectform = fxmlLoader1.load();
              UpdateProjectCardController updateUserCardController = fxmlLoader1.getController();
              UpdateProjectCardController.setFxmlToLoad("ProjectsList.fxml");
              project = projectService.getOneProject(projetIdToUpdate);

              updateUserCardController.setProjectUpdateData(project);
              updateProjectModelContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
              e.printStackTrace();
            }
        }
        try {
            ArrayList<Project> projectList;
                projectList = (ArrayList<Project>) projectService.getAllProject();
            // ArrayList<Project> projectList = projectService.getAllProject();
            for (int i = 0; i < projectList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FXML/ProjectItem.fxml"));
                HBox projectItem = fxmlLoader.load();
                ProjectItemController projectItemController = fxmlLoader.getController();
                projectItemController.setprojectData(projectList.get(i));
                projectListContainer.getChildren().add(projectItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

      // System.out.println(projectEmailToUpdate);
      if (ProjectListController.getaddProjectModelShow() == 0) {
        addProjectModel.setVisible(false);
      } else if (ProjectListController.getaddProjectModelShow() == 1) {
        addProjectModel.setVisible(true);
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/FXML/updateProjectCard.fxml"));
        VBox updateProjectform;
        try {
          updateProjectform = fxmlLoader1.load();
          UpdateProjectCardController addUserCardController = fxmlLoader1.getController();
          UpdateProjectCardController.setFxmlToLoad("ProjectsList.fxml");
          project = projectService.getOneProject(projetIdToUpdate);

          addUserCardController.setProjectUpdateData(project);
          addProjectModelContent.getChildren().add(updateProjectform);
        } catch (IOException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      try {
        ArrayList<Project> projectList;
        projectList = (ArrayList<Project>) projectService.getAllProject();
        // ArrayList<Project> projectList = projectService.getAllProject();
        for (int i = 0; i < projectList.size(); i++) {
          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(getClass().getResource("/FXML/ProjectItem.fxml"));
          HBox projectItem = fxmlLoader.load();
          ProjectItemController projectItemController = fxmlLoader.getController();
          projectItemController.setprojectData(projectList.get(i));
          projectListContainer.getChildren().add(projectItem);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    @FXML
    void close_updateProjectModel(MouseEvent event) {
        updateProjectModel.setVisible(false);
        updateProjectModelShow = 0;
    }

  @FXML
  private void open_addProject(MouseEvent event) throws IOException {
    Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/AddProject.fxml"));
    content_area.getChildren().removeAll();
    content_area.getChildren().setAll(fxml);

  }
}
