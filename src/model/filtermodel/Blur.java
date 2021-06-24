package model.filtermodel;

import model.layermodel.Layer;
import java.awt.image.Kernel;

/**
 * This class will be called to blur an image.
 */
public class Blur extends FilterMethods {

  /**
   * This passes in the specific blur kernel which will apply to every pixel in the image.
   *
   * @param layer The layer the filter should be applied to.
   */
  @Override
  public void filter(Layer layer) {
    float[] blurData = new float[9];
    blurData[0] = (float) (1.0 / 16.0);
    blurData[1] = (float) (1.0 / 8.0);
    blurData[2] = (float) (1.0 / 16.0);
    blurData[3] = (float) (1.0 / 8.0);
    blurData[4] = (float) (1.0 / 4.0);
    blurData[5] = (float) (1.0 / 8.0);
    blurData[6] = (float) (1.0 / 16.0);
    blurData[7] = (float) (1.0 / 8.0);
    blurData[8] = (float) (1.0 / 16.0);
    Kernel blur = new Kernel(3, 3, blurData);

    kernelApplyHelper(blur, layer);
  }
}
