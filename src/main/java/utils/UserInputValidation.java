package utils;

import Entities.User;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserInputValidation {

    public static boolean signUpValidator(User user, String repassword, UserService userService) {
        if (user.getFullname().equals("") || user.getEmail().equals("") || user.getTel().equals("")
                || user.getRoles().equals("")
                || user.getPassword().equals("")) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("Please fill out all required fields.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "Please fill out all required fields.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern fullnamePattern = Pattern.compile(".*\\d.*");
        if (fullnamePattern.matcher(user.getFullname()).matches()) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("Your name cannot contain a number.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "Your name cannot contain a number.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (user.getFullname().length() < 3) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("not a valid fullname");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "not a valid fullname.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("The email " + user.getEmail() + " is not a valid
            // email.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "The email " + user.getEmail() + " is not a valid email.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern telPattern = Pattern.compile("[0-9]+");
        if (!(telPattern.matcher(user.getTel()).matches() && user.getTel().length() >= 8)) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("not a valid phone number.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "not a valid phone number.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (user.getRoles() != "[\"ROLE_USER\"]" && user.getRoles() != "[\"ROLE_ASSOCIATION\"]") {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("Hello little hacker 🐱‍💻👩‍💻");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "Hello little hacker 🐱‍💻👩‍💻",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (user.getPassword().length() < 8) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("Your password must be at least 8 characters long.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "Your password must be at least 8 characters long.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (!user.getPassword().equals(repassword)) {
            // Alert alert = new Alert(AlertType.WARNING);
            // alert.setTitle("signUp error");
            // alert.setHeaderText(null);
            // alert.setContentText("Passwords do NOT match.");

            // alert.showAndWait();

            TrayNotificationAlert.notif("sign Up", "Passwords do NOT match.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        try {
            if (userService.getOneUser(user.getEmail()).getId() != -999) {
                // Alert alert = new Alert(AlertType.WARNING);
                // alert.setTitle("signUp error");
                // alert.setHeaderText(null);
                // alert.setContentText("email already in use.");
                // alert.showAndWait();

                TrayNotificationAlert.notif("sign Up", "email already in use.",
                        NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean forgetPasswordValidator(String email, User user) {
        if (email.equals("")) {

            TrayNotificationAlert.notif("Forgot password", "Please fill out all required fields.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        if (!emailPattern.matcher(email).matches()) {

            TrayNotificationAlert.notif("sign Up", "The email " + email + " is not a valid email.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (user.getId() == -999) {
            TrayNotificationAlert.notif("Forgot password", "email not associated with zeroWaste account",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        return true;
    }

    public static boolean forgetPasswordValidator2(String password, String rePassword) {

        if (password.equals("") || rePassword.equals("")) {

            TrayNotificationAlert.notif("Forgot password", "Please fill out all required fields.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (password.length() < 8) {

            TrayNotificationAlert.notif("Forgot password", "Your password must be at least 8 characters long.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (!password.equals(rePassword)) {

            TrayNotificationAlert.notif("Forgot password", "Passwords do NOT match.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        return true;
    }

    public static boolean changePasswordValidator(User user, String oldPassword, String password, String rePassword) {

        if (password.equals("") || rePassword.equals("")) {

            TrayNotificationAlert.notif("change password", "Please fill out all required fields.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (!BCrypt.checkpw(oldPassword, user.getPassword().replace("$2y$", "$2a$"))) {
            TrayNotificationAlert.notif("change password", "Please verify your old password",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (password.length() < 8) {

            TrayNotificationAlert.notif("change password", "Your password must be at least 8 characters long.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (!password.equals(rePassword)) {

            TrayNotificationAlert.notif("change password", "Passwords do NOT match.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        return true;
    }

    public static boolean updateAccountValidator(User user) {
        if (user.getFullname().equals("") || user.getTel().equals("")) {

            TrayNotificationAlert.notif("Update Account", "Please fill out all required fields.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern fullnamePattern = Pattern.compile(".*\\d.*");
        if (fullnamePattern.matcher(user.getFullname()).matches()) {

            TrayNotificationAlert.notif("Update Account", "Your name cannot contain a number.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        if (user.getFullname().length() < 3) {

            TrayNotificationAlert.notif("Update Account", "not a valid fullname.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        Pattern telPattern = Pattern.compile("[0-9]+");
        if (!(telPattern.matcher(user.getTel()).matches() && user.getTel().length() >= 8)) {

            TrayNotificationAlert.notif("Update Account", "not a valid phone number.",
                    NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

            return false;
        }

        return true;
    }
}
