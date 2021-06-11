import java.lang.reflect.Array;
import java.util.ArrayList;


public class PictureCreator {

  /** Creates a picture given a width, height, and list of Colors
   *
   * @param width The width of the picture in pixels
   * @param height The height of the picture in pixels
   * @param loColor An ArrayList of Colors with one color for each pixel.
   * @return A picture.
   */
  public Picture makePicture(int width, int height, ArrayList<Color> loColor) {
    ArrayList<Pixel> loPixel = new ArrayList<Pixel>();
    for (int i = 0; i < loColor.size(); i++) {
      Pixel newPixel = new Pixel(loColor.get(i), new Position((i % width), (i / width)));
    }
    return new Picture(loPixel, null, width, height);
  }

  /** Returns a new picture with a checkerboard pattern, alternating colors each pixel.
   *
   * @param color1 The first color in the pattern.
   * @param color2 The second color in the pattern.
   * @param width The width of the picture in pixels.
   * @param height The height of the picture in pixels.
   * @return The checkerboard picture.
   */
  public Picture checkerBoard(Color color1, Color color2, int width, int height) {

    int totalSize = width * height;
    ArrayList<Color> loColor = new ArrayList<Color>();
    for (int h = 0; h < height; h++) {
      Color currColor;
      if (h % 2 == 0) {
        currColor = color1;
      }
      else {
        currColor = color2;
      }
      for (int w = 0; w < width; w++) {
        loColor.add(currColor);
        if (currColor == color1) {
          currColor = color2;
        }
        else {
          currColor = color1;
        }
      }
    }
    return makePicture(width, height, loColor);
  }
}
