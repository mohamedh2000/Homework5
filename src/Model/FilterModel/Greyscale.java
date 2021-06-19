package Model.FilterModel;

import Model.LayerModel.Layer;
import java.util.HashMap;

/**
 * This will apply a greyscale filter to a layer.
 */
public class Greyscale extends filterMethods {

  /**
   * Applies a filter to a picture.
   * @param layer The layer the filter should be applied to
   */
  public void filter(Layer layer) {
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

    linearApplyHelper(greyscaleFilter, layer);
  }

}
