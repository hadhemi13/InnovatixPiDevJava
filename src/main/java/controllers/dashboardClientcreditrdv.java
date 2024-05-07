package controllers;

import Entities.Credit;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Credit.DemandeCreditListClient;
import controllers.Credit.DemandeCreditListClientUser;
import controllers.Credit.DemandeRdvListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceCredit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

        List<Credit> credits;

        // Populate the list of credits (Assuming 'populateCredits' method exists)
        ServiceCredit s=new ServiceCredit();
        try {
            credits=s.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Instantiate CreditService
        ServiceCredit creditService = new ServiceCredit();

        // Get the count of credits for each year
        Map<Integer, Integer> creditCountByYear = creditService.countCreditsByYear(credits);

        // Create axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Credits");

        // Create bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // Prepare data series
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Populate data series
        for (Map.Entry<Integer, Integer> entry : creditCountByYear.entrySet()) {
            // Add data points to the series
            series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }

        // Add the series to the chart
        barChart.getData().add(series);

        // Add the chart to the pane
        content_area.getChildren().add(barChart);
    }

}