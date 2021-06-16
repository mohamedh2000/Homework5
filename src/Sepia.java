import java.util.HashMap;

public class Sepia extends AbstractFilter {

  @Override
  public void add(Picture p) {

    HashMap<Integer, Double> sepiaFilter = new HashMap<>(9);
    sepiaFilter.put(11, 0.393);
    sepiaFilter.put(21, 0.769);
    sepiaFilter.put(31, 0.189);
    sepiaFilter.put(12, 0.349);
    sepiaFilter.put(22, 0.686);
    sepiaFilter.put(32, 0.168);
    sepiaFilter.put(13, 0.272);
    sepiaFilter.put(23, 0.534);
    sepiaFilter.put(33, 0.131);

    p.linearApplyHelper(sepiaFilter);
  }
}
