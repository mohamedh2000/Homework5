import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tests the picture class.
 */
public class PictureTest {

    /**
     * Tests the image blur method.
     *
     * @throws IOException If the ppm has a problem being written for some reason.
     */
    @Test
    public void imageBlur() throws IOException {
        //Picture newPic = ImageUtil.readPPM("Koala.ppm");

        ArrayList<Pixel> pixels = new ArrayList<Pixel>();
        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();
        map.put(0, new ArrayList<Pixel>());
        map.put(1, new ArrayList<Pixel>());
        map.put(2, new ArrayList<Pixel>());

        for (int i = 0; i < 9; i++) {
            if (i < 3) {
                Pixel newPixel = new Pixel(new Color(0, 255, 0), new Position(i % 3, i / 3));
                pixels.add(newPixel);
                map.get(i % 3).add(newPixel);
            } else if (i >= 3 && i <= 5) {
                Pixel newPixel = new Pixel(new Color(255, 0, 0), new Position(i % 3, i / 3));
                pixels.add(newPixel);
                map.get(i % 3).add(newPixel);
            } else {
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

    /**
     * Tests to see if the grey scale method works.
     */
    @Test
    public void testGreyscale() {
        Picture koalapic = ImageUtil.readPPM("Koala.ppm");
        koalapic.imageGreyscale();
        try {
            koalapic.pictureToPPM("greyScaleKoala");
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Tests the Sepia method.
     */
    @Test
    public void testSepia() {
        Picture koalapic = ImageUtil.readPPM("Koala.ppm");
        koalapic.imageSepia();
        try {
            koalapic.pictureToPPM("sepiaKoala");
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Tests the pictureConstructor
     */
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

    /**
     * Tests the picture constructor if there is a null list passed in.
     */
    @Test(expected = IllegalArgumentException.class)
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

    /**
     * Tests output if there is an incorrect number of pixels passed in.
     */
    @Test(expected = IllegalArgumentException.class)
    public void IncorrectNumberOfPixels() {
        Color color1 = new Color(255, 0, 0);
        Pixel pixel1 = new Pixel(color1, new Position(0, 0));
        Pixel pixel2 = new Pixel(color1, new Position(0, 1));

        ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2));
        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
        map.put(0, loPixel);

        Picture testPic = new Picture(loPixel, null, 3, 1);
    }

    /**
     * Tests if the mapsize and pixel list dont match in the constructor.
     */
    @Test(expected = IllegalArgumentException.class)
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

    /**
     * Tests another file for Sepia.
     */
    @Test
    public void testSepia2() {
        Picture koalapic = ImageUtil.readPPM("Koala.ppm");
        koalapic.imageSepia();
        try {
            koalapic.pictureToPPM("KoalaSepia");
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Tests another file for GreyScale. Can be found in res folder.
     */
    @Test
    public void testGreyscale2() {
        Picture koalapic = ImageUtil.readPPM("koala.ppm");
        koalapic.imageGreyscale();
        try {
            koalapic.pictureToPPM("greyScaleKoala");
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}