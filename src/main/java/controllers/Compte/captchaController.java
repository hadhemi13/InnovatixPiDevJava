package controllers.Compte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class captchaController  implements Initializable{

    @FXML
    private AnchorPane CaptchaPane;

    @FXML
    private Button Retour;

    @FXML
    private ImageView cap;

    @FXML
    private TextField code;

    @FXML
    private Button reset;

    @FXML
    private Button submit;

    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 200)
                .addText()
                .addBackground(new GradiatedBackgroundProducer()) // Ajout d'un fond gradient
                .addNoise()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }


    Captcha captcha;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reCaptchaFX.siteKeyProperty().setValue("6LeGWckpAAAAABk4EpaQvKloqNvfRrycncXoHmmh");
        reCaptchaFX.themeProperty().setValue("light"); // Vous pouvez changer le thème si nécessaire
        reCaptchaFX.callbackProperty().setValue("myCallback");
        reCaptchaFX.languageProperty().setValue("fr"); // Vous pouvez changer la langue si nécessaire
    }
    //Verifier l captcha + notification systéme
    @FXML
    private void submit(ActionEvent event) throws IOException {
        if (captcha.isCorrect(code.getText())) {

            String tilte = "Captcha";
            String message = "Vous avez saisi le code avec succés!";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

            //     try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
            CaptchaPane.getChildren().removeAll();
            CaptchaPane.getChildren().setAll(fxml);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(Agent_mainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        else {

            String tilte = "Captcha";
            String message = "Vous avez saisi un faux code !";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));

            captcha =  setCaptcha();
            code.setText("");
        }

    }

    @FXML
    private void reseting(ActionEvent event) {
        captcha =  setCaptcha();
        code.setText("");
    }
    @FXML
    void FormAJoutBack(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/Front.fxml"));
        CaptchaPane.getChildren().removeAll();
        CaptchaPane.getChildren().setAll(fxml);

    }

}
