package Model.PixelModel;

import java.util.ArrayList;

/**
 * Represents the color of a picture.
 */
public class Color {

    private int red;
    private int green;
    private int blue;

    /**
     * Creates a color. Each value will be between 0 and 255. If a value is inputted greater than 255,
     * the value will be clamped to 255. If a value is less than 0, the value will be clamped to 0.
     *
     * @param red   an integer representing the red channel.
     * @param green an integer representing the green channel.
     * @param blue  an integer representing the blue channel.
     */
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        if (red > 255) {
            this.red = 255;
        }
        if (red < 0) {
            this.red = 0;
        }
        if (green > 255) {
            this.green = 255;
        }
        if (green < 0) {
            this.green = 0;
        }
        if (blue > 255) {
            this.blue = 255;
        }
        if (blue < 0) {
            this.blue = 0;
        }

    }


    /**
     * Returns an list of integers containing the three color channels.
     *
     * @return A list of the color values for each channel.
     */
    public ArrayList<Integer> getColors() {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(this.red);
        rgb.add(this.green);
        rgb.add(this.blue);
        return rgb;
    }

    /**
     * Returns a string representation of the color with the three channels separated by spaces.
     *
     * @return a string representation of the color.
     */
    public String toString() {
        return red + " " + green + " " + blue;
    }


}
