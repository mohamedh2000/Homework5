import java.io.IOException;
import org.junit.Test;

/**
 * Tests the picture creator test.
 */
public class PictureCreatorTest {

    /**
     * Tests to see if the checkerboard method works.
     * @throws IOException Throws IO exception if it cannot be properly written for some reason.
     */
  @Test
  public void checkerBoard() throws IOException {
      PictureCreator creator = new PictureCreator();
      Picture checkerBoardPic = creator.checkerBoard(new Color(255,255,255), new Color(0,0,0), 500, 1000, 100);
      checkerBoardPic.pictureToPPM("CheckerboardNEW");
  }

}