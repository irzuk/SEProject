import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Jsons {

    static String workDir = "/Users/irzuk/Documents/Studying/SE/Project/";

  /*  Jsons() {
        File projectDir = new File("./metainf/");
        workDir = projectDir.getAbsolutePath();
    } */

    static public Recipe getFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(workDir + file), Recipe.class);
    }

    static public void toJson(Recipe rec, String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(file), rec);
    }

    static public Recipies getAllFromJson(String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(workDir + "recipes.json"), Recipies.class);
    }

    static public void allToJson(Recipies recs, String file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(file), recs);
    }
}