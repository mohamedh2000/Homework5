package Model.LayerModel;

import Model.PictureModel.Picture;
import Model.PixelModel.Color;
import Model.PixelModel.Pixel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Represents a layer in a multi-layered picture.
 *
 */
public class Layer {
  private List<Pixel> pixels;
  public String name;
  private boolean currentLayer;
  private boolean visible;
  public int width;
  public int height;

  public Layer(int width, int height) {
    this.pixels = new ArrayList<Pixel>();
    this.name = "Untitled Layer";
    this.visible = true;
    this.width = width;
    this.height = height;
    this.currentLayer = true;
  }

  public Layer(List<Pixel> pixels, int width, int height) {
    this.name = "Untitled Layer";
    this.pixels = pixels;
    this.visible = true;
    this.width = width;
    this.height = height;
    this.currentLayer = true;
  }

  public void changeName(String newName) {
    if(newName == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = newName;
  }

  public void toggleVisibility(boolean newVisibility) {
    this.visible = newVisibility;
  }

  public void toggleCurrentLayer(boolean newCurrentLayer) {
    this.currentLayer = newCurrentLayer;
  }

  public void load(List<Pixel> newPixels) {
    if(newPixels == null) {
      throw new IllegalArgumentException("Cannot load a null list of pixels");
    }
    this.pixels = newPixels;
  }

  public List<Pixel> getPixels() {
    return this.pixels;
  }

  public void blend(ArrayList<Layer> imagesToBlend) throws IllegalArgumentException{
    if(imagesToBlend == null) {
      throw new IllegalArgumentException("cannot blend null list of images");
    }

    ArrayList<List<Pixel>> lop = new ArrayList<>();
    for(Layer lyr : imagesToBlend) {
      if(lyr.width != this.width || lyr.height != this.height) {
        System.out.println("LAYER WIDTH: " + lyr.width);
        System.out.println("THIS LAYER WIDTHL " + this.width);
        throw new IllegalArgumentException("All Images to blend need to have same dimensions!");
      }
      lop.add(lyr.getPixels());
    }
    

    ArrayList<Pixel> newPixelList = new ArrayList<>();
    for(int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Integer> currentPixelColor = this.pixels.get(i).getColors();
      ArrayList<ArrayList<Integer>> colorsToBlend = new ArrayList<>();
      for(int k = 0; k < lop.size(); k++) {
        colorsToBlend.add(lop.get(k).get(i).getColors());
      }
      //now average all of the colors up
      ArrayList<Integer> newAveragedColor = new ArrayList<>();
      int averageDenom = 1 + colorsToBlend.size();
      System.out.println("Im before averaging colors");
      for(int c = 0; c < 3; c ++) {
        int total = currentPixelColor.get(c);
        for(ArrayList<Integer> lyr : colorsToBlend) {
          total += lyr.get(c);
        }
        int averageColorValue = Math.round(total / averageDenom);
        newAveragedColor.add(averageColorValue);
      }
      newPixelList.add(new Pixel(new Color(newAveragedColor.get(0), newAveragedColor.get(1), newAveragedColor.get(2)),
              this.pixels.get(i).getPosition()));
      System.out.println("done with this pixel averaging!");         
    }
    load(newPixelList);
  }

  public void exportLayer(String fileType) throws IOException {
    switch (fileType) {
      case ("jpeg"):
         new Picture(this.pixels, this.width, this.height).pictureToJPEG(this.name);
         break;
      case ("png"):
          new Picture(this.pixels, this.width, this.height).pictureToPNG(this.name);
      default:
          new Picture(this.pixels, this.width, this.height).pictureToPPM(this.name);
    }
  }

}
