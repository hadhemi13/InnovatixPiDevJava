package controllers.rss;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture; // Importer CompletableFuture pour gérer les opérations asynchrones

public class RSSReader {
    public static void main(String[] args) {
        // Créer un client HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Créer une requête HTTP GET vers l'URL du flux RSS
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rss.app/feeds/v1.1/tOgyrCZKdMWLeE5M.json"))
                .build();

        // Envoyer la requête de manière asynchrone et traiter la réponse
        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Utiliser CompletableFuture pour traiter la réponse lorsque disponible
        futureResponse.thenApply(HttpResponse::body) // Récupérer le corps de la réponse
                .thenAccept(System.out::println) // Afficher le corps de la réponse
                .join(); // Attendre que toutes les opérations se terminent
    }
}
