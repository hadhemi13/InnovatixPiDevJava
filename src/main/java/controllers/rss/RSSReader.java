package controllers.rss;

//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.concurrent.CompletableFuture; // Importer CompletableFuture pour gérer les opérations asynchrones
//
//public class RSSReader {
//    public static void main(String[] args) {
//        // Créer un client HttpClient
//        HttpClient client = HttpClient.newHttpClient();
//
//        // Créer une requête HTTP GET vers l'URL du flux RSS
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://rss.app/feeds/v1.1/tOgyrCZKdMWLeE5M.json"))
//                .build();
//
//        // Envoyer la requête de manière asynchrone et traiter la réponse
//        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//
//        // Utiliser CompletableFuture pour traiter la réponse lorsque disponible
//        futureResponse.thenApply(HttpResponse::body) // Récupérer le corps de la réponse
//                .thenAccept(System.out::println) // Afficher le corps de la réponse
//                .join(); // Attendre que toutes les opérations se terminent
//    }
//}
import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class RSSReader {
    private String title;
    private String content;
    private String imageUrl;
    private String datePublished;

    public RSSReader(String title, String content, String imageUrl, String datePublished) {

        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.datePublished = datePublished;
    }
    public RSSReader(String title,  String imageUrl) {

        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.datePublished = datePublished;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public String getDatePublished() {
//        return datePublished;
//    }

public static List<RSSReader> loadArticlesFromURL(String urlString) throws IOException {
    List<RSSReader> articles = new ArrayList<>();

    // Step 1: Load JSON data from API
    ObjectMapper objectMapper = new ObjectMapper();
    URL url = new URL("https://rss.app/feeds/v1.1/tRnP5WwFlg3QpD80.json");
    JsonNode jsonNode = objectMapper.readTree(url);

    // Step 2: Parse JSON data into RSSReader objects
    for (JsonNode item : jsonNode.get("items")) {
        String title = item.get("title").asText();
        //String content = item.get("content_text").asText();
        JsonNode imageNode = item.get("image");
        String imageUrl = imageNode != null ? imageNode.asText() : null;
//        String datePublished = item.get("date_published").asText();
        articles.add(new RSSReader(title, imageUrl));
    }

    return articles;
}
}

