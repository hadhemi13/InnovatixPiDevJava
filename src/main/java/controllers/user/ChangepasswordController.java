package controllers.user;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;
import services.ServiceUser;

import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ChangepasswordController {

    @FXML
    private HBox changePassModel;

    @FXML
    private Button confirmChangePassBtn;

    @FXML
    private PasswordField newPassField;

    @FXML
    private PasswordField newRepassField;

    @FXML
    private PasswordField oldPassField;

    @FXML
    private Pane profilePane;
    @FXML
    void changePass(ActionEvent event) {
        changePassModel.setVisible(true);
    }
    User user;




    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML



    public void close_ChangePassModel(javafx.scene.input.MouseEvent mouseEvent) {
        changePassModel.setVisible(false);
    }
    @FXML
    public void confirmChangePass(javafx.scene.input.MouseEvent mouseEvent) {
        ServiceUser userService = new ServiceUser();
        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("mariem@gmail.com");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }

            if (UserControleSaisie.changePasswordValidator(user, oldPassField.getText(),
                newPassField.getText(), newRepassField.getText())) {

            try {
                user.setPassword(BCrypt.hashpw(newPassField.getText(), BCrypt.gensalt()));
                userService.modifier(user);
                changePassModel.setVisible(false);

                // Utilisation d'une alerte pour indiquer que le mot de passe a été changé avec succès
                showAlert("Change Password", "Password changed successfully.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici
            }
        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}

