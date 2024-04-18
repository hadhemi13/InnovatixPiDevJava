package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import Entities.User;
import services.ServiceUser;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.awt.*;

public class TESTCONTROLLER {

    @FXML
    private Text userItemEmail;

    @FXML
    private ImageView userItemImg;

    @FXML
    private Text userItemName;

    @FXML
    private Text userItemRole;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Label userItemStateLabel;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;
    public void setUserData(User user) {
        ServiceUser userService = new ServiceUser();
        Image image = new Image(
                getClass().getResource("/FXML/img/" + user.getPhoto()).toExternalForm());
        userItemImg.setImage(image);

        Rectangle clip = new Rectangle(
                userItemImg.getFitWidth(), userItemImg.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);

        userItemImg.setClip(clip);

        userItemName.setText(user.getName());
        userItemEmail.setText(user.getEmail());
        userItemTel.setText(user.getTel());
        if (user.getRoles().equals("[\"ROLE_CLIENT\"]")) {
            userItemRole.setText("client");
        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
            userItemRole.setText("admin");
        }
      /*  if (user.getIs_verified()) {

            if (!userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().add("userItem__field-active");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-unactive");
            }

            userItemStateText.setText("active");

            Image stateBtnImg = new Image(
                    getClass().getResource("/img/lock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        } else {
            if (!userItemStateLabel.getStyleClass().contains("userItem__field-unactive")) {
                userItemStateLabel.getStyleClass().add("userItem__field-unactive");
            }

            if (userItemStateLabel.getStyleClass().contains("userItem__field-active")) {
                userItemStateLabel.getStyleClass().remove("userItem__field-active");
            }
            userItemStateText.setText("unactive");

            Image stateBtnImg = new Image(
                    getClass().getResource("/img/unlock-icon.png").toExternalForm());
            userItemStateBtnImg.setImage(stateBtnImg);

        }*/

        userItemStateBtn.setOnMouseClicked(event -> {
            System.out.println("user EMAIL: " + user.getEmail());
            try {
             //   user.setIs_blocked(!user.getIs_blocked());
                userService.modifier(user);
              //  updateStateLabel(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        userItemUpdateBtn.setOnMouseClicked(event -> {
            System.out.println("user EMAIL: " + user.getEmail());

            UsersListController.setupdateUserModelShow(1);
            UsersListController.setuserEmailToUpdate(user.getEmail());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
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
    }

}
