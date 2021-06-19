package Model.FilterModel;

import Model.LayerModel.Layer;
import java.awt.image.Kernel;
import java.util.HashMap;

public interface Filter {

  /** Applies a filter to a picture.
   *
   */
  public void filter(Layer layer);
  public void kernelApplyHelper(Kernel toApply, Layer layer);
  public void linearApplyHelper(HashMap<Integer, Double> filter, Layer layer);

}
