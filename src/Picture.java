import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Picture {
  List<Pixel> pixels;
  HashMap<Integer, ArrayList<Pixel>> positions;
  int width;
  int height;

  Picture(List<Pixel> pixels, HashMap<Integer, ArrayList<Pixel>> map, int width, int height) {
    this.pixels = pixels;
    this.positions = map;
    this.width = width;
    this.height = height;
  }

  public void imageBlur() {
    float[] blurData = new float[9];
    blurData[0] = 1 / 16;
    blurData[1] = 1 / 8;
    blurData[2] = 1 / 16;
    blurData[3] = 1 / 8;
    blurData[4] = 1 / 4;
    blurData[5] = 1 / 8;
    blurData[6] = 1 / 16;
    blurData[7] = 1 / 8;
    blurData[8] = 1 / 16;
    Kernel blur = new Kernel(3, 3, blurData);
    kernelApplyHelper(blur);
  }

  public void imageSharpen() {
    float[] sharpenData = new float[25];
    sharpenData[0] = -1/8;
    sharpenData[1] = -1/8;
    sharpenData[2] = -1/8;
    sharpenData[3] = -1/8;
    sharpenData[4] = -1/8;
    sharpenData[5] = -1/8;
    sharpenData[6] = 1/4;
    sharpenData[7] = 1/4;
    sharpenData[8] = 1/4;
    sharpenData[9] = -1/8;
    sharpenData[10] = -1/8;
    sharpenData[11] = 1/4;
    sharpenData[12] = 1;
    sharpenData[13] = 1/4;
    sharpenData[14] = -1/8;
    sharpenData[15] = -1/8;
    sharpenData[16] = 1/4;
    sharpenData[17] = 1/4;
    sharpenData[18] = 1/4;
    sharpenData[19] = -1/8;
    sharpenData[20] = -1/8;
    sharpenData[21] = -1/8;
    sharpenData[22] = -1/8;
    sharpenData[23] = -1/8;
    sharpenData[24] = -1/8;

    Kernel sharpen = new Kernel(5, 5, sharpenData);
    kernelApplyHelper(sharpen);

  }


  /** Transforms the colors of each pixel of an image to greyscale. Creates a colorTransoformation
   * hashmap which will be used to transform the colors to greyscale.
   *
   */
  public void imageGreyscale() {
    HashMap<Integer, Double> greyscaleFilter = new HashMap<>(9);
    greyscaleFilter.put(11, 0.2126);
    greyscaleFilter.put(21, 0.7152);
    greyscaleFilter.put(31, 0.0722);
    greyscaleFilter.put(12, 0.2126);
    greyscaleFilter.put(22, 0.7152);
    greyscaleFilter.put(32, 0.0722);
    greyscaleFilter.put(13, 0.2126);
    greyscaleFilter.put(23, 0.7152);
    greyscaleFilter.put(33, 0.0722);

    this.linearApplyHelper(greyscaleFilter);
  }

  /** Applies a sepia color filter to the image.
   *
   */
  public void imageSepia() {
    HashMap<Integer, Double> sepiaFilter = new HashMap<>(9);
    sepiaFilter.put(11, 0.393);
    sepiaFilter.put(21, 0.769);
    sepiaFilter.put(31, 0.189);
    sepiaFilter.put(12, 0.349);
    sepiaFilter.put(22, 0.686);
    sepiaFilter.put(32, 0.168);
    sepiaFilter.put(13, 0.272);
    sepiaFilter.put(23, 0.534);
    sepiaFilter.put(33, 0.131);

    this.linearApplyHelper(sepiaFilter);
  }

  private void kernelApplyHelper(Kernel toApply) {
    HashMap<Integer, ArrayList<Pixel>> mapUpdated = new HashMap<>(this.positions);
    int kernelHeight = toApply.getHeight();

    for (int row : this.positions.keySet()) {
      for (Pixel pixel : this.positions.get(row)) {
        Position pixelPosition = pixel.getPosition();
        float[] data = toApply.getKernelData(null);
        int column = pixelPosition.getColumn();

        int z = 0;

        int temp = (kernelHeight - 1) / 2;
        //iterating over every row that counts
        for (int j = temp; j < kernelHeight; j++) {
          if (temp < 0 || temp > width || temp > height) {
            continue;
          } else {
            ArrayList<Pixel> currentRow = this.positions.get(j);
            for (int k = column - temp; k < column + temp; k++) {
              if(k < 0 || k > width || k > height ) {
                continue;
              }
              Pixel currentPixel = currentRow.get(k);
              ArrayList<Integer> currentPixelColors = currentPixel.getColors();
              for (int x = 0; x < currentPixelColors.size(); x++) {
                currentPixelColors.set(x, (int) Math.round(currentPixelColors.get(x) * data[z]));
              }
              mapUpdated.get(j).get(k).setColors(currentPixelColors);
              z++;
            }
          }
        }
      }
    }
    this.positions = mapUpdated;
    ArrayList<Pixel> newPixelList = new ArrayList<>();
    for(int row : this.positions.keySet()) {
      for(Pixel pixel : this.positions.get(row)) {
        newPixelList.add(pixel);
      }
    }
    this.pixels = newPixelList;
  }

  /** Applies the filter to each pixel in the image. The filter is a HashMap with the key being a
   * two digit coordinate (11, 12, 13, 21, 22, 23, 31, 32, 33), and the value being a double.
   * The new red value of a pixel is:
   * (the value of 11 * previous red + 12 * previous green + 13 * previous blue).
   * The new green value of a pixel is:
   * (the value of 21 * previous red + 22 * previous green + 23 * previous blue).
   * The new blue value of a pixel is:
   * (the value of 31 * previous red + 32 * previous green + 33 * previous blue).
   *
   * @param filter The hashmap containing the values by which each value of each pixel is multiplied
   *               by.
   */
  private void linearApplyHelper(HashMap<Integer, Double> filter) {
    List<Pixel> workingList = new ArrayList<Pixel>();
    for (Pixel p: this.pixels) {
      ArrayList<Integer> pLoColors = p.getColors();
      float pRed = pLoColors.get(0);
      float pGreen = pLoColors.get(1);
      float pBlue = pLoColors.get(2);

      int newRed = (int) Math.round(pRed * filter.get(11) + pGreen * filter.get(21) + pBlue * filter.get(31));
      int newGreen = (int) Math.round(pRed * filter.get(12) + pGreen * filter.get(22) + pBlue * filter.get(32));
      int newBlue = (int) Math.round(pRed * filter.get(13) + pGreen * filter.get(23) + pBlue * filter.get(33));
      Color newColor = new Color(newRed, newGreen, newBlue);
      Pixel newPixel = new Pixel(newColor, p.getPosition());
    }
    this.pixels = workingList;
  }
}
