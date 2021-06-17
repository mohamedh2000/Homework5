import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import Model.PixelModel.Color;
import Model.PixelModel.Position;
import Model.PixelModel.Pixel;
import org.junit.Test;

/**
 * Tests the pixel Class.
 */
public class PixelTest {

  /**
   * Tests the get Pixel.Position method.
   */
  @Test
  public void testGetPosition() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    Pixel testPixel = new Pixel(newColor, newPosn);

    assertEquals(newPosn, testPixel.getPosition());
  }

  /**
   * Tests the set colors method.
   */
  @Test
  public void testSetColors() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);
    testPixel.setColors(intlist);

    assertEquals(intlist, testPixel.getColors());
  }

  /**
   * Tests the get colors method.
   */
  @Test
  public void testGetColors() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);
    ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(255, 255, 255));

    assertEquals(integers, testPixel.getColors());
  }

  /**
   * Tests the pixelToColorString method.
   */
  @Test
  public void testPixelToColorsString() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);

    assertEquals("255 255 255", testPixel.pixelToColorsString());
  }

}