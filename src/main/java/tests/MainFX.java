package tests;

import com.twilio.Twilio;
import controllers.Credit.GMailer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        final String ACCOUNT_SID = "{{AC4adc7fe4cb61a460897686e28e173bb5}}";
        final String AUTH_TOKEN = "{{ 95216ee1ff02a93e4c15247aa8d32ddb}}";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideNavBar.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("E-Flex Bank");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
