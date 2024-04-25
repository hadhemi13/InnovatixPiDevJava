package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;

import java.io.IOException;
import java.sql.SQLException;

public class CompteItems {

    @FXML
    private Label ApprouveBtn;

    @FXML
    private ImageView Approuver;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text Email;

    @FXML
    private Text Nom;

    @FXML
    private Text NumCin;

    @FXML
    private Text Numtel;

    @FXML
    private Text Prenom;

    @FXML
    private Text Professon;

    @FXML
    private Label RefusBtn;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;


    public void initData(Compte i){
        ServiceCompte serviceCompte = new ServiceCompte();

        Email.setText(i.getEmail());
        Nom.setText(i.getNom());
        Prenom.setText(i.getPrenom());
        TypeCin.setText(i.getType_cin());
        NumCin.setText(String.valueOf(i.getCin()));
        DateNaiss.setText(String.valueOf(i.getDate_naissance()));
        Professon.setText(i.getProffesion());
        Numtel.setText(String.valueOf(i.getNumero_telephone()));
        TypeCompte.setText(i.getType_compte());
        ApprouveBtn.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            try {
                Compte compte = serviceCompte.afficher().get(Integer.parseInt(ApprouveBtn.getId()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Historique.fxml")) ;
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                ApprouverCompte approuverCompte = loader.getController();
                ApprouverCompte.initData(compte);
            }catch (SQLException | IOException exception)
            {
                throw new RuntimeException(exception);
            }

        });
        RefusBtn.setOnMouseClicked(mouseEvent -> {
            try {
                serviceCompte.supprimer(Integer.parseInt(RefusBtn.getId()));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }



}
