import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class App {   
     static String[] endPoint = new String[]{
        "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json",
        "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json",
        "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json",
       "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json"
    };
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        String url = endPoint[randomNumber];
        var client = HttpClient.newHttpClient();
        URI link = URI.create(url);
        var req = HttpRequest.newBuilder(link).GET().build();
        HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
        String body = resp.body();
        var parser = new JsonParser();
        List<Map<String, String>> list = parser.parse(body);
        for (Map<String,String> movie : list) {
           var print = new Print();
           print.show(movie);
           Thread.sleep(2000);
        }
    }

}
