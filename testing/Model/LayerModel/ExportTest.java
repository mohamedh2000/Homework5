package Model.LayerModel;

import static org.junit.Assert.assertTrue;

import Model.ImageUtil;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

/** Class to test public methods of the Export class.
 *
 */
public class ExportTest {

  /**
   * Tests the PictureToPPM method.
   * @throws IOException
   */
  @Test
  public void testPictureToPPM() throws IOException {
    Layer test = ImageUtil.readJPEGPNG("spencer.jpeg");
    test.exportLayer("ppm");
    assertTrue(new File("Untitled Layer").exists());
  }

  /**
   * Tests the pictureToJpeg method.
   * @throws IOException
   */
  @Test
  public void testPictureToJPEG() throws IOException {
    Layer test = ImageUtil.readJPEGPNG("spencer.jpeg");
    test.exportLayer("jpeg");
    assertTrue(new File("Untitled Layer").exists());
  }

  /**
   * Tests the pictureToPng method.
   * @throws IOException
   */
  @Test
  public void testPictureToPNG() throws IOException {
    Layer test = ImageUtil.readJPEGPNG("spencer.jpeg");
    test.exportLayer("png");
    assertTrue(new File("Untitled Layer").exists());
  }

}