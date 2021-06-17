package Model.PictureModel;

import Model.PixelModel.Pixel;
import Model.PixelModel.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.io.File;
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

  private List<Pixel> pixels;
  private HashMap<Integer, ArrayList<Pixel>> pixelToRow;
  public final int width;
  public final int height;

  /**
   * Creates a picture
   *
   * @param pixels The list of all pixels
   * @param map    A hashmap with the key being the row, and the value being an arraylist of all
   *               pixels in the row.
   * @param width  The width of the picture in pixels.
   * @param height The height of the picture in pixels.
   */
  public Picture(List<Pixel> pixels, HashMap<Integer, ArrayList<Pixel>> map, int width, int height) {
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
    this.pixels = pixels;
    this.pixelToRow = map;
    this.width = width;
    this.height = height;
  }

  public Picture(List<Pixel> pixels, int width, int height) {
    this.width = width;
    this.height = height;

    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();
    map.put(0, new ArrayList<Pixel>());
    int rowNum = 0;
    System.out.println(pixels.size());
    for(int i = 0; i < pixels.size(); i++) {
      if(i % width == 0 && i != 0) {
        rowNum++;
        map.put(rowNum, new ArrayList<Pixel>());
      }
      map.get(rowNum).add(pixels.get(i));
    }

    this.pixelToRow = map;
  }

  /**
   * The channel of the color. Red, Green, or Blue.
   */
  private enum Channel {
    RED, GREEN, BLUE
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
   * Creates a JPEG file corresponding to the picture.
   *
   * @param fileName The name of the ppm file.
   * @throws IOException
   */
  public void pictureToJPEG(String fileName) throws IOException {
    BufferedImage img = writeBufferedImage();
    ImageIO.write(img, "jpeg", new File(fileName + ".jpeg"));
  }

  public BufferedImage writeBufferedImage() {
    BufferedImage img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for(int row : this.pixelToRow.keySet()) {
      for(int currentPixel = 0; currentPixel < this.pixelToRow.get(row).size(); currentPixel++) {
        Pixel pixel = this.pixelToRow.get(row).get(currentPixel);
        ArrayList<Integer> colors = pixel.getColors();
        int r = colors.get(0);
        int g = colors.get(1);
        int b = colors.get(2);
        img.setRGB(currentPixel, row, ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff));
      }
    }
    return img;
  }

  /**
   * Creates a PNG file corresponding to the picture.
   *
   * @param fileName The name of the ppm file.
   * @throws IOException
   */
  public void pictureToPNG(String fileName) throws IOException {
    BufferedImage img = writeBufferedImage();
    ImageIO.write(img, "png", new File(fileName + ".png"));
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
    List<Picture.Channel> Channels = Arrays.asList(Picture.Channel.values());

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
    for (int p = 0; p < this.pixels.size(); p++) {
      Pixel currPix = this.pixels.get(p);
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
    this.pixels = workingList;
    this.pixelToRow = workingMap;
  }

}
