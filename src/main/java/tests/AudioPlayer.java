package tests;

import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.media.Media;
        import javafx.scene.media.MediaPlayer;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URI;
        import java.net.http.HttpClient;
        import java.net.http.HttpRequest;
        import java.net.http.HttpResponse;

        import org.json.JSONException;
        import org.json.JSONObject;

public class AudioPlayer extends Application {

    @Override
    public void start(Stage primaryStage) {
        String url = "https://cloudlabs-text-to-speech.p.rapidapi.com/synthesize";
        String apiKey = "e1e3d0a5edmshf8ab4a8aa19fd20p10c234jsn40bedaaedca5";
        String textToConvert = "salut ayoub je suis text to speech et je travaille très bien";

        try {
            String audioUrl = Yesser.getAudioUrl(url, apiKey, textToConvert);

            Media media = new Media(audioUrl);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            primaryStage.setScene(new Scene(null, 0, 0));
            primaryStage.show();
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
