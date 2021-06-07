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
    blurData[0] = (float) (1.0 / 16.0);
    blurData[1] = (float)(1.0 / 8.0);
    blurData[2] = (float)(1.0 / 16.0);
    blurData[3] = (float)(1.0 / 8.0);
    blurData[4] = (float)(1.0 / 4.0);
    blurData[5] = (float)(1.0 / 8.0);
    blurData[6] = (float)(1.0 / 16.0);
    blurData[7] = (float)(1.0 / 8.0);
    blurData[8] = (float)(1.0 / 16.0);
    Kernel blur = new Kernel(3, 3, blurData);
    kernalApplyHelper(blur, blur.getHeight());
  }

  private void kernalApplyHelper(Kernel toApply, int kernelHeight) {
    HashMap<Integer, ArrayList<Pixel>> mapUpdated = new HashMap<>(this.positions);

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
              ArrayList<Float> currentPixelColors = currentPixel.getColors();
              ArrayList<Float> newPixelColors = new ArrayList<>();
              for (int x = 0; x < currentPixelColors.size(); x++) {
                newPixelColors.add(currentPixelColors.get(x) * data[z]);
              }
              mapUpdated.get(j).get(k).setColors(newPixelColors);
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
}
