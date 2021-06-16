import java.awt.image.Kernel;

public class Blur extends AbstractFilter {

  @Override
  public void add(Picture p) {
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
    p.kernelApplyHelper(blur);
  }
}
