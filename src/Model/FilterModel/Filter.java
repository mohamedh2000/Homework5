package Model.FilterModel;

import Model.LayerModel.Layer;
import java.awt.image.Kernel;
import java.util.HashMap;

/**
 * Represents a general filter. It holds basic methods that should be implemented by a sub class of filter.
 */
public interface Filter {

  /**
   * Applies a filter to a picture.
   * @param layer The layer the filter should be applied to
   */
  public void filter(Layer layer);

  /**
   * This will apply the current kernel to every pixel in the layer.
   * @param toApply Which kernel we are applying to the layer.
   * @param layer Which layer we will be applying the kernel to.
   */
  public void kernelApplyHelper(Kernel toApply, Layer layer);

  /**
   * This apply the filter linearly using the intaking hashmap.
   * @param filter The filter we want used on the layer.
   * @param layer The layer we want to apply this to.
   */
  public void linearApplyHelper(HashMap<Integer, Double> filter, Layer layer);

}
