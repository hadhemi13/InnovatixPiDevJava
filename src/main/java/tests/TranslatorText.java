package tests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TranslatorText {
    private static String key = "f9b9c186e1a748ae91cf01b2fc399e43";
    private static String location = "francecentral";

    // Cette fonction effectue une requête POST.
    public String post(String text, String fromLanguage, String toLanguage) throws IOException, InterruptedException {
        String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" +
                fromLanguage + "&to=" + toLanguage;
        String requestBody = "[{\"Text\": \"" + text + "\"}]";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", key)
                .header("Ocp-Apim-Subscription-Region", location)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Obtenir la traduction du texte à partir de la réponse JSON
        String responseBody = response.body();
        String translatedText = null;
        int startIndex = responseBody.indexOf("\"text\":\"");
        if (startIndex != -1) { // Vérifier si la sous-chaîne est trouvée
            startIndex += 8; // Déplacer l'index au début du texte traduit
            int endIndex = responseBody.indexOf("\"", startIndex); // Trouver la fin de la sous-chaîne
            if (endIndex != -1) { // Vérifier si la fin de la sous-chaîne est trouvée
                translatedText = responseBody.substring(startIndex, endIndex); // Extraire le texte traduit
                System.out.println(translatedText); // Affiche la traduction
            }
        }
        return translatedText; // Retourner le texte traduit, peut être null si la traduction n'a pas pu être extraite
    }




    public static void main(String[] args) {
        try {
            TranslatorText translateRequest = new TranslatorText();
            String text = "I would really like to drive your car around the block a few times!";
            String fromLanguage = "en";
            String toLanguage = "ar";

            String response = translateRequest.post(text, fromLanguage, toLanguage);
           // System.out.println(response);
            String translatedText = prettify(response).split("\"text\":\"")[1].split("\"")[0];
          //  System.out.println(translatedText);
        } catch (Exception e) {
            System.out.println("lahnee yee maryem");
        }
    }

    // Cette fonction formate joliment la réponse JSON.
    public static String prettify(String json_text) {
        // Vous pouvez réutiliser la fonction prettify() existante
        return json_text;
    }
}
