import java.util.ArrayList;
import java.util.List;

public class CookbookFuncs {
    public List<Recipe> SearchIngridient(List<Recipe> recs, Ingridient ing) {
        List<Recipe> final_recs = new ArrayList<>();
        for (Recipe rec : recs) {
            for (Ingridient i : rec.ingridients) {
                if (i.equals(ing)) {
                    final_recs.add(rec);
                    break;
                }
            }
        }
        return final_recs;
    }
}
