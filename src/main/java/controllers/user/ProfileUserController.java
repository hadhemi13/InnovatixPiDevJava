package controllers.user;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceUser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//import io.camunda.connector.Connector;

import java.io.File;
import java.io.FileOutputStream;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;

public class ProfileUserController  implements Initializable {

    @FXML
    private Button changePassBTN;

    @FXML
    private ImageView downloadPdfButton;

    @FXML
    private Text descriptionText;

    @FXML
    private Text emailText;

    @FXML
    private Text fullnameText;

    @FXML
    private Pane profilePane;

    @FXML
    private Text telText;

    @FXML
    private ImageView userItemImg;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        ServiceUser userService = new ServiceUser();
        try {
            // user = userService.getOneUser(UserSession.getInstance().getEmail());
            User user;
            if (UserSession.getInstance().getEmail() == null) {
                user = userService.getOneUser("mariem@gmail.com");
            } else {
                user = userService.getOneUser(UserSession.getInstance().getEmail());
            }

            // Image image = new Image(
            // getClass().getResource("/img/admin.png/" + user.getPhoto()).toExternalForm());
            // userItemImg.setImage(image);


            fullnameText.setText(user.getName());
            descriptionText.setText(user.getRoles());
            emailText.setText(user.getEmail());
            telText.setText(user.getTel());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleDownloadPdf() {
        try {
            // Instancier ServiceUser
            ServiceUser userService = new ServiceUser();
            // Utiliser l'instance pour appeler la méthode getOneUser
            User user = userService.getOneUser(UserSession.getInstance().getEmail());
            generatePdf(user);
        } catch (SQLException e) {
            e.printStackTrace(); // handle exception properly
        }
    }

    private void generatePdf(User user) {
        Document document = new Document();
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName(user.getName() + "_profile.pdf");
            File file = fileChooser.showSaveDialog(downloadPdfButton.getScene().getWindow());
            if (file != null) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                document.add(new Paragraph("Full Name: " + user.getName()));
                document.add(new Paragraph("Description: " + user.getRoles()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Telephone: " + user.getTel()));
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // handle exception properly
        }
    }

    @FXML
    void changePass(ActionEvent event) throws IOException {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChangePassword.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu
            profilePane.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public void generatePdf(MouseEvent mouseEvent) {
        try {
            ServiceUser userService = new ServiceUser();
            User user = userService.getOneUser(UserSession.getInstance().getEmail());
            Document document = new Document();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName(user.getName() + "_profile.pdf");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                document.add(new Paragraph("Full Name: " + user.getName()));
                document.add(new Paragraph("Description: " + user.getRoles()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Telephone: " + user.getTel()));
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // handle exception properly
        }
    }
}





