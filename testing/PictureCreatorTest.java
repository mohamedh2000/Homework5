import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PictureCreatorTest {

    @Test
    public void makePicture() {


    }

    @Test
    public void checkerBoard() throws IOException {
        PictureCreator creator = new PictureCreator();
        Picture checkerBoardPic = creator.checkerBoard(new Color(255,255,255), new Color(0,0,0), 500, 1000, 100);
        checkerBoardPic.pictureToPPM("CheckerboardNEW");
    }
}