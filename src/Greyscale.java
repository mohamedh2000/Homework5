import java.util.HashMap;

public class Greyscale extends AbstractFilter {

  public void add(Picture p) {
    HashMap<Integer, Double> greyscaleFilter = new HashMap<>(9);
    greyscaleFilter.put(11, 0.2126);
    greyscaleFilter.put(21, 0.7152);
    greyscaleFilter.put(31, 0.0722);
    greyscaleFilter.put(12, 0.2126);
    greyscaleFilter.put(22, 0.7152);
    greyscaleFilter.put(32, 0.0722);
    greyscaleFilter.put(13, 0.2126);
    greyscaleFilter.put(23, 0.7152);
    greyscaleFilter.put(33, 0.0722);

    p.linearApplyHelper(greyscaleFilter);
  }

}
