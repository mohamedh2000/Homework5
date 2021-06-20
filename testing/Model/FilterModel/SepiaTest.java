package Model.FilterModel;

import static org.junit.Assert.*;

import Model.ImageUtil;
import Model.LayerModel.Layer;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests the Sepia Filter class.
 */
public class SepiaTest {

  /**
   * Tests the filter Sepia class by checking the pixels before and after the filter.
   *
   * @throws IOException If the reading of the file fails for some reason.
   */
  @Test
  public void filter() throws IOException {
    Layer newTestLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    Layer newTestLayerSepia = ImageUtil.readJPEGPNG("spencer.jpeg");
    new Sepia().filter(newTestLayerSepia);
    assertTrue(newTestLayer.getPixels() != newTestLayerSepia.getPixels());
  }

}