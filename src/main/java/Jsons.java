import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Jsons {
    static public Recipe getFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new URL("file:///" + file), Recipe.class);
    }

    static public String toJson(Recipe rec, String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File f = new File(file);
        objectMapper.writeValue(f, rec);
        return f.getAbsolutePath();
    }

    static public Recipies getAllFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new URL("file:///" + "recipes.json"), Recipies.class);
    }

    static public String allToJson(Recipies recs, String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File f = new File(file);
        objectMapper.writeValue(new File(file), recs);
        return f.getAbsolutePath();
    }
}