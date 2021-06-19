package Model.PixelModel;

import static org.junit.Assert.*;

import org.junit.Test;

/** Class used to test public methods of the Position class.
 *
 */
public class PositionTest {

  @Test
  public void testGetRow() {
    Position p1 = new Position(3, 5);

    assertEquals(3, p1.getRow());
  }

  @Test
  public void testGetColumn() {
    Position p1 = new Position(3, 5);

    assertEquals(5, p1.getColumn());
  }

}