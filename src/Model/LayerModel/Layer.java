package Model.LayerModel;

import Model.PixelModel.Color;
import Model.PixelModel.Pixel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a layer in a multi-layered picture.
 * This will be used in the scheme of things to create a multi-layered Project.
 */
public class Layer extends Export {
    private ArrayList<Pixel> pixels;
    private HashMap<Integer, ArrayList<Pixel>> pixelPositions;
    public String name;
    private boolean currentLayer;
    private boolean visible;
    private int width;
    private int height;

    public Layer(int width, int height) {
        this.pixels = new ArrayList<Pixel>();
        this.pixelPositions = new HashMap<Integer, ArrayList<Pixel>>();
        this.name = "Untitled Layer";
        this.visible = true;
        this.width = width;
        this.height = height;
        this.currentLayer = true;
    }

    public Layer(ArrayList<Pixel> pixels, int width, int height) {
        this.name = "Untitled Layer";
        this.pixels = pixels;
        this.pixelPositions = retPixelPositions(this.pixels, this.width, this.height);
        this.visible = true;
        this.width = width;
        this.height = height;
        this.currentLayer = true;
    }

    public Layer(ArrayList<Pixel> pixels, HashMap<Integer, ArrayList<Pixel>> map, int width, int height) {
        this.pixels = pixels;
        this.pixelPositions = map;
        this.width = width;
        this.height = height;
        this.currentLayer = true;
        this.name = "Untitled Layer";
        this.visible = true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public HashMap<Integer, ArrayList<Pixel>> getPixelPositions() {
        return this.pixelPositions;
    }

    public ArrayList<Pixel> getPixelList() {
        return this.pixels;
    }

    public void setPixels(ArrayList<Pixel> newPixelList) {
        this.pixels = newPixelList;
    }

    public void setPixelPositions(HashMap<Integer, ArrayList<Pixel>> newPixelPostions) {
        this.pixelPositions = newPixelPostions;
    }

    private HashMap<Integer, ArrayList<Pixel>> retPixelPositions(ArrayList<Pixel> pixels, int width, int height) {
        HashMap<Integer, ArrayList<Pixel>> map = new HashMap<Integer, ArrayList<Pixel>>();

        int currentPlacement = 0;
        for (int row = 0; row < height; row++) {
            map.put(row, new ArrayList<Pixel>());
            int threshold = currentPlacement + width;
            for (int i = currentPlacement; i < threshold; i++) {
                Pixel pixel = pixels.get(i);
                map.get(row).add(pixel);
                currentPlacement++;
            }
        }
        return map;
    }

    public void changeName(String newName) {
        if (newName == null) {
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

    public void load(ArrayList<Pixel> newPixels) {
        if (newPixels == null) {
            throw new IllegalArgumentException("Cannot load a null list of pixels");
        }
        this.pixels = newPixels;
    }

    public List<Pixel> getPixels() {
        return this.pixels;
    }

    public void blend(ArrayList<Layer> imagesToBlend) throws IllegalArgumentException {
        if (imagesToBlend == null) {
            throw new IllegalArgumentException("cannot blend null list of images");
        }

        ArrayList<List<Pixel>> lop = new ArrayList<>();
        for (Layer lyr : imagesToBlend) {
            if (lyr.width != this.width || lyr.height != this.height) {
                throw new IllegalArgumentException("All Images to blend need to have same dimensions!");
            }
            lop.add(lyr.getPixels());
        }

        ArrayList<Pixel> newPixelList = new ArrayList<>();
        for (int i = 0; i < this.pixels.size(); i++) {
            ArrayList<Integer> currentPixelColor = this.pixels.get(i).getColors();
            ArrayList<ArrayList<Integer>> colorsToBlend = new ArrayList<>();
            for (int k = 0; k < lop.size(); k++) {
                colorsToBlend.add(lop.get(k).get(i).getColors());
            }
            //now average all of the colors up
            ArrayList<Integer> newAveragedColor = new ArrayList<>();
            int averageDenom = 1 + colorsToBlend.size();
            for (int c = 0; c < 3; c++) {
                int total = currentPixelColor.get(c);
                for (ArrayList<Integer> lyr : colorsToBlend) {
                    total += lyr.get(c);
                }
                int averageColorValue = Math.round(total / averageDenom);
                newAveragedColor.add(averageColorValue);
            }
            newPixelList.add(new Pixel(new Color(newAveragedColor.get(0), newAveragedColor.get(1), newAveragedColor.get(2)),
                    this.pixels.get(i).getPosition()));
        }
        load(newPixelList);
    }

    public String exportLayer(String fileType) throws IOException {
        switch (fileType) {
            case ("jpeg"):
                return pictureToJPEG(this.name, this.width, this.height, this.pixelPositions);
            case ("png"):
                return pictureToPNG(this.name, this.width, this.height, this.pixelPositions);
            default:
                return pictureToPPM(this.name, this.width, this.height, this.pixelPositions);
        }
    }

    public void scrapeInfo(String filePath, File txtFile) {
        try {
            FileWriter writer = new FileWriter(txtFile);
            String scrapedInfo = this.name + " " + this.visible + " " + this.currentLayer + " " + filePath;
            writer.write(scrapedInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //#TODO:PUT SHIT TON TRY CATCHES AND GO BACK AND GET EDGE CASES
}
