import java.util.ArrayList;
import java.util.List;

public class CookbookFuncs {

    public static List<Recipe> SearchIngridient(List<Recipe> recs, String ing) {
        List<Recipe> final_recs = new ArrayList<>();
        for (Recipe rec : recs) {
            for (Ingridient i : rec.ingridients) {
                if (i.name.equals(ing)) {
                    final_recs.add(rec);
                    break;
                }
            }
        }
        return final_recs;
    }

    static private Boolean hasIngridient(Recipe rec, Ingridient ing) {
        for (Ingridient i : rec.ingridients) {
            if (i.equals(ing)) {
                return true;
            }
        }
        return false;
    }
}
