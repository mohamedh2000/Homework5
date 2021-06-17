import java.awt.image.Kernel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Represents a picture.
 */
public class Picture {

  private Layer currLayer;
  private List<Layer> layers;
  private HashMap<Integer, ArrayList<Pixel>> pixelToRow;
  private final int width;
  private final int height;

  /**
   * Creates a picture
   *
   * @param pixels The list of all pixels
   * @param map    A hashmap with the key being the row, and the value being an arraylist of all
   *               pixels in the row.
   * @param width  The width of the picture in pixels.
   * @param height The height of the picture in pixels.
   */
  Picture(List<Pixel> pixels, HashMap<Integer, ArrayList<Pixel>> map, int width, int height) {
    if (Objects.isNull(pixels) || Objects.isNull(map) || Objects.isNull(width) || Objects
        .isNull(height)) {
      throw new IllegalArgumentException("Null arguments.");
    }
    if (width < 1) {
      throw new IllegalArgumentException("Invalid width");
    }
    if (height < 1) {
      throw new IllegalArgumentException("Invalid height");
    }
    if (width * height != pixels.size()) {
      throw new IllegalArgumentException("Size of pixels doesn't match picture dimensions");
    }
    this.currLayer = new Layer(pixels);
    this.pixelToRow = map;
    this.width = width;
    this.height = height;
  }

  /**
   * The channel of the color. Red, Green, or Blue.
   */
  private enum Channel {
    RED, GREEN, BLUE
  }

  /**
   * Blurs an image by applying a kernel.
   */
  public void imageBlur() {
    float[] blurData = new float[9];
    blurData[0] = (float) (1.0 / 16.0);
    blurData[1] = (float) (1.0 / 8.0);
    blurData[2] = (float) (1.0 / 16.0);
    blurData[3] = (float) (1.0 / 8.0);
    blurData[4] = (float) (1.0 / 4.0);
    blurData[5] = (float) (1.0 / 8.0);
    blurData[6] = (float) (1.0 / 16.0);
    blurData[7] = (float) (1.0 / 8.0);
    blurData[8] = (float) (1.0 / 16.0);
    Kernel blur = new Kernel(3, 3, blurData);
    kernelApplyHelper(blur);
  }

  /**
   * Creates a PPM file corresponding to the picture.
   *
   * @param fileName The name of the ppm file.
   * @throws IOException
   */
  public void pictureToPPM(String fileName) throws IOException {
    FileWriter ppmObjectWriter = new FileWriter(fileName + ".ppm");

    String Header = "P3" + "\n" + this.width + " " + this.height + "\n" + "255";
    ppmObjectWriter.write(Header);
    for (Integer row : this.pixelToRow.keySet()) {
      String rowString = "\n";
      for (Pixel nextPixel : this.pixelToRow.get(row)) {
        ArrayList<Integer> pixelColor = nextPixel.getColors();
        for (Integer channel : pixelColor) {
          rowString += channel + " ";
        }
      }
      ppmObjectWriter.write(rowString);
    }
    ppmObjectWriter.close();
  }

  /**
   * Sharpens the image by applying a kernel.
   */
  public void imageSharpen() {
    float[] sharpenData = new float[25];
    sharpenData[0] = (float) (-1.0 / 8.0);
    sharpenData[1] = (float) (-1.0 / 8.0);
    sharpenData[2] = (float) (-1.0 / 8.0);
    sharpenData[3] = (float) (-1.0 / 8.0);
    sharpenData[4] = (float) (-1.0 / 8.0);
    sharpenData[5] = (float) (-1.0 / 8.0);
    sharpenData[6] = (float) (1.0 / 4.0);
    sharpenData[7] = (float) (1.0 / 4.0);
    sharpenData[8] = (float) (1.0 / 4.0);
    sharpenData[9] = (float) (-1.0 / 8.0);
    sharpenData[10] = (float) (-1.0 / 8.0);
    sharpenData[11] = (float) (1.0 / 4.0);
    sharpenData[12] = (float) 1.0;
    sharpenData[13] = (float) (1.0 / 4.0);
    sharpenData[14] = (float) (-1.0 / 8.0);
    sharpenData[15] = (float) (-1.0 / 8.0);
    sharpenData[16] = (float) (1.0 / 4.0);
    sharpenData[17] = (float) (1.0 / 4.0);
    sharpenData[18] = (float) (1.0 / 4.0);
    sharpenData[19] = (float) (-1.0 / 8.0);
    sharpenData[20] = (float) (-1.0 / 8.0);
    sharpenData[21] = (float) (-1.0 / 8.0);
    sharpenData[22] = (float) (-1.0 / 8.0);
    sharpenData[23] = (float) (-1.0 / 8.0);
    sharpenData[24] = (float) (-1.0 / 8.0);

    Kernel sharpen = new Kernel(5, 5, sharpenData);
    kernelApplyHelper(sharpen);
  }

