package model.pixelmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Class used to test public methods of the Position class.
 *
 */
public class PositionTest {

  /**
   * Tests to see if get row correctly works.
   */
  @Test
  public void testGetRow() {
    Position p1 = new Position(3, 5);

    assertEquals(3, p1.getRow());
  }

  /**
   * Tests to see if get Column correctly works.
   */
  @Test
  public void testGetColumn() {
    Position p1 = new Position(3, 5);

    assertEquals(5, p1.getColumn());
  }

}