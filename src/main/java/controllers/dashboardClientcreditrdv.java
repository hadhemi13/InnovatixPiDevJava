package controllers;

import controllers.Cheque.DemandeChequeListClient;
import controllers.Credit.DemandeCreditListClient;
import controllers.Credit.DemandeCreditListClientUser;
import controllers.Credit.DemandeRdvListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardClientcreditrdv implements Initializable  {

    @FXML
    private Button ListeCheque;

    @FXML
    private Button ListeVirement;

    @FXML
    private Pane content_area;

    @FXML
    private VBox fundListContainer;

    @FXML
    private Text userPointText;
    @FXML
    private BarChart<?, ?> stat1;
    @FXML
    private HBox userTableHead;

    @FXML
    public void listcredit(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeCreditListClient.fxml"));
        System.out.println("e");
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
       // DemandeCreditListClientUser demandeCreditListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    @FXML
    void listrdv(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeRdvListClient.fxml"));
        System.out.println("e");
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        DemandeRdvListClient demandeRdvListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<>("2024", 3));

        XYChart.Series  series2 = new XYChart.Series<>();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<>("2023", 2));

        stat1.getData().addAll(series1, series2);


    }
}