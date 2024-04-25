package controllers;

import Entities.Cheque;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;

import java.io.IOException;
import java.sql.SQLException;

public class ChequeItemsController {

    @FXML
    private Text date;

    @FXML
    private Text userItemEmail;

    @FXML
    private Text personne;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Text hhh;

    @FXML
    private HBox userItemUpdateBtn;

    @FXML
    private Text userItemRole;

    @FXML
    private Text productName;

    @FXML
    private Text userItemName;

    @FXML
    private HBox userItemStateBtn;


    public void initData(Cheque i ){
        ServiceCheque serviceCheque = new ServiceCheque();

        userItemUpdateBtn.setId(String.valueOf(i.getId()));

        userItemStateBtn.setId(String.valueOf(i.getId()));

        userItemEmail.setText(String.valueOf(i.getCin()));
        userItemName.setText(String.valueOf(i.getCompte_id()));
        userItemTel.setText(i.getNom_prenom());
        userItemRole.setText(i.getEmail());
        userItemStateText.setText(i.getDecision());
        date.setText(String.valueOf(i.getDate()));
        hhh.setText(String.valueOf(i.getTelephone()));
        personne.setText(i.getBeneficiaire().toString());

        userItemUpdateBtn.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            try {
                Cheque cheque = serviceCheque.getById(Integer.parseInt(userItemUpdateBtn.getId()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierCheque.fxml")) ;
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("E-Flex Bank");
                primaryStage.setScene(scene);
                primaryStage.show();
                ModifierCheque modifierCheque = loader.getController();
                modifierCheque.initData(cheque);
            }catch (SQLException | IOException exception)
            {
                throw new RuntimeException(exception);
            }

        });
        userItemStateBtn.setOnMouseClicked(mouseEvent -> {
            try {
                serviceCheque.supprimer(Integer.parseInt(userItemStateBtn.getId()));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

    public void UpdateCheque(MouseEvent mouseEvent) {
    }

    public void DeleteCheque(MouseEvent mouseEvent) {
    }
}