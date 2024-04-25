package controllers.user;

import Entities.User;
import controllers.SideNavBarController;
import controllers.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class yesser implements Initializable {

    @FXML
    private Button hh;
    @FXML
    void test(ActionEvent event) {
        UserSession userSession = new UserSession();
        System.out.println(userSession.email);
        if (userSession.email == null){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            ServiceUser serviceUser  = new ServiceUser();
            try {
                User user = serviceUser.getOneUser(userSession.email);
                SideNavBarController sideNavBarController = new SideNavBarController();
                sideNavBarController.initData(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            ServiceUser serviceUser  = new ServiceUser();
            try {
                User user = serviceUser.getOneUser(userSession.email);
                SideNavBarController sideNavBarController = new SideNavBarController();
                sideNavBarController.initData(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}