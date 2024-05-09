//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.File;
//import java.nio.file.Files;
//
//public class SignNowService extends Application {
//
//    private static final String BASE_URL = "http://votre-serveur:port";
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button signButton = new Button("Signer en ligne");
//
//        signButton.setOnAction(e -> {
//            // Appel à l'API pour signer en ligne
//            signOnline();
//        });
//
//        VBox root = new VBox(signButton);
//        Scene scene = new Scene(root, 300, 200);
//
//        primaryStage.setTitle("SignNow Client");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void signOnline() {
//        // Créer une configuration client JAX-RS
//        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
//        Client client = clientBuilder.build();
//
//        // Exemple : envoi d'un document à signer
//        File documentFile = new File("C:\\Users\\Admin\\Documents\\InnovatixPiDevJava\\src\\main\\resources\\pdf\\cheque.pdf");
//        byte[] documentContent;
//        try {
//            documentContent = Files.readAllBytes(documentFile.toPath());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // Appel à l'API REST pour envoyer le document à signer
//        Response response = client.target(BASE_URL)
//                .path("/documents")
//                .request(MediaType.APPLICATION_OCTET_STREAM)
//                .post(Entity.entity(documentContent, MediaType.APPLICATION_OCTET_STREAM));
//
//        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
//            // Le document a été envoyé avec succès à l'API SignNow pour signature
//            System.out.println("Document envoyé avec succès pour signature en ligne.");
//        } else {
//            // Gérer les cas d'échec de l'envoi du document
//            System.err.println("Échec de l'envoi du document pour signature en ligne.");
//        }
//
//        response.close();
//        client.close();
//    }
//}

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SignNowService {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Chemin vers le fichier PDF à signer
        String pdfFilePath = "C:\\Users\\Admin\\Documents\\InnovatixPiDevJava\\src\\main\\resources\\pdf\\cheque.pdf";

        // Lire le contenu du fichier PDF en tant que tableau d'octets
        byte[] pdfBytes = Files.readAllBytes(Paths.get(pdfFilePath));

        // Construire la requête HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://skysignature-skysignature-v1.p.rapidapi.com/skysignature"))
                .header("X-RapidAPI-Key", "Umf6QRvOT73PiecD7BsUUWbklFvQ5UoO")
                .header("X-RapidAPI-Host", "skysignature-skysignature-v1.p.rapidapi.com")
                .header("Content-Type", "application/pdf") // Spécifier le type de contenu du corps de la requête
                .POST(HttpRequest.BodyPublishers.ofByteArray(pdfBytes)) // Utiliser le contenu du PDF comme corps de la requête
                .build();

        // Envoyer la requête HTTP et récupérer la réponse
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Afficher la réponse
        System.out.println(response.body());
    }
}
