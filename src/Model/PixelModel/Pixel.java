package Model.PixelModel;

import java.util.ArrayList;

/**
 * Represents a singular pixel which consists of a color and a position in a layer.
 */
public class Pixel {

  private Color color;
  private final Position position;

  /**
   * Constructs a new pixel with a color object and position object.
   * @param color This will determine the color of the pixel.
   * @param position This will determine the position of the current pixel.
   */
  public Pixel(Color color, Position position) {
    this.color = color;
    this.position = position;
  }

  /**
   * Gets the color of the pixel.
   * @return An arraylist consisting of red, green, blur in that order.
   */
  public ArrayList<Integer> getColors() {
    return this.color.getColors();
  }

  /**
   * Gets the position which is a class that has an x and y, where x is the row and y is column.
   * @return Returns the pixels position.
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * This will set the color of the current Pixel.
   * @param newColors The arraylist of colors we want to set for the pixel.
   */
  public void setColors(ArrayList<Integer> newColors) {
    System.out.println(newColors);
    this.color = new Color(newColors.get(0), newColors.get(1), newColors.get(2));
  }

  /**
   * This will turn the pixel colors to a string.
   * @return The string representation of the current Color.
   */
  public String pixelToColorsString() {
    return this.color.toString();
  }
}
