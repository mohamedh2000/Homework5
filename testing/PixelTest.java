import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class PixelTest {

  @Test
  public void testGetPosition() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    Pixel testPixel = new Pixel(newColor, newPosn);

    assertEquals(newPosn, testPixel.getPosition());
  }

  @Test
  public void testSetColors() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);
    testPixel.setColors(intlist);

    assertEquals(intlist, testPixel.getColors());
  }

  @Test
  public void testGetColors() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);
    ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(255, 255, 255));

    assertEquals(integers, testPixel.getColors());
  }

  @Test
  public void testPixelToColorsString() {
    Position newPosn = new Position(0, 0);
    Color newColor = new Color(255, 255, 255);

    ArrayList<Integer> intlist = new ArrayList<Integer>(Arrays.asList(255, 255, 255));

    Pixel testPixel = new Pixel(newColor, newPosn);

    assertEquals("255 255 255", testPixel.pixelToColorsString());
  }

}