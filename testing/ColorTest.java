import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.PixelModel.Color;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Test the color class to make sure it generates the correct class.
 */
public class ColorTest {

  /**
   * Tests the get Pixel.Pixel.Color Method.
   */
  @Test
  public void testGetColor() {
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> loColor = newColor.getColors();
    assertTrue(loColor.get(0) == 255 && loColor.get(1) == 255 & loColor.get(2) == 255);

  }

  /**
   * Tests the colorToString method.
   */
  @Test
  public void testColorToString() {
    Color newColor = new Color(255, 255, 255);

    assertEquals("255 255 255", newColor.toString());

  }

  /**
   * Tests the clamp Method.
   */
  @Test
  public void testColorClamp() {
    Color newColor = new Color(255, -800, 255);

    assertEquals("255 0 255", newColor.toString());
  }

}