  /**
   * Transforms the colors of each pixel of an image to greyscale. Creates a colorTransoformation
   * hashmap which will be used to transform the colors to greyscale.
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

  /**
   * Applies a sepia color filter to the image.
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


  /**
   * Applies filtering to each pixel. If the pixel/row doesn't exist it will just skip onto the
   * next.
   *
   * @param toApply The kernel that we want to retrieve the values from.
   */
  public void kernelApplyHelper(Kernel toApply) {
    HashMap<Integer, ArrayList<Pixel>> mapUpdated = this.pixelToRow;
    int kernelHeight = toApply.getHeight();
    List<Channel> Channels = Arrays.asList(Channel.values());

    for (int row : this.pixelToRow.keySet()) {
      System.out.println("ROW: " + row);
      for (Pixel pixel : this.pixelToRow.get(row)) {
        int pixelIndex = mapUpdated.get(row).indexOf(pixel);
        float[] data = toApply.getKernelData(null);

        int temp = (kernelHeight - 1) / 2;

        int totalRed = 0;
        int totalBlue = 0;
        int totalGreen = 0;
        int currentRow = row - temp;
        for (int i = 0; i < (kernelHeight * kernelHeight); i++) {
          if (i != 0 && (i % kernelHeight == 0) && currentRow >= 0) {
            currentRow += 1;
          }
          if (currentRow < 0) {
            currentRow += 1;
            i += (kernelHeight - 1);
            continue;
          }
          if (mapUpdated.containsKey(currentRow)) {
            ArrayList<Pixel> currentPixelList = mapUpdated.get(currentRow);
            for (int k = pixelIndex - temp; k < pixelIndex + temp; k++) {
              if (!(k < 0 || k >= mapUpdated.get(currentRow).size())) {
                ArrayList<Integer> pixelColors = currentPixelList.get(k)
                    .getColors();
                totalRed += Math.floor(pixelColors.get(0) * data[i]);
                totalGreen += Math.floor(pixelColors.get(1) * data[i]);
                totalBlue += Math.floor(pixelColors.get(2) * data[i]);
              }
              i++;
            }
          }
        }
        if (totalRed > 256) {
          totalRed = 255;
        }
        if (totalGreen > 256) {
          totalGreen = 255;
        }
        if (totalBlue > 256) {
          totalBlue = 255;
        }
        if (totalRed < 0) {
          totalRed = 0;
        }
        if (totalGreen < 0) {
          totalGreen = 0;
        }
        if (totalBlue < 0) {
          totalBlue = 0;
        }

        ArrayList<Integer> newColor = new ArrayList<>();
        newColor.add(totalRed);
        newColor.add(totalGreen);
        newColor.add(totalBlue);
        mapUpdated.get(row).get(pixelIndex).setColors(newColor);
      }
    }
  }


  /**
   * Applies the filter to each pixel in the image. The filter is a HashMap with the key being a two
   * digit coordinate (11, 12, 13, 21, 22, 23, 31, 32, 33), and the value being a double. The new
   * red value of a pixel is: (the value of 11 * previous red + 12 * previous green + 13 * previous
   * blue). The new green value of a pixel is: (the value of 21 * previous red + 22 * previous green
   * + 23 * previous blue). The new blue value of a pixel is: (the value of 31 * previous red + 32 *
   * previous green + 33 * previous blue).
   *
   * @param filter The hashmap containing the values by which each value of each pixel is multiplied
   *               by.
   */
  public void linearApplyHelper(HashMap<Integer, Double> filter) {
    List<Pixel> workingList = new ArrayList<Pixel>();
    HashMap<Integer, ArrayList<Pixel>> workingMap = new HashMap<Integer, ArrayList<Pixel>>();
    for (int h = 0; h < this.height; h++) {
      workingMap.put(h, new ArrayList<Pixel>());
    }
    for (int p = 0; p < this.currLayer.getPixels().size(); p++) {
      Pixel currPix = this.currLayer.getPixels().get(p);
      ArrayList<Integer> pLoColors = currPix.getColors();
      float pRed = pLoColors.get(0);
      float pGreen = pLoColors.get(1);
      float pBlue = pLoColors.get(2);

      int newRed = (int) Math
          .round(pRed * filter.get(11) + pGreen * filter.get(21) + pBlue * filter.get(31));
      int newGreen = (int) Math
          .round(pRed * filter.get(12) + pGreen * filter.get(22) + pBlue * filter.get(32));
      int newBlue = (int) Math
          .round(pRed * filter.get(13) + pGreen * filter.get(23) + pBlue * filter.get(33));
      Color newColor = new Color(newRed, newGreen, newBlue);
      Pixel newPixel = new Pixel(newColor, currPix.getPosition());
      workingMap.get(p / width).add(newPixel);
    }
    this.currLayer.load(workingList);
    this.pixelToRow = workingMap;
  }

  public void load(List<Pixel> pixels) {
    if (!(pixels.size() == this.width * this.height)) {
      throw new IllegalArgumentException("Layer must have the same ammount of pixels as the"
          + "picture");
    }
    currLayer.load(pixels);
  }

  public void changeCurrentLater(int layerIndex) {
    if (layerIndex >= this.layers.size() || layerIndex < 0) {
      throw new IllegalArgumentException("No layer at this index");
    }
    this.currLayer = this.layers.get(layerIndex);
  }

  public void createLayer() {
    this.layers.add(new Layer());
  }
}
