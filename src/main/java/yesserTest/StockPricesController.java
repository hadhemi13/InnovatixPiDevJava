package yesserTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StockPricesController {
    private static final String API_KEY = "IUUDQ7QK4I7O597B";
    private static final String SYMBOL = "AAPL"; // Symbole de l'action (Apple Inc. ici)

    @FXML
    private Label priceLabel;

    public void refreshStockPrices(ActionEvent actionEvent) {
        try {
            // Construire l'URL de l'API
            String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + SYMBOL + "&apikey=" + API_KEY;

            // Lire les données JSON de l'API
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Analyser les données JSON
            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONObject globalQuote = jsonObject.getJSONObject("Global Quote");
            String price = globalQuote.getString("05. price");

            // Afficher le prix de l'action
            priceLabel.setText("Prix de l'action " + SYMBOL + " : " + price);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            priceLabel.setText("Erreur lors de la récupération des données.");
        }
    }
}
