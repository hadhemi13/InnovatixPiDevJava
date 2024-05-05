package yesserTest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TextToSpeechExample {
    // Constantes pour la clé d'abonnement et la région du service
    private static final String SUBSCRIPTION_KEY = "7adafd0b-88d3-4d49-ac8a-b68f20ca9cee";
    private static final String SERVICE_REGION = "francecentral";

    public static void main(String[] args) {
        // Texte à convertir en discours
        String textToSpeech = "Hello, world!";

        // Chemin où sauvegarder le fichier audio généré
        String outputPath = "output.wav";

        HttpURLConnection connection = null;
        try {
            // Encodage du texte à convertir en discours
            String encodedText = URLEncoder.encode(textToSpeech, "UTF-8");

            // Construction de l'URL de l'API Text-to-Speech
            String url = "https://" + SERVICE_REGION + ".tts.speech.microsoft.com/cognitiveservices/v1";
            URL textToSpeechUrl = new URL(url);

            // Création de la connexion HTTP
            connection = (HttpURLConnection) textToSpeechUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/ssml+xml");
            connection.setRequestProperty("X-Microsoft-OutputFormat", "riff-24khz-16bit-mono-pcm");
            connection.setRequestProperty("Authorization", "Bearer " + SUBSCRIPTION_KEY);
            connection.setDoOutput(true);

            // Construction du corps de la requête
            String body = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='en-US'>" +
                    "<voice name='en-US-AriaRUS'>" + textToSpeech + "</voice></speak>";

            // Envoi du texte à convertir en discours
            connection.getOutputStream().write(body.getBytes("UTF-8"));

            // Récupération de la réponse
            InputStream inputStream = connection.getInputStream();

            // Enregistrement du flux audio dans un fichier
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }

            // Affichage du message de succès
            System.out.println("Conversion en discours terminée. Le fichier audio a été enregistré à : " + outputPath);
        } catch (java.net.ConnectException ex) {
            System.out.println("Une erreur de connexion s'est produite : " + ex.getMessage());
        } catch (java.io.IOException ex) {
            System.out.println("Une erreur d'entrée/sortie s'est produite : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Une erreur s'est produite : " + ex.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
