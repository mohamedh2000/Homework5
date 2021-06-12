import java.util.ArrayList;

public class Color {
  private int red;
  private int green;
  private int blue;

  Color(int red, int green, int blue) {
    if (red > 255) {
      this.red = 255;
    } else {
      if (red < 0) {
        this.red = 0;
      } else {
        this.red = red;
      }
    }
    if (green > 255) {
      this.green = 255;
    } else {
      if (green < 0) {
        this.green = 0;
      } else {
        this.green = red;
      }
    }
    if (blue > 255) {
      this.blue = 255;
    } else {
      if (blue < 0) {
        this.blue = 0;
      } else {
        this.blue = red;
      }
    }
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
