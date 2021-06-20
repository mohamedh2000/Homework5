package Model.FilterModel;

import static org.junit.Assert.*;

import Model.ImageUtil;
import Model.LayerModel.Layer;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests the GreyScale Filter class.
 */
public class GreyscaleTest {

  /**
   * Tests the filter GreyScale class by checking the pixels before and after the filter.
   *
   * @throws IOException If the reading of the file fails for some reason.
   */
  @Test
  public void filter() throws IOException {
    Layer newTestLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    Layer newTestLayerGreyScale = ImageUtil.readJPEGPNG("spencer.jpeg");
    new Greyscale().filter(newTestLayerGreyScale);
    assertTrue(newTestLayer.getPixels() != newTestLayerGreyScale.getPixels());
  }

}