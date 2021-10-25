import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class VisionApi {
    public String recognizeFaces(String imageFilename, String token) throws IOException, InterruptedException {
        final String base64Image = Base64.getEncoder()
                .encodeToString(Files.readAllBytes(Path.of(imageFilename)));
        return recognizeFacesBase64(base64Image, token);
    }

    public String recognizeFacesBase64(String base64Image, String token) throws IOException, InterruptedException {
        final String folderId = "b1gs3fkb74nvosinsfld";
        final String url = "https://vision.api.cloud.yandex.net/vision/v1/batchAnalyze";
        String json = "{\n" +
                "    \"folderId\": \"%s\",\n" +
                "    \"analyze_specs\": [{\n" +
                "        \"content\": \"%s\",\n" +
                "        \"features\": [{\n" +
                "            \"type\": \"FACE_DETECTION\"\n" +
                "        }]\n" +
                "    }]\n" +
                "}";

        final String jsonRequestBody = String.format(json, folderId, base64Image);
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("Authorization", token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String recognizeFaces(byte[] imageBytes, String token) throws IOException, InterruptedException {
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return recognizeFacesBase64(base64Image, token);
    }
}
