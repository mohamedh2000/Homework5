import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class PictureTest {

  @Test
  public void imageBlur() throws IOException {
//    //Picture newPic = ImageUtil.readPPM("Koala.ppm");
//
//    ArrayList<Pixel> pixels = new ArrayList<Pixel>();
//    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();
//    map.put(0, new ArrayList<Pixel>());
//    map.put(1, new ArrayList<Pixel>());
//    map.put(2, new ArrayList<Pixel>());
//
//    for(int i = 0; i < 9; i++) {
//      if(i < 3) {
//        Pixel newPixel = new Pixel(new Color(0, 255, 0), new Position(i % 3, i / 3));
//        pixels.add(newPixel);
//        map.get(i % 3).add(newPixel);
//      }
//      else if(i >= 3 && i <= 5) {
//        Pixel newPixel = new Pixel(new Color(255, 0, 0), new Position(i % 3, i / 3));
//        pixels.add(newPixel);
//        map.get(i % 3).add(newPixel);
//      }
//      else {
//        Pixel newPixel = new Pixel(new Color(0, 0, 255), new Position(i % 3, i / 3));
//        pixels.add(newPixel);
//        map.get(i % 3).add(newPixel);
//      }
//    }

    Picture koalaPic = new ImageUtil().readPPM("Koala.ppm");
    koalaPic.imageSharpen();
    koalaPic.pictureToPPM("ImageSharpen");

  }
}