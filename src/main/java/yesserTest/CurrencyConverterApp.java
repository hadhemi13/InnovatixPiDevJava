package yesserTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

public class CurrencyConverterApp extends Application {
    private static final String API_KEY = "11e5b9a50b08917ef02721ae"; // Remplacez par votre clé API ExchangeRate-API
    private static final String[] CURRENCIES = {"EUR", "USD", "SAR", "DZD"}; // Liste des devises à comparer
    private static final String BASE_CURRENCY = "TND"; // Devise de référence

    @Override
    public void start(Stage primaryStage) throws JSONException {
        Label titleLabel = new Label("Taux de change par rapport au dinar tunisien (TND) :");
        VBox root = new VBox(titleLabel);

        // Appel de l'API pour obtenir les taux de change
        JSONObject rates = null;
        try {
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + BASE_CURRENCY;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            // Analyse des données JSON
            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            rates = jsonObject.getJSONObject("rates");

            // Boucler à travers les devises et récupérer les taux de change
            for (String currency : CURRENCIES) {
                double rate = rates.getDouble(currency);
                Label rateLabel = new Label(currency + ": " + rate);
                System.out.println(rateLabel);

                // Ajouter le drapeau du pays
                String countryCode = currency.substring(0, 2).toLowerCase();
                String flagUrl = "https://www.countryflags.io/" + countryCode + "/flat/64.png";
                Image flagImage = new Image(flagUrl);
                ImageView flagImageView = new ImageView(flagImage);
                flagImageView.setFitWidth(32);
                flagImageView.setFitHeight(32);

                // Ajouter le taux de change et le drapeau dans une VBox
                VBox currencyBox = new VBox(rateLabel, flagImageView);
                root.getChildren().add(currencyBox);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Label errorLabel = new Label("Erreur lors de la récupération des taux de change.");
            root.getChildren().add(errorLabel);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Convertisseur de devises");
        primaryStage.show();
        double amountInTND = 8.0;
        double amountInEUR = amountInTND * rates.getDouble("EUR");
        double amountInUSD = amountInTND * rates.getDouble("USD");
        double amountInSAR = amountInTND * rates.getDouble("SAR");
        double amountInDZD = amountInTND * rates.getDouble("DZD");

// Afficher les résultats
        System.out.println("8 TND équivaut à :");
        System.out.println("EUR : " + amountInEUR);
        System.out.println("USD : " + amountInUSD);
        System.out.println("SAR : " + amountInSAR);
        System.out.println("DZD : " + amountInDZD);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
