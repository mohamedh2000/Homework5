package model.picturemodel;

import static junit.framework.TestCase.assertTrue;

import java.io.File;
import model.layermodel.Layer;
import model.pixelmodel.Color;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests the picture creator test.
 */
public class PictureCreatorTest {
  PictureCreator creator = new PictureCreator();

  /**
   * Tests to see if the checkerboard method works.
   *
   * @throws IOException Throws IO exception if it cannot be properly written for some reason.
   */
  @Test
  public void checkerBoard() throws IOException {
    Layer checkerBoardPic = creator
        .checkerBoard(new Color(255, 255, 255), new Color(0, 0, 0), 500, 1000, 100);
    checkerBoardPic.exportLayer("ppm");
    assertTrue(new File("Untitled Layer.ppm").exists());
  }
}