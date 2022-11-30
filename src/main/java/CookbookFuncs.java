import java.util.ArrayList;
import java.util.List;

public class CookbookFuncs {
    public List<Recipe> SearchIngridients(List<Recipe> recs, List<Ingridient> ings) {
        List<Recipe> final_recs = new ArrayList<>();
        for (Recipe rec : recs) {
            boolean flag = true;
            for (Ingridient i : ings) {
                if (!hasIngridient(rec, i)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                final_recs.add(rec);
        }
        return final_recs;
    }

    private Boolean hasIngridient(Recipe rec, Ingridient ing) {
        for (Ingridient i : rec.ingridients) {
            if (i.equals(ing)) {
                return true;
            }
        }
        return false;
    }
}
