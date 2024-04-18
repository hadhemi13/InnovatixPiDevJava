package controllers;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.collections.ObservableList;


public class UsersListController {

    @FXML
    private ComboBox<?> roleInput;

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


    public void initialize(URL location, ResourceBundle resources) {
        ServiceUser userService = new ServiceUser();
        User userToUpdate;

        // System.out.println(userEmailToUpdate);
       /* if (UsersListController.getupdateUserModelShow() == 0) {
            updateUserModel.setVisible(false);
        } else if (UsersListController.getupdateUserModelShow() == 1) {
            updateUserModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("updateUserCard.fxml"));
            VBox updateUserform;
            try {
                updateUserform = fxmlLoader1.load();
                UpdateUserCardController updateUserCardController = fxmlLoader1.getController();
                UpdateUserCardController.setFxmlToLoad("UsersList.fxml");
                userToUpdate = userService.getOneUser(userEmailToUpdate);

                updateUserCardController.setUserUpdateData(userToUpdate);
                updateUserModelContent.getChildren().add(updateUserform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        try {
            ArrayList<User> userList;
            if (filter == 0) {
                userList = userService.getAllUser();
            } else if (filter == 1) {
                userList = userService.getAllAdmin();
            } else {
                userList = userService.getAllAdmin();
            }
            // ArrayList<User> userList = userService.getAllUser();
            for (int i = 0; i < userList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("UserItem.fxml"));
                HBox userItem = fxmlLoader.load();
                TESTCONTROLLER userItemController = fxmlLoader.getController();
                userItemController.setUserData(userList.get(i));
                userListContainer.getChildren().add(userItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  ObservableList<String> items = roleInput.getItems();

        // Add new items to the list
        items.addAll("tout", "Admin", "client");

        if (filter == 0) {
            roleInput.setValue("tout");
        } else if (filter == 1) {
            roleInput.setValue("Admin");
        } else {
            roleInput.setValue("client");
        }*/
    }


}
