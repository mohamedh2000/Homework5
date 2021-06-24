package model.filtermodel;

import model.layermodel.Layer;
import java.awt.image.Kernel;

/**
 * This will apply a sharpen filter to the given layer.
 */
public class Sharpen extends FilterMethods {

  /**
   * Applies a filter to a picture.
   *
   * @param layer The layer the filter should be applied to
   */
  @Override
  public void filter(Layer layer) {
    float[] sharpenData = new float[25];
    sharpenData[0] = (float) (-1.0 / 8.0);
    sharpenData[1] = (float) (-1.0 / 8.0);
    sharpenData[2] = (float) (-1.0 / 8.0);
    sharpenData[3] = (float) (-1.0 / 8.0);
    sharpenData[4] = (float) (-1.0 / 8.0);
    sharpenData[5] = (float) (-1.0 / 8.0);
    sharpenData[6] = (float) (1.0 / 4.0);
    sharpenData[7] = (float) (1.0 / 4.0);
    sharpenData[8] = (float) (1.0 / 4.0);
    sharpenData[9] = (float) (-1.0 / 8.0);
    sharpenData[10] = (float) (-1.0 / 8.0);
    sharpenData[11] = (float) (1.0 / 4.0);
    sharpenData[12] = (float) 1.0;
    sharpenData[13] = (float) (1.0 / 4.0);
    sharpenData[14] = (float) (-1.0 / 8.0);
    sharpenData[15] = (float) (-1.0 / 8.0);
    sharpenData[16] = (float) (1.0 / 4.0);
    sharpenData[17] = (float) (1.0 / 4.0);
    sharpenData[18] = (float) (1.0 / 4.0);
    sharpenData[19] = (float) (-1.0 / 8.0);
    sharpenData[20] = (float) (-1.0 / 8.0);
    sharpenData[21] = (float) (-1.0 / 8.0);
    sharpenData[22] = (float) (-1.0 / 8.0);
    sharpenData[23] = (float) (-1.0 / 8.0);
    sharpenData[24] = (float) (-1.0 / 8.0);

    Kernel sharpen = new Kernel(5, 5, sharpenData);
    kernelApplyHelper(sharpen, layer);
  }
}
