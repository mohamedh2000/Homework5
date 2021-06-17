import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Represents a layer in a multi-layered picture.
 *
 */
public class Layer {
  private List<Pixel> pixels;
  private boolean visible;

  public Layer() {
    this.pixels = new ArrayList<Pixel>();
    this.visible = true;
  }

  public Layer(List<Pixel> pixels) {
    this.pixels = pixels;
    this.visible = true;
  }

  public void changeVisibility(boolean newVisibility) {
    this.visible = newVisibility;
  }

  public void load(List<Pixel> newPixels) {
    this.pixels = newPixels;
  }

  public List<Pixel> getPixels() {
    ArrayList<Pixel> returnPixels = new ArrayList<Pixel>();
    for (Pixel p : this.pixels) {
      returnPixels.add(p);
    }
    return getPixels();
  }

}
