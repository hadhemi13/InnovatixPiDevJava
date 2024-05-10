package controllers.rss;


import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class rssFeed {
    private String title;
    private String content;
    private String image;
    private String createdAt;

    public rssFeed(String title, String content, String image, String createdAt) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public rssFeed() {

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static List<rssFeed> loadArticlesFromURL(String urlString) throws IOException {
        List<rssFeed> articles = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(urlString); // Utiliser l'URL passée en paramètre
        JsonNode jsonNode = objectMapper.readTree(url);

        for (JsonNode item : jsonNode.get("items")) {
            JsonNode titleNode = item.get("title");
            JsonNode contentNode = item.get("content_text");
            JsonNode imageNode = item.get("image");
            JsonNode createdAtNode = item.get("date_published");

            if (titleNode != null && contentNode != null && createdAtNode != null) {
                // Vérifier si imageNode est null
                if (imageNode != null) {
                    String title = titleNode.asText();
                    String content = contentNode.asText();
                    String image = imageNode.asText();
                    String createdAt = createdAtNode.asText();
                    articles.add(new rssFeed(title, content, image, createdAt));
                } else {
                    System.out.println("L'article ne contient pas d'image. Il sera ignoré.");
                }
            } else {
                System.out.println("Un ou plusieurs champs manquants pour cet article. Il sera ignoré.");
            }
        }

        return articles;
    }

    public static List<String> loadArticleTitlesFromURL(String urlString) throws IOException {
        List<String> articleTitles = new ArrayList<>();

        // Step 1: Load JSON data from API
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(urlString); // Utiliser l'URL passée en paramètre
        JsonNode jsonNode = objectMapper.readTree(url);

        // Step 2: Parse JSON data and extract article titles
        for (JsonNode item : jsonNode.get("items")) {
            JsonNode titleNode = item.get("title");

            if (titleNode != null) {
                String title = titleNode.asText();
                articleTitles.add(title);
            }
        }

        return articleTitles;
    }
}