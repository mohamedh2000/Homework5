package Model.LayerModel;

import static org.junit.Assert.assertEquals;

import Model.ImageUtil;
import Model.PixelModel.Color;
import Model.PixelModel.Pixel;
import Model.PixelModel.Position;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/** Class to test public methods of the Layer class.
 *
 */
public class LayerTest {

  @Test
  public void toggleVisibility() {
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    Layer l1 = new Layer(pixels, 1, 1);
    Layer l2 = new Layer(pixels, 1, 1);
    Layer l3 = new Layer(pixels, 1, 1);
    l2.toggleVisibility();
    l3.toggleVisibility();
    l3.toggleVisibility();

    assertEquals("Untitled Layer true true filepath\n", l1.scrapeInfo("filepath"));
    assertEquals("Untitled Layer false true filepath\n", l2.scrapeInfo("filepath"));
    assertEquals("Untitled Layer true true filepath\n", l3.scrapeInfo("filepath"));
  }


  @Test
  public void load() {
    Layer l1 = new Layer(1, 1);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    l1.load(pixels);

    assertEquals(pixels, l1.getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadNullArray() {
    Layer l1 = new Layer(1, 1);
    l1.load(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadIncorrectSizeArray() {
    Layer l1 = new Layer(10, 10);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    l1.load(pixels);
  }

  @Test
  public void getPixels() {
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    Layer l1 = new Layer(pixels, 1, 1);
    assertEquals(pixels, l1.getPixels());
  }

  @Test
  public void testJpegPngRead() throws IOException { //export top most layer
    Layer aenamiLayer = new ImageUtil().readJPEGPNG("aenami.jpeg");
    aenamiLayer.exportLayer("png");
  }

  @Test
  public void blend() throws IOException {
    Layer rainbowLayer = new ImageUtil().readPPM("rainbow.ppm");
    Layer koalaScaledLayer = new ImageUtil().readPPM("koalaScaled.ppm");
    ArrayList<Layer> blendLayers = new ArrayList<>();
    blendLayers.add(rainbowLayer);
    koalaScaledLayer.blend(blendLayers);
    koalaScaledLayer.exportLayer("ppm");
  }
}