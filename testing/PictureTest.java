import static org.junit.Assert.assertTrue;

import java.awt.Image;
import org.junit.Test;
        import org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PictureTest {

  @Test
  public void imageBlur() throws IOException {
    //Picture newPic = ImageUtil.readPPM("Koala.ppm");

    ArrayList<Pixel> pixels = new ArrayList<Pixel>();
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();
    map.put(0, new ArrayList<Pixel>());
    map.put(1, new ArrayList<Pixel>());
    map.put(2, new ArrayList<Pixel>());

    for(int i = 0; i < 9; i++) {
      if(i < 3) {
        Pixel newPixel = new Pixel(new Color(0, 255, 0), new Position(i % 3, i / 3));
        pixels.add(newPixel);
        map.get(i % 3).add(newPixel);
      }
      else if(i >= 3 && i <= 5) {
        Pixel newPixel = new Pixel(new Color(255, 0, 0), new Position(i % 3, i / 3));
        pixels.add(newPixel);
        map.get(i % 3).add(newPixel);
      }
      else {
        Pixel newPixel = new Pixel(new Color(0, 0, 255), new Position(i % 3, i / 3));
        pixels.add(newPixel);
        map.get(i % 3).add(newPixel);
      }
    }

    /**
     *
     * (0,255,0)(0,255,0)(0,255,0)
     * (255,0,0)(255,0,0)(255,0,0)
     * (0,0,255)(0,0,255)(0,0,255)
     *
     */
    Picture koalapic = ImageUtil.readPPM("Koala.ppm");
    koalapic.imageBlur();

    koalapic.pictureToPPM("BlurryKoala");



  }

  @Test
  public void testGreyscale() {
    Picture koalapic = ImageUtil.readPPM("Koala.ppm");
    koalapic.imageGreyscale();
    try {
      koalapic.pictureToPPM("greyScaleKoala");
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testSepia() {
    Picture koalapic = ImageUtil.readPPM("Koala.ppm");
    koalapic.imageSepia();
    try {
      koalapic.pictureToPPM("sepiaKoala");
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }
}