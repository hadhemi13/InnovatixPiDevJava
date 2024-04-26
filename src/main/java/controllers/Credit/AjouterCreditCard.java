package controllers.Credit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Entities.Cheque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import services.ServiceCheque;
import utils.MyDatabase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AjouterCreditCard implements Initializable {


    @FXML
    private HBox DateInputErrorHbox;

    @FXML
    private TextField Email;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private Button addcreditbtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private VBox content_area;

    @FXML
    private DatePicker datedebut;

    @FXML
    private Text dateinputerror;

    @FXML
    private TextField duree;

    @FXML
    private Text dureeinputerror;

    @FXML
    private TextField fraisretard;

    @FXML
    private HBox frasierror;

    @FXML
    private TextField id_client;

    @FXML
    private Text mensualiteerror;

    @FXML
    private TextField montant;

    @FXML
    private Text montantinputerror;

    @FXML
    private TextField taux;

    @FXML
    private Text tauxinputerror;

    @FXML
    private Text telInputError2;

    @FXML
    private HBox telInputErrorHbox;

    @FXML
    private HBox telInputErrorHbox1;

    @FXML
    private TextField userid;
    @FXML
    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    private Timer timer;
    @FXML
    void calculertaux(ActionEvent event) {
        try {
            // Parse values from text fields
            Double montantValue = Double.parseDouble(montant.getText());
            Double mensualiteValue = Double.parseDouble(Email.getText());
            Double dureeValue = Double.parseDouble(duree.getText());

            // Calculate the interest rate
            Double interestRate = (((mensualiteValue * dureeValue) - montantValue) / montantValue) * 12 / dureeValue;

            // Set the calculated interest rate to the taux field
            taux.setText(String.valueOf(interestRate));
        } catch (NumberFormatException e) {
            // Handle parsing errors (e.g., invalid input format)
            System.err.println("Error parsing input values: " + e.getMessage());
            // You may want to display an error message to the user
        }
    }
    @FXML
    void calculerfraisretard(ActionEvent event) {
        Double montantValue = Double.parseDouble(montant.getText());
        var fraisretardv= montantValue*0.01;
        fraisretard.setText(String.valueOf(fraisretardv));

    }
    @FXML
    void ajoutercredit(MouseEvent event) throws SQLException {
        String insert = "insert into credit (user_id, id_client, montant, taux, datedebut, mensualite, duree, fraisretard) values (?, ?, ?, ?, ?, ?, ?, ?)";
        con = MyDatabase.getInstance().getConnection();


        try {
            if (id_client.getText().length() != 8) {
                cinInputError.setText("Erreur: idclient doit avoir une longueur de 8 caractères.");


            }
            if (montant.getText().isEmpty()) {
                montantinputerror.setText("Erreur: employename ne peut pas être vide.");


            }
            if (Email.getText().isEmpty()) {
                mensualiteerror.setText("Erreur: methode ne peut pas être vide.");


            }
            if (taux.getText().isEmpty()) {
               tauxinputerror.setText("Erreur: methode ne peut pas être vide.");


            }
            if (duree.getText().isEmpty()) {
                dateinputerror.setText("Erreur: methode ne peut pas être vide.");


            }
            if (fraisretard.getText().isEmpty()) {
                telInputError2.setText("Erreur: methode ne peut pas être vide.");



            }








            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    cinInputError.setText("");
                    montantinputerror.setText("");
                    mensualiteerror.setText("");
                    tauxinputerror.setText("");
                    dateinputerror.setText("");
                    dureeinputerror.setText("");




                    timer.cancel();
                }
            }, 4000);





            st = con.prepareStatement(insert);
            st.setInt(1, 1); // Static value for user_id
            st.setInt(2, Integer.parseInt(id_client.getText())); // id_client
            st.setDouble(3, Double.parseDouble(montant.getText())); // montant
            st.setDouble(4, Double.parseDouble(taux.getText())); // taux
            st.setDate(5, java.sql.Date.valueOf(datedebut.getValue())); // datedebut
            st.setDouble(6, Double.parseDouble(Email.getText())); // mensualite
            st.setInt(7, Integer.parseInt(duree.getText())); // duree
            st.setDouble(8, Double.parseDouble(fraisretard.getText())); // fraisretard

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void saverdv(ActionEvent actionEvent) {
    }
}


// Méthode pour ajouter une image au chèque
   // public void ajouter_image(ActionEvent event)  throws IOException {
      //  FileChooser fileChooser=new FileChooser();
        //fileChooser.setTitle("Choisir une image");
        //fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.jpeg","*.gif"));
        //selectedImageFile= fileChooser.showOpenDialog(ImageInput.getScene().getWindow()):
        //if (selectedImageFile != null) {
           // Image image = new (selectedImageFile.toURI().toString());
           // imageInput.setImage(image);

           // String uniqueID = UUID.randomUUID().toString();
            //String extension =selectedImageFile.getName

        //}
    //}
//}
