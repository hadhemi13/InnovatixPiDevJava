package controllers;

import Entities.User;
import controllers.user.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;

public class SideNavBarController {

    @FXML
    private Pane content_area;

    @FXML
    private PieChart pieChart;

    @FXML
    void openUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UsersList.fxml"));
            Pane userListPane = loader.load();
            content_area.getChildren().setAll(userListPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        UserSession.getInstance().cleanUserSession();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initData(User user) {
        // Vous pouvez implémenter cette méthode pour initialiser des données spécifiques à l'utilisateur
    }

    public void initializeDashboard() {
        try {
            ServiceUser serviceUser = new ServiceUser();
            int activeNB = serviceUser.getActiveNB();
            int unActiveNB = serviceUser.getunActiveNB();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Actifs", activeNB),
                    new PieChart.Data("Inactifs", unActiveNB)
            );

            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



