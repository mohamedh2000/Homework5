import java.awt.image.Kernel;
import java.util.ArrayList;

public class Pixel {
  private Color color;
  private final Position position;

  public Pixel(Color color, Position position) {
    this.color = color;
    this.position = position;
  }

  public ArrayList<Integer> getColors() {
    return this.color.getColors();
  }

  public Position getPosition() { return this.position;}

  public void setColors(ArrayList<Integer> newColors) {
    System.out.println(newColors);
    this.color = new Color(newColors.get(0), newColors.get(1), newColors.get(2));
  }

  public String pixelToColorsString() {
    return this.color.toString();
  }
}
