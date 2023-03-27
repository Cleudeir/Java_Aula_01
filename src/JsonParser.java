import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    
    // Regular expression pattern to match the items in the JSON string
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");

    // Regular expression pattern to match the key-value pairs in the JSON string
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    // Method to parse the JSON string and return a list of maps with key-value pairs
    public List<Map<String, String>> parse(String json) {
        
        // Use the regular expression pattern to find the items in the JSON string
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {
            // If no items are found, throw an exception
            throw new IllegalArgumentException("Não encontrou items.");
        }

        // Split the items into an array
        String[] items = matcher.group(1).split("\\},\\{");

        // Create a new list to hold the maps of key-value pairs for each item
        List<Map<String, String>> dados = new ArrayList<>();

        // Loop through each item in the array
        for (String item : items) {

            // Create a new map to hold the key-value pairs for this item
            Map<String, String> atributosItem = new HashMap<>();

            // Use the regular expression pattern to find the key-value pairs in the item
            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);

            // Loop through each key-value pair in the item
            while (matcherAtributosJson.find()) {
                // Get the key and value from the matcher group
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                // Add the key-value pair to the map
                atributosItem.put(atributo, valor);
            }

            // Add the map of key-value pairs to the list of items
            dados.add(atributosItem);
        }
        // Return the list of maps of key-value pairs for each item
        return dados;
    } 

}
