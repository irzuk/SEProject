import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Jsons {
    static public Recipe getFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new URL("file:///" + file), Recipe.class);
    }

    static public void toJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(file), Recipe.class);
    }

    static public Recipies getAllFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new URL("file:///" + "recipes.json"), Recipies.class);
    }

    static public void allToJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(file), Recipies.class);
    }
}