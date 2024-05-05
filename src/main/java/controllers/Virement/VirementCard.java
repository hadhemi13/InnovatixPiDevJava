package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceVirement;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;

public class VirementCard  implements Initializable {

    @FXML
    private Text DesicionItems;

    @FXML
    private Text MontantItems;

    @FXML
    private Text NomPrenomItems;

    @FXML
    private Text NumBenefItems;

    @FXML
    private Text RibItems;

    @FXML
    private Text TelItems;

    @FXML
    private Text TypeItems;

    @FXML
    private Text benfItems;

    @FXML
    private Text cinItems;

    @FXML
    private HBox editItemsBtn;
    @FXML
    private Text productName;

    @FXML
    private HBox supprItems;
    @FXML
    private ImageView qrCodeImage;
//    private Virement virement;


    // Ne rien faire dans l'initialisation par défaut
//
//    public void initData(Virement virement) {
//        ServiceVirement serviceVirement = new ServiceVirement();
//        editItemsBtn.setId(String.valueOf(virement.getId()));
//        supprItems.setId(String.valueOf(virement.getId()));
//        RibItems.setText(String.valueOf(virement.getRib()));
//        cinItems.setText(String.valueOf(virement.getCin()));
//        NomPrenomItems.setText(virement.getNomet_prenom());
//        TelItems.setText(virement.getPhone_number());
//        benfItems.setText(virement.getTransferez_a());
//        NumBenefItems.setText(String.valueOf(virement.getNum_beneficiare()));
//        MontantItems.setText(String.valueOf(virement.getMontant()));
//        DesicionItems.setText(virement.getDecision_v().toString());
//
//        editItemsBtn.setOnMouseClicked(mouseEvent -> {
//            Stage primaryStage = new Stage();
//            try {
//                Virement virement1 = serviceVirement.getById(Integer.parseInt(editItemsBtn.getId()));
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierVirement.fxml")) ;
//                Parent parent = loader.load();
//                Scene scene = new Scene(parent);
//                primaryStage.setTitle("E-Flex Bank");
//                primaryStage.setScene(scene);
//                primaryStage.show();
//                ModifierVirement modifierCheque = loader.getController();
//                modifierCheque.initData(virement);
//            }catch (SQLException | IOException exception)
//            {
//                throw new RuntimeException(exception);
//            }
//        });
//
//        supprItems.setOnMouseClicked(mouseEvent -> {
//            try {
//                serviceVirement.supprimer(Integer.parseInt(supprItems.getId()));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
    public void initData(Virement virement) {
        // Initialisez les champs de votre interface utilisateur avec les valeurs du virement
        editItemsBtn.setId(String.valueOf(virement.getId()));
        supprItems.setId(String.valueOf(virement.getId()));
        RibItems.setText(String.valueOf(virement.getRib()));
        cinItems.setText(String.valueOf(virement.getCin()));
        NomPrenomItems.setText(virement.getNomet_prenom());
        TelItems.setText(virement.getPhone_number());
        benfItems.setText(virement.getTransferez_a());
        NumBenefItems.setText(String.valueOf(virement.getNum_beneficiare()));
        MontantItems.setText(String.valueOf(virement.getMontant()));
        DesicionItems.setText(virement.getDecision_v().toString());
        String base64QRCode = virement.getQrCode();


        if (base64QRCode != null) {
            // Convert the Base64 string to byte array
            byte[] qrCodeBytes = Base64.getDecoder().decode(base64QRCode);

            // Load the byte array into an Image
            Image qrCode = new Image(new ByteArrayInputStream(qrCodeBytes));

            // Set the Image to the ImageView
            qrCodeImage.setImage(qrCode);
        }

        // Définissez un gestionnaire d'événements pour le bouton de modification
        ServiceVirement serviceVirement = new ServiceVirement();
        editItemsBtn.setOnMouseClicked(mouseEvent -> {
            ModifierVirement virement1 = new ModifierVirement();
            virement1.idV = virement.getId();
            try {
                // Récupérez l'objet Virement associé au bouton de modification en utilisant son ID
                int virementId = Integer.parseInt(editItemsBtn.getId());
                Virement virementToUpdate = serviceVirement.getById(virementId);

                // Ouvrez la fenêtre de modification et passez l'objet Virement récupéré
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierVirement.fxml"));
                Parent parent = loader.load();
                ModifierVirement modifierVirementController = loader.getController();
                modifierVirementController.initData(virementToUpdate);

                // Affichez la fenêtre de modification
                Stage primaryStage = new Stage();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("Modifier Virement");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (SQLException | IOException exception) {
                throw new RuntimeException(exception);
            }
        });

        // Définissez un gestionnaire d'événements pour le bouton de suppression
        supprItems.setOnMouseClicked(mouseEvent -> {
            try {
                // Supprimez le virement associé au bouton de suppression en utilisant son ID
                int virementId = Integer.parseInt(supprItems.getId());
                serviceVirement.supprimer(virementId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void DeleteVirement(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updatevirement(MouseEvent mouseEvent) {
    }

    public void deletevirement(MouseEvent mouseEvent) {
    }
}