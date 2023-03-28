import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ImgToAscii {

    public ImgToAscii() {
    }

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static String transform (String url) throws IOException, InterruptedException{
        // doc https://www.filestack.com/docs/api/file/
        String urlConvert = "https://process.filestackapi.com/" + System.getenv("API_KEY_FILESTACK") +"/ascii=c:false,b:black,r:true,s:80/" + url;
        var client = HttpClient.newHttpClient();
        URI link = URI.create(urlConvert);
        var req = HttpRequest.newBuilder(link).GET().build();
        HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
        String body = resp.body();
        String regex = "(?<=<pre[^>]*>)(.*?)(?=<\\/pre>)";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(body);

        var textConverted = "";

        if (matcher.find()) {
            String preContent = matcher.group(1);
            textConverted = preContent;
        }
        return textConverted.replaceAll("<br>", "\n");
    }
}