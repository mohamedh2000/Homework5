import java.util.ArrayList;

public class Color {
  private float red;
  private float green;
  private float blue;

  Color(float red, float green, float blue) {
    if(red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Color values must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public ArrayList<Float> getColors() {
    ArrayList<Float> rgb = new ArrayList<>();
    rgb.add(this.red);
    rgb.add(this.green);
    rgb.add(this.blue);
    return rgb;
  }


}
