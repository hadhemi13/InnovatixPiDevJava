package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRealTimeFinanceData {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://ai-chatbot.p.rapidapi.com/chat/free?message=What's%20your%20name%3F&uid=user1"))
            .header("X-RapidAPI-Key", "8b61275eedmshe9d32ea6e4a9f06p109cc6jsnfe3c6c62e564")
            .header("X-RapidAPI-Host", "ai-chatbot.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response;

    {
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//        System.out.println(response.body());
}
