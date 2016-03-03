package scriptures.model;

import java.util.ArrayList;
import java.util.List;

public class HardCodedScriptureHandler implements ScriptureDataHandler {

  @Override
  public List<Scripture> getFavoriteScriptures() {
    List<Scripture> scriptures = new ArrayList<Scripture>();

    scriptures.add(new Scripture("Proverbs", 3, 5));
    scriptures.add(new Scripture("Doc. & Cov.", 112, 10));
    scriptures.add(new Scripture("John", 7, 17));

    return scriptures;
  }
}