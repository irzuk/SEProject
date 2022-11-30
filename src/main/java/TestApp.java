import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestApp {
    @Test
    public void simpleFileTest() throws IOException {
        Recipe recipe = new Recipe();
        recipe.name = "kek";
        recipe.instruction = "lol";
        Jsons.toJson(recipe, "aboba.json");
        Recipe recipe1 = Jsons.getFromJson("aboba.json");
        assertEquals("kek", recipe1.name);
        assertEquals("lol", recipe1.instruction);
    }

    @Test
    public void simpleRecipesTest() throws IOException {
        Recipe recipe1 = new Recipe();
        recipe1.name = "rec1";
        recipe1.instruction = "inst1";
        Recipe recipe2 = new Recipe();
        recipe2.name = "rec2";
        recipe2.instruction = "inst2";
        Jsons.toJson(recipe1, "rec1.json");
        Jsons.toJson(recipe2, "rec2.json");
        Recipies recs = new Recipies();
        List recss = new ArrayList<>();
        recss.add("rec1");
        recss.add("rec2");
        recs.names = recss;
        Jsons.allToJson(recs, "recipes.json");
        Recipe read_rec1 = Jsons.getFromJson("rec1.json");
        Recipe read_rec2 = Jsons.getFromJson("rec2.json");
        assertEquals("rec1", read_rec1.name);
        assertEquals("inst1", read_rec1.instruction);
        assertEquals("rec2", read_rec2.name);
        assertEquals("inst2", read_rec2.instruction);
        Recipies read_recs = Jsons.getAllFromJson("recipes.json");
        assertEquals(read_recs.names, recss);
    }

    @Test
    public void saveIngridientTest() throws IOException {
        Recipe rec = new Recipe();
        rec.name = "rec";
        rec.instruction = "inst";
        Ingridient ing = new Ingridient();
        ing.name = "ing";
        ing.amount = 228;
        ing.unit = "sugoma";
        List<Ingridient> ingss = new ArrayList<>();
        ingss.add(ing);
        rec.ingridients = ingss;
        Jsons.toJson(rec, "rec.json");
        Recipe read_rec = Jsons.getFromJson("rec.json");
        assertEquals(read_rec.name, "rec");
        assertEquals(read_rec.instruction, "inst");
        assertEquals(read_rec.ingridients.get(0).name, "ing");
        assertEquals(read_rec.ingridients.get(0).amount, 228);
        assertEquals(read_rec.ingridients.get(0).unit, "sugoma");
    }
}
