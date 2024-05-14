//package test;
//
//
//        import javafx.application.Application;
//        import javafx.event.ActionEvent;
//        import javafx.event.EventHandler;
//        import javafx.scene.Scene;
//        import javafx.scene.control.Button;
//        import javafx.scene.layout.StackPane;
//        import javafx.stage.Stage;
//
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//        import java.io.IOException;
//        import java.net.URI;
//        import java.net.http.HttpClient;
//        import java.net.http.HttpRequest;
//        import java.net.http.HttpResponse;
//        public class texttospeech extends Application {
//    private String url = "https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize";
//    private String apiKey = "b090e6cdc7msh955335d5f298704p15c4e9jsnd258bf270b2e";
//    private String textToConvert = "bonjour madame nous allons corriger tout le travail pour le bal de projet";
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Convertir en audio");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    String audioUrl = getAudioUrl(url, apiKey, textToConvert);
//                    System.out.println("URL de l'audio: " + audioUrl);
//                    // Ici, vous pouvez ajouter le code pour jouer le son à partir de l'URL audioUrl
//                } catch (IOException | InterruptedException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Text to Speech");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private String getAudioUrl(String url, String apiKey, String textToConvert) throws IOException, InterruptedException, JSONException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("content-type", "application/x-www-form-urlencoded")
//                .header("X-RapidAPI-Key", apiKey)
//                .header("X-RapidAPI-Host", "cloudlabs-text-to-speech.p.rapidapi.com")
//                .method("POST", HttpRequest.BodyPublishers.ofString("voice_code=fr-FR-5&text=" + textToConvert.replaceAll("\\s+", "%20") + "&speed=1.00&pitch=1.00&output_type=audio_url"))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        JSONObject jsonResponse = new JSONObject(response.body());
//        String audioUrl = jsonResponse.getJSONObject("result").getString("audio_url");
//        return audioUrl;
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
