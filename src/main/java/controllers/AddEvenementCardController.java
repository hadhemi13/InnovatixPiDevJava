package controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.IService;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * FXML Controller class
 *
 * @author ALI
 */
public class AddEvenementCardController implements Initializable {

    @FXML
    public TextField fxNom;
    @FXML
    private TextField fxDesc;
    @FXML
    private DatePicker fxdateDebut;
    @FXML
    private DatePicker fxdateFin;
    @FXML
    private TextField fxLieu;

    @FXML
    private TextField nameInput;



    @FXML
    private TextArea descriptionInput;

    @FXML
    private TextField prixInput;
    @FXML
    private Button add_new_EvenementBtn;

    @FXML
    private Button update_EvenementBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text nameInputError;

    @FXML
    private Text descriptionInputError;

    @FXML
    private Text categoryInputError;

    @FXML
    private Text numberInputError;

    @FXML
    private Text priceInputError;

    @FXML
    private Text pointsInputError;

    @FXML
    private Text photoInputError;



    @FXML
    private HBox descriptionInputErrorHbox;

    @FXML
    private HBox categoryInputErrorHbox;

    @FXML
    private HBox numberInputErrorHbox;

    @FXML
    private HBox priceInputErrorHbox;

    @FXML
    private HBox pointsInputErrorHbox;

    @FXML
    private HBox photoInputErrorHbox;

    private int categId = -1;
    private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    private int descriptionTest = 0;
    private int categoryTest = 0;
    private int numberTest = 0;
    private int priceTest = 0;
    private int pointsTest = 0;
    private int photoTest = 0;
    private String etiquette = null;

    private double score;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // Instancier le service de produit
      IService serviceEvenement = new ServiceEvenement();
      Evenement e = new Evenement();
      nameInput.setText(e.getNom());
      fxLieu.setText(e.getLieu());
      descriptionInput.setText(e.getDescription());
      prixInput.setText(String.valueOf(e.getPrix()));
      LocalDateTime dateDebut = e.getDateDebut();
      fxdateDebut.setValue(dateDebut != null ? dateDebut.toLocalDate() : null);
      LocalDateTime dateFin = e.getDateFin();
      fxdateFin.setValue(dateFin != null ? dateFin.toLocalDate() : null);
      descriptionInput.setText(e.getDescription());
      if (e.getImg() != null) {
        Image image = new Image(getClass().getResource("/assets/ProductUploads/" + e.getImg()).toExternalForm());
        imageInput.setImage(image);
      } else {
        Image image = new Image(getClass().getResource("/assets/ProductUploads/" + "pngwing1.com.png").toExternalForm());
        imageInput.setImage(image);
      }
      imageName = e.getImg();
    }

        // Ajouter la liste des categories au combobox-----------------
        // Instancier le service de categorie

        // Récupérer tous les categories

        // Afficher les categories dans la console (juste pour tester)
        /*
         * System.out.println("Liste des produits:");
         * for (Categorie_produit categorie : categories) {
         * System.out.println(categorie);
         * }
         */

//        Map<String, Integer> valuesMap = new HashMap<>();
//        for (Categorie_Collecte categorie : categories) {
//            categoryInput.getItems().add(categorie.getNom_categorie());
//            valuesMap.put(categorie.getNom_categorie(), categorie.getId());
//        }
//
//        categoryInput.setOnAction(event -> {
//            String selectedOption = categoryInput.getValue();
//            int selectedValue = valuesMap.get(selectedOption);
//            categId = selectedValue;
//            categoryTest = 1;
//            categoryInputErrorHbox.setVisible(false);
//            // System.out.println("Selected option: " + selectedOption);
//            // System.out.println("Selected value: " + selectedValue);
//        });

    @FXML
    void addNewEvenement(MouseEvent event) throws SQLException {

              Evenement evenement = new Evenement();
              evenement.setNom(nameInput.getText());
              evenement.setDateDebut(fxdateDebut.getValue() != null ? fxdateDebut.getValue().atStartOfDay() : null);
              evenement.setDateFin(fxdateFin.getValue() != null ? fxdateFin.getValue().atStartOfDay() : null);
              evenement.setLieu(fxLieu.getText());
              evenement.setDescription(descriptionInput.getText());
              evenement.setPrix(Double.parseDouble(prixInput.getText()));
              evenement.setImg(imageName);

            IService evenementService = new ServiceEvenement();
            try {
              evenementService.ajouter(evenement);
                TrayNotificationAlert.notif("Evenement", "Evenement added successfully.",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/EvenementsList.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneEvenementListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de EvenementsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

  @FXML
  void ajouter_image(MouseEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
    if (selectedImageFile != null) {
      Image image = new Image(selectedImageFile.toURI().toString());
      imageInput.setImage(image);

      // Générer un nom de fichier unique pour l'image
      String uniqueID = UUID.randomUUID().toString();
      String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
      imageName = uniqueID + extension;

      Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "ProductUploads", imageName);
      Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

      // pour le controle de saisie
      photoTest = 1;
      photoInputErrorHbox.setVisible(false);






    }

  }
  @FXML
  void updateEvenement(MouseEvent event) throws IOException, SQLException {
    Evenement evenement = new Evenement();
    evenement.setNom(nameInput.getText());
    evenement.setLieu(fxLieu.getText());
    evenement.setDescription(descriptionInput.getText());
    evenement.setPrix(Double.parseDouble(prixInput.getText()));
    evenement.setImg(imageName);
    ServiceEvenement serviceEvenement = new ServiceEvenement();

    try {
      serviceEvenement.modifier(evenement);

      TrayNotificationAlert.notif("Evenement", "Evenement updated successfully.",
        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/EvenementsList.fxml"));
      Parent root = loader.load();
      Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
      contentArea.getChildren().clear();
      contentArea.getChildren().add(root);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
      TrayNotificationAlert.notif("Error", "Incorrect date format. Please enter dates in yyyy-MM-dd HH:mm:ss format.",
        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
    }
  }



}
