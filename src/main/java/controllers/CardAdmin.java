package controllers;

import controllers.Cheque.ListeChequeAdmin;
import controllers.Compte.ListeCompteAdmin;
import controllers.Virement.AjouterVirementCard;
import controllers.Virement.ListVirementAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class CardAdmin {

    @FXML
    private Pane content_area;

    @FXML
    private Button listC;

    @FXML
    private Button listCh;

    @FXML
    private Button listV;

    @FXML
    private HBox offreModel;

    @FXML
    private VBox reductionForm;

    public void ListCompte(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListCompteAdmin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListeCompteAdmin listeCompteAdmin = loader.getController();

        // Set the scene
        Stage stage = (Stage) listC.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void listCheque(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListChequeAdmin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListeChequeAdmin listeChequeAdmin = loader.getController();

        // Set the scene
        Stage stage = (Stage) listCh.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void listVirement(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListVirementAdmin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListVirementAdmin listVirementAdmin = loader.getController();

        // Set the scene
        Stage stage = (Stage) listV.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }
