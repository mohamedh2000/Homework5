import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class ColorTest {

  @Test
  public void testGetColor() {
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> loColor = newColor.getColors();
    assertTrue(loColor.get(0) == 255 && loColor.get(1) == 255 & loColor.get(2) == 255);

  }

  @Test
  public void testColorToString() {
    Color newColor = new Color(255, 255, 255);

    assertEquals("255 255 255", newColor.toString());

  }

  @Test
  public void testColorClamp() {
    Color newColor = new Color(255, -800, 255);

    assertEquals("255 0 255", newColor.toString());
  }

}