package model.filtermodel;

import static org.junit.Assert.assertTrue;

import model.ImageUtil;
import model.layermodel.Layer;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests the Blur Filter class.
 */
public class BlurTest {

  /**
   * Tests the filter blur method by checking the pixels before and after the filter.
   * @throws IOException If the reading of the file fails for some reason.
   */
  @Test
  public void filter() throws IOException {
    Layer newTestLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    Layer newTestLayerBlur = ImageUtil.readJPEGPNG("spencer.jpeg");
    new Blur().filter(newTestLayerBlur);
    assertTrue(newTestLayer.getPixels() != newTestLayerBlur.getPixels());
  }
}