
import Model.FilterModel.Blur;
import Model.FilterModel.Greyscale;
import Model.FilterModel.Sepia;
import Model.ImageUtil;
import Model.LayerModel.Layer;
import java.io.IOException;
import org.junit.Test;

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
    Layer newPic = Model.ImageUtil.readPPM("KoalaScaled.ppm");

    new Blur().filter(newPic);
    newPic.changeName("UPDATEDBLURTEST");
    newPic.exportLayer("jpeg");
  }

  /**
   * Tests to see if the grey scale method works.
   */
  @Test
  public void testGreyscale() throws IOException {
    Layer newPic = Model.ImageUtil.readPPM("KoalaScaled.ppm");

    new Greyscale().filter(newPic);
    newPic.changeName("UPDATEDGREYSCALEDTEST");
    newPic.exportLayer("jpeg");

  }

  /**
   * Tests the Model.FilterModel.Sepia method.
   */
  @Test
  public void testSepia() throws IOException {
    Layer newPic = Model.ImageUtil.readPPM("KoalaScaled.ppm");

    new Sepia().filter(newPic);
    newPic.changeName("UPDATEDGREYSCALEDTEST");
    newPic.exportLayer("jpeg");
  }

//    /**
//     * Tests the pictureConstructor
//     */
//    @Test
//    public void testPictureConstructor() {
//        Color color1 = new Color(255, 0, 0);
//        Pixel pixel1 = new Pixel(color1, new Position(0, 0));
//        Pixel pixel2 = new Pixel(color1, new Position(0, 1));
//        Pixel pixel3 = new Pixel(color1, new Position(0, 2));
//
//        ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2, pixel3));
//        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
//        map.put(0, loPixel);
//
//        Picture testPic = new Picture(loPixel, map, 3, 1);
//    }
//
//    /**
//     * Tests the picture constructor if there is a null list passed in.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testPictureConstructorNullLoPixel() {
//        Color color1 = new Color(255, 0, 0);
//        Pixel pixel1 = new Pixel(color1, new Position(0, 0));
//        Pixel pixel2 = new Pixel(color1, new Position(0, 1));
//        Pixel pixel3 = new Pixel(color1, new Position(0, 2));
//
//        ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2, pixel3));
//        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
//        map.put(0, loPixel);
//
//        Picture testPic = new Picture(null, map, 3, 1);
//    }

//    /**
//     * Tests output if there is an incorrect number of pixels passed in.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void IncorrectNumberOfPixels() {
//        Color color1 = new Color(255, 0, 0);
//        Pixel pixel1 = new Pixel(color1, new Position(0, 0));
//        Pixel pixel2 = new Pixel(color1, new Position(0, 1));
//
//        ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2));
//        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
//        map.put(0, loPixel);
//
//        Picture testPic = new Picture(loPixel, null, 3, 1);
//    }

//    /**
//     * Tests if the mapsize and pixel list dont match in the constructor.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void mapSizeAndPixelListSizeDontMatch() {
//        Color color1 = new Color(255, 0, 0);
//        Pixel pixel1 = new Pixel(color1, new Position(0, 0));
//        Pixel pixel2 = new Pixel(color1, new Position(0, 1));
//        Pixel pixel3 = new Pixel(color1, new Position(0, 2));
//
//        ArrayList<Pixel> loPixel = new ArrayList<Pixel>(Arrays.asList(pixel1, pixel2));
//        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();
//        map.put(0, new ArrayList<Pixel>());
//
//        Picture testPic = new Picture(loPixel, null, 3, 1);
//    }

  /**
   * Tests another file for Model.FilterModel.Sepia.
   */
  @Test
  public void testSepia2() {
    //Picture koalapic = ImageUtil.readPPM("Koala.ppm");
    //new Sepia().filter(koalapic);
    //try {
    //koalapic.pictureToPPM("KoalaSepia");
    //} catch (IOException e) {
    //    assertTrue(false);
    //}
  }

  /**
   * Tests another file for GreyScale. Can be found in res folder.
   */
  @Test
  public void testGreyscale2() {
    //Picture koalapic = ImageUtil.readPPM("koala.ppm");
    //new Greyscale().filter(koalapic);
    //try {
    //koalapic.pictureToPPM("greyScaleKoala");
    //} catch (IOException e) {
    //    assertTrue(false);
    //}
  }

  @Test
  public void testBlur() {
    Layer spencerpic = ImageUtil.readPPM("koala.ppm");
    //new Blur().filter(spencerpic);
    //try {
    //spencerpic.pictureToPPM("spencerBlur");
    //}
    //catch (IOException e) {
    //    assertTrue(false);
    //}
  }

}