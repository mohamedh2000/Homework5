package model.filtermodel;

import model.layermodel.Layer;
import model.pixelmodel.Color;
import model.pixelmodel.Pixel;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This abstracts the necessary and possible methods that can be abstracted for all of the
 * subclasses to use.
 */
public abstract class FilterMethods implements Filter {

  /**
   * Applies filtering to each pixel. If the pixel/row doesn't exist it will just skip onto the
   * next.
   *
   * @param toApply The kernel that we want to retrieve the values from.
   */
  public void kernelApplyHelper(Kernel toApply, Layer layer) {
    HashMap<Integer, ArrayList<Pixel>> mapUpdated = layer.getPixelPositions();
    int kernelHeight = toApply.getHeight();

    for (int row : layer.getPixelPositions().keySet()) {
      for (Pixel pixel : layer.getPixelPositions().get(row)) {
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
    layer.setPixelPositions(mapUpdated);
    ArrayList<Pixel> newPixelList = new ArrayList<Pixel>();
    for (int row : mapUpdated.keySet()) {
      for (Pixel currentPixel : mapUpdated.get(row)) {
        newPixelList.add(currentPixel);
      }
    }
    layer.setPixels(newPixelList);
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
  public void linearApplyHelper(HashMap<Integer, Double> filter, Layer layer) {
    ArrayList<Pixel> workingList = new ArrayList<Pixel>();
    HashMap<Integer, ArrayList<Pixel>> workingMap = new HashMap<Integer, ArrayList<Pixel>>();

    for (int h = 0; h < layer.getHeight(); h++) {
      workingMap.put(h, new ArrayList<Pixel>());
    }
    for (int p = 0; p < layer.getPixels().size(); p++) {
      Pixel currPix = layer.getPixels().get(p);
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
      workingMap.get(p / layer.getWidth()).add(newPixel);
    }
    layer.setPixels(workingList);
    layer.setPixelPositions(workingMap);
  }

}
