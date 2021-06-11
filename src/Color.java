import java.util.ArrayList;

public class Color {
  private int red;
  private int green;
  private int blue;

  Color(int red, int green, int blue) {
    if(red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Color values must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public ArrayList<Integer> getColors() {
    ArrayList<Integer> rgb = new ArrayList<>();
    rgb.add(this.red);
    rgb.add(this.green);
    rgb.add(this.blue);
    return rgb;
  }

  public String toString() {
    return red + " " + green + " " + blue;
  }


}
