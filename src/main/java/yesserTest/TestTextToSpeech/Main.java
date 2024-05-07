package yesserTest.TestTextToSpeech;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        String url = "https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize";
        String apiKey = "6335f0e1b6mshe51bdb6be5175a1p1914fajsn7770537fdb84";
        String textToConvert = "je suis hadhemi mahmoud"; // Remplacez "Votre texte ici" par le texte que vous souhaitez convertir en discours.

        try {
            String audioUrl = getAudioUrl(url, apiKey, textToConvert);
            System.out.println("URL de l'audio: " + audioUrl);
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static String getAudioUrl(String url, String apiKey, String textToConvert) throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "cloudlabs-text-to-speech.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("voice_code=fr-FR-5&text=" + textToConvert.replaceAll("\\s+", "%20") + "&speed=1.00&pitch=1.00&output_type=audio_url"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        String audioUrl = jsonResponse.getJSONObject("result").getString("audio_url");
        return audioUrl;
    }
}
