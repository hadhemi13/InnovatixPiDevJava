package services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONObject;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
public class YouSignService {

    private static final String API_KEY = "9h5fd7zBM0KB5KhHyP8L41Hf0Nx69Pgf";
    private static final String BASE_URL = "https://api-sandbox.yousign.app/v3";

    public static JSONObject initiateSignatureRequest(String requestName) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String requestBody = String.format("{\"name\": \"%s\", \"delivery_mode\": \"email\", \"timezone\": \"Europe/Paris\"}", requestName);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/signature_requests"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response body: " + response.body());

            return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject uploadDocument(String signatureRequestId, String documentPath) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "signature_requests/" + signatureRequestId + "/documents"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "multipart/form-data")
                    .POST(BodyPublishers.ofFile(Path.of(documentPath)))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject addSigner(String signatureRequestId, String signerInfo, String documentId) {
        try {
            JSONObject signerInfoJson = new JSONObject(signerInfo);
            JSONObject requestBody = new JSONObject();
            requestBody.put("info", signerInfoJson);
            requestBody.put("signature_level", "electronic_signature");
            requestBody.put("signature_authentication_mode", "no_otp");

            JSONArray fieldsArray = new JSONArray();
            JSONObject field = new JSONObject();
            field.put("document_id", documentId);
            field.put("type", "signature");
            field.put("page", 1);
            field.put("width", 180);
            field.put("x", 400);
            field.put("y", 650);
            fieldsArray.put(field);
            requestBody.put("fields", fieldsArray);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "signature_requests/" + signatureRequestId + "/signers"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject activateSignatureRequest(String signatureRequestId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "signature_requests/" + signatureRequestId + "/activate"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString("{}"))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
