import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        // https://jsonplaceholder.typicode.com/posts

         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts")).build();

         client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenAccept(Main::format)
                .join();

        // -> lambda
        // :: method reference
    }
    // todo: make a formatting method
    public static void format(String responseBody) {
        JSONArray posts = new JSONArray(responseBody);

        for (int i = 0; i < posts.length(); i++) {
            JSONObject post = posts.getJSONObject(i);

            int id = post.getInt("id");
            String title = post.getString("title");
            String body = post.getString("body");

            System.out.println(id + "\n" + title + "\n" + body);
        }
    }
}