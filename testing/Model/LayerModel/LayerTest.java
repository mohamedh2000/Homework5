package Model.LayerModel;

import static org.junit.Assert.assertEquals;

import Model.ImageUtil;
import Model.PixelModel.Color;
import Model.PixelModel.Pixel;
import Model.PixelModel.Position;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;

/** Class to test public methods of the Layer class.
 *
 */
public class LayerTest {

  /**
   * Tests the toggle visibility method.
   */
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

  /**
   * Tests that you  can't load a null array;
   */
  @Test(expected = IllegalArgumentException.class)
  public void loadNullArray() {
    Layer l1 = new Layer(1, 1);
    l1.load(null);
  }

  /**
   * Tests you can't load an incorrect size Array.
   */
  @Test(expected = IllegalArgumentException.class)
  public void loadIncorrectSizeArray() {
    Layer l1 = new Layer(10, 10);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    l1.load(pixels);
  }

  /**
   * Tests the getPixels method.
   */
  @Test
  public void getPixels() {
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    Layer l1 = new Layer(pixels, 1, 1);
    assertEquals(pixels, l1.getPixels());
  }

  /**
   * Tests the jpegPngRead method.
   * @throws IOException
   */
  @Test
  public void testJpegPngRead() throws IOException { //export top most layer
    Layer aenamiLayer = new ImageUtil().readJPEGPNG("aenami.jpeg");
    aenamiLayer.exportLayer("png");
  }

  /**
   * Tests the blend method.
   * @throws IOException
   */
  @Test
  public void testBlend() throws IOException {
    Layer rainbowLayer = new ImageUtil().readPPM("rainbow.ppm");
    Layer koalaScaledLayer = new ImageUtil().readPPM("koalaScaled.ppm");
    ArrayList<Layer> blendLayers = new ArrayList<>();
    blendLayers.add(rainbowLayer);
    koalaScaledLayer.blend(blendLayers);
    koalaScaledLayer.exportLayer("ppm");
  }

  /**
   * Tests the getWidth method.
   */
  @Test
  public void getWidth() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.getWidth(), 100);
  }

  /**
   * Tests the getHeight method.
   */
  @Test
  public void getHeight() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.getHeight(), 200);
  }

  /**
   * Tests the getPixelPositions method.
   * @throws IOException
   */
  @Test
  public void getPixelPositions() throws IOException {
    Layer testLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    Layer testLayerII = ImageUtil.readPPM("spencer.jpeg");
    assertEquals(testLayer.getPixelPositions(), testLayerII.getPixelPositions());
  }

  /**
   * Tests the setPixels method.
   */
  @Test
  public void setPixels() {
    Layer testLayer = new Layer(100,200);
    testLayer.setPixels(new ArrayList<>());
    assertEquals(testLayer.getPixels(), new ArrayList<>());
  }

  /**
   * Tests the setPixelPositions method.
   */
  @Test
  public void setPixelPositions() {
    Layer testLayer = new Layer(100,200);
    testLayer.setPixelPositions(new HashMap<>());
    assertEquals(testLayer.getPixelPositions(), new HashMap<>());
  }

  /**
   * Tests the changeName method.
   */
  @Test
  public void changeName() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.name, "Untitled Layer");
    testLayer.changeName("TEST");
    assertEquals(testLayer.name, "TEST");
  }

  /**
   * Tests the toggleVisibility method.
   */
  @Test
  public void testToggleVisibility() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.getVisibility(), true);
    testLayer.toggleVisibility();
    assertEquals(testLayer.getVisibility(), false);
  }

  /**
   * Tests the getVisibility method.
   */
  public void testGetVisibility() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.getVisibility(), true);
  }

  /**
   * Tests the currentLayer method.
   */
  @Test
  public void toggleCurrentLayer() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.isCurrent(), true);
    testLayer.toggleCurrentLayer();
    assertEquals(testLayer.isCurrent(), false);
  }

  /**
   * Tests the load method.
   * @throws IOException
   */
  @Test
  public void testLoad() throws IOException {
    Layer testLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    Layer testLayerII = new Layer(testLayer.getWidth(), testLayer.getHeight());
    testLayer.load(testLayerII.getPixels());
    assertEquals(testLayer.getPixels(), testLayerII.getPixels());
  }

  /**
   * Tests the getPixels method.
   */
  @Test
  public void testGetPixels() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.getPixels(), new ArrayList<Pixel>());
  }

  /**
   * Tests the isCurrent Method.
   */
  @Test
  public void isCurrent() {
    Layer testLayer = new Layer(100,200);
    assertEquals(testLayer.isCurrent(), true);
    testLayer.toggleCurrentLayer();
    assertEquals(testLayer.isCurrent(), false);
  }

  /**
   * Tests the export Layer method.
   * @throws IOException
   */
  @Test
  public void exportLayer() throws IOException {
    Layer testLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    testLayer.changeName("TESTLAYER");
    testLayer.exportLayer("ppm");
    assertEquals(new File("TESTLAYER.ppm").exists(), true);
  }

  /**
   * Tests the scrapeInfo method.
   * @throws IOException
   */
  @Test
  public void scrapeInfo() throws IOException {
    Layer testLayer = ImageUtil.readJPEGPNG("spencer.jpeg");
    testLayer.changeName("TESTLAYER");
    assertEquals(testLayer.scrapeInfo("TESTPATH"), "TESTLAYER true true TESTPATH \n");
  }

}