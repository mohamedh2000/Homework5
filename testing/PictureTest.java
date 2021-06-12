import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.util.Arrays;
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

  @Test
  public void testPictureConstructor() {
    Color color1 = new Color(255, 0, 0);
    Pixel pixel1 = new Pixel(color1, new Position(0, 0));
    Pixel pixel2 = new Pixel(color1, new Position(0, 1));
    Pixel pixel3 = new Pixel(color1, new Position(0, 2));

    ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2, pixel3));
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
    map.put(0, loPixel);

    Picture testPic = new Picture(loPixel, map, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPictureConstructorNullLoPixel() {
    Color color1 = new Color(255, 0, 0);
    Pixel pixel1 = new Pixel(color1, new Position(0, 0));
    Pixel pixel2 = new Pixel(color1, new Position(0, 1));
    Pixel pixel3 = new Pixel(color1, new Position(0, 2));

    ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2, pixel3));
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
    map.put(0, loPixel);

    Picture testPic = new Picture(null, map, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void IncorrectNumberOfPixels() {
    Color color1 = new Color(255, 0, 0);
    Pixel pixel1 = new Pixel(color1, new Position(0, 0));
    Pixel pixel2 = new Pixel(color1, new Position(0, 1));

    ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2));
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
    map.put(0, loPixel);

    Picture testPic = new Picture(loPixel, null, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void mapSizeAndPixelListSizeDontMatch() {
    Color color1 = new Color(255, 0, 0);
    Pixel pixel1 = new Pixel(color1, new Position(0, 0));
    Pixel pixel2 = new Pixel(color1, new Position(0, 1));
    Pixel pixel3 = new Pixel(color1, new Position(0, 2));

    ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2));
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
    map.put(0, new ArrayList<Pixel>());

    Picture testPic = new Picture(loPixel, null, 3, 1);
  }



  @Test
  public void testSepia2() {
    Picture koalapic = ImageUtil.readPPM("Spencer.ppm");
    koalapic.imageSepia();
    try {
      koalapic.pictureToPPM("sepiaSpencer");
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testGreyscale2() {
    Picture koalapic = ImageUtil.readPPM("Spencer.ppm");
    koalapic.imageGreyscale();
    try {
      koalapic.pictureToPPM("greyScaleSpencer");
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }
}