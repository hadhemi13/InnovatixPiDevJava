package tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OpenAIApiClient {
    private static final String API_KEY = "sk-proj-LriDTtRiL9JAX7e7olRAT3BlbkFJMVk5lp4pqZYOwERmgSPS";

    public static String callOpenAI(String prompt) throws Exception {
        String url = "https://api.openai.com/v1/engines/davinci/completions";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);

        String postBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 150}";
        con.setDoOutput(true);
        con.getOutputStream().write(postBody.getBytes("UTF-8"));

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
