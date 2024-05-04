package yesserTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    private static final String API_KEY = "0920662cbc6a470ba6813c20187b4eef"; // Remplacez par votre propre clé API

    public static String translate(String text, String sourceLang, String targetLang) throws IOException {
        String url = "https://api.cognitive.microsofttranslator.com/" + API_KEY +
                "&q=" + URLEncoder.encode(text, "UTF-8") +
                "&source=" + sourceLang +
                "&target=" + targetLang;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response to extract the translated text
        String translatedText = parseTranslationResponse(response.toString());

        return translatedText;
    }

    private static String parseTranslationResponse(String jsonResponse) {
        // Implement your logic to parse the JSON response and extract the translated text
        // This will depend on the structure of the response returned by the translation API
        // For Google Translate API, the response is usually in JSON format with the translated text under "data.translations[0].translatedText"
        // You may use libraries like Gson or Jackson for parsing JSON
        return "Translated Text";
    }

    public static void main(String[] args) {
        try {
            String translatedText = translate("Hello", "en", "fr");
            System.out.println("Translated Text: " + translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
