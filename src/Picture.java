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
    kernalApplyHelper(blur, blur.getWidth(), blur.getHeight());
  }

  private void kernalApplyHelper(Kernel toApply, int kernelWidth, int kernelHeight) {
    HashMap<Integer, ArrayList<Pixel>> mapUpdated = new HashMap<>(this.positions);

    for (int row : this.positions.keySet()) {
      for (Pixel pixel : this.positions.get(row)) {
        Position pixelPosition = pixel.getPosition();
        float[] data = toApply.getKernelData(null);
        int column = pixelPosition.getColumn();

        int z = 0;

        int temp = (kernelHeight - 1) / 2;
        //iterating over every row that counts
        for (int j = temp; j <= kernelHeight; j++) {
          if(temp < 0 || temp > width || temp > height) {
            continue;
          }
          else {
            ArrayList<Pixel> currentRow = this.positions.get(j);
            for (int k = column - temp; k <= column + temp; k++) {
              Pixel currentPixel = currentRow.get(k);
              ArrayList<Float> currentPixelColors = currentPixel.getColors();
              for (int x = 0; x < currentPixelColors.size(); x++) {
                currentPixelColors.set(x, currentPixelColors.get(x) * data[z]);
              }
              mapUpdated.get(j).get(k).setColors(currentPixelColors);
              z++;
            }
          }

        }
      }
    }

  }

}
