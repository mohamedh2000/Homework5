package model;

import model.layermodel.Layer;
import model.pixelmodel.Color;
import model.pixelmodel.Pixel;
import model.pixelmodel.Position;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * This will getThe PixelList from the scanner as it reads each color for each pixel.
   * @param sc The Scanner.
   * @param pixelList The pixelList.
   * @param map The hashmap.
   * @param width The width.
   * @param height The height.
   */
  public static void setPixelistMap(Scanner sc, ArrayList<Pixel> pixelList, HashMap<Integer,
      ArrayList<Pixel>> map, int width, int height) {
    for (int i = 0; i < height; i++) {
      //i is row
      //h is column
      map.put(i, new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel newPixel = new Pixel(new Color(r, g, b), new Position(i, j));
        pixelList.add(newPixel);
        map.get(i).add(newPixel);
      }
    }
  }

  /**
   * Gets the file type from the filePath/name.
   * @param fileName The filePath/name.
   * @return A string of the file type.
   */
  public static String getFileType(String fileName) {
    String fileType = fileName.substring(fileName.length() - 3);
    switch (fileType) {
      case "png":
        return "png";
      case "ppm":
        return "ppm";
      default:
        String tryII = fileName.substring(fileName.length() - 4);
        if (tryII.equals("jpeg")) {
          return "jpeg";
        } else {
          throw new IllegalArgumentException("File type is unaccepted.");
        }
    }
  }

  /**
   * Reads the file based on the file type.
   * @param fileName The filepath in question.
   * @return A Layer of info extrapalated from the file.
   * @throws IOException If there is an issue reading the file.
   */
  public static Layer readFile(String fileName) throws IOException {
    String fileType = getFileType(fileName);
    switch (fileType) {
      case "png":
        return readJPEGPNG(fileName);
      case "jpeg":
        return readJPEGPNG(fileName);
      case "ppm":
        return readPPM(fileName);
      default:
        return null;
    }
  }

  /**
   * Reads a ppm file.
   * @param filename The filepath/name of the file.
   * @return A Layer with the info of the ppm extracted.
   */
  public static Layer readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    ArrayList<Pixel> pixels = new ArrayList<>();
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();

    setPixelistMap(sc, pixels, map, width, height);
    return new Layer(pixels, map, width, height);
  }

  /**
   * Reads A JPEG/PNG file.
   * @param filename The filename/path in question.
   * @return A Layer with the jpeg/png info extracted/
   * @throws IOException If there is an issue reading/writing.
   */
  public static Layer readJPEGPNG(String filename) throws IOException {
    File f = new File(filename);
    BufferedImage image = ImageIO.read(f);

    int width = image.getWidth();
    int height = image.getHeight();
    ArrayList<Pixel> pixels = new ArrayList<>();
    HashMap<Integer, ArrayList<Pixel>> map = new HashMap<>();
    //TODO: Get rid of Picture class and add map functionality to jpeg layer

    for (int h = 0; h < height; h++) {
      map.put(h, new ArrayList<Pixel>());
      for (int w = 0; w < width; w++) {
        java.awt.Color rgb = new java.awt.Color(image.getRGB(w, h));
        int red = rgb.getRed();
        int blue = rgb.getBlue();
        int green = rgb.getGreen();
        Pixel newPixel = new Pixel(new Color(red, green, blue), new Position(h, w));
        pixels.add(newPixel);
        map.get(h).add(newPixel);
      }
    }
    return new Layer(pixels, width, height);
  }

  /**
   * Democode given to us.
   * @param args Random arguments.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

