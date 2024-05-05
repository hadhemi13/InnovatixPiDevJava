package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceCheque;
import services.ServiceCompte;

public class ShowCompte {

    @FXML
    private Text Cin;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text DatedelCin;

    @FXML
    private Text Email;

    @FXML
    private Text Montant;

    @FXML
    private Text Nom;

    @FXML
    private Text Prenom;

    @FXML
    private Text Profession;

    @FXML
    private Text Sexe;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;

    @FXML
    private HBox addReviewsModel;

    @FXML
    private Pane content_area;

    @FXML
    private Text nameInputError;

    @FXML
    private HBox nameInputErrorHbox;

    @FXML
    private GridPane productDetailsContainer;

    @FXML
    private HBox updateBtnContainer;

    @FXML
    void close_addReviewsModel(MouseEvent event) {
        addReviewsModel.setVisible(false);
//        addReviewsModelShow = 0;

    }
    public void initData(Compte compte ) {
        ServiceCompte serviceCompte = new ServiceCompte();

        Email.setText(String.valueOf(compte.getEmail()));
        Nom.setText(String.valueOf(compte.getNom()));
        Prenom.setText(compte.getPrenom());
        TypeCin.setText(compte.getType_cin());
        Cin.setText(String.valueOf(compte.getCin()));
        DatedelCin.setText(String.valueOf(compte.getDate_delivrance_cin()));
        DateNaiss.setText(String.valueOf(compte.getDate_naissance()));
        TypeCompte.setText(compte.getType_compte());
        Profession.setText(compte.getProffesion());
        Sexe.setText(compte.getSexe());
        Montant.setText(String.valueOf(compte.getMontant()));
    }







    }
