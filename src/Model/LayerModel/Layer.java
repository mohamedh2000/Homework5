package Model.LayerModel;
import Model.PixelModel.Color;
import Model.PixelModel.Pixel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a layer in a multi-layered picture.
 * This will be used by the Project class in order to create a multilayered Project that can exported/imported.
 */
public class Layer extends Export {
    private ArrayList<Pixel> pixels;
    private HashMap<Integer, ArrayList<Pixel>> pixelPositions;
    public String name;
    private boolean currentLayer;
    private boolean visible;
    private final int width;
    private final int height;

    /**
     * This will allow for a new layer class with a set amount of width and height. Default values are set for other
     * non specified parameters.
     * @param width The width of the new Layer.
     * @param height The height of the new Layer.
     * @throws IllegalArgumentException If the height or width are negative.
     */
    public Layer(int width, int height) throws IllegalArgumentException {
        if(width < 0 || height < 0) {
            throw new IllegalArgumentException("This is not a valid width/height");
        }
        this.pixels = new ArrayList<Pixel>();
        this.pixelPositions = new HashMap<Integer, ArrayList<Pixel>>();
        this.name = "Untitled Layer";
        this.visible = true;
        this.width = width;
        this.height = height;
        this.currentLayer = true;
    }

    /**
     * This will allow for a new Layer class with a user specified width, height, and the pixels, so essentially it
     * has a picture already loaded(unless the pixels class is empty which is fine).
     * @param pixels An array list of pixels that define every pixel in the arraylist.
     * @param width The width of the inputted Layer.
     * @param height The height of the inputted layer.
     * @throws IllegalArgumentException If the pixels are null or if the parameters don't make sense,
     *              such as the pixel size being greater than possible with the width and height provided.
     */
    public Layer(ArrayList<Pixel> pixels, int width, int height) throws IllegalArgumentException {
        if(pixels == null) {
            throw new IllegalArgumentException("List of Pixels cannot be null");
        }
        if(width < 0 || height < 0) {
            throw new IllegalArgumentException("This is not a valid width/height");
        }
        if(pixels.size() > width * height) {
            throw new IllegalArgumentException("The pixels inputted does not match the width and height specified.");
        }
        this.name = "Untitled Layer";
        this.pixels = pixels;
        this.visible = true;
        this.width = width;
        this.height = height;
        this.pixelPositions = retPixelPositions(this.pixels, this.width, this.height);
        this.currentLayer = true;
    }

    /**
     * This will take in a user-inputted list of pixels, the map of the layer, and the height and width of the layer.
     * @param pixels The pixels in the layer. Each pixel will have a color attribute.
     * @param map The pixels split up into rows.
     * @param width The width of the layer.
     * @param height The height of the layer.
     * @throws IllegalArgumentException If map or pixels is null, if the width and height are negative,
     *                                     or if the map size does not match with the pixel size
     */
    public Layer(ArrayList<Pixel> pixels, HashMap<Integer, ArrayList<Pixel>> map, int width, int height)
            throws IllegalArgumentException{
        if(pixels == null || map == null) {
            throw new IllegalArgumentException("List of Pixels and/or Map cannot be null");
        }
        if(width < 0 || height < 0) {
            throw new IllegalArgumentException("This is not a valid width/height");
        }
        if(pixels.size() > width * height) {
            throw new IllegalArgumentException("The pixels inputted does not match the width and height specified.");
        }
        if(map.size() * width != pixels.size()) {
            throw new IllegalArgumentException("The map inputted is not compatible with the rest of the parameters.");
        }
        this.pixels = pixels;
        this.pixelPositions = map;
        this.width = width;
        this.height = height;
        this.currentLayer = true;
        this.name = "Untitled Layer";
        this.visible = true;
    }

    /**
     * Returns the width of the layer.
     * @return An integer specifying the width of the image.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the Layer.
     * @return An integer specifying the width of the image.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the pixelPositions in the Layer.
     * @return A hashmap mapping each row to an array of pixels.
     *          This will make it easier to traverse the pixel list for filterings and such.
     */
    public HashMap<Integer, ArrayList<Pixel>> getPixelPositions() {
        return this.pixelPositions;
    }

    /**
     * Returns the list of pixels in the layer.
     * @return An array of pixels of the layer.
     */
    public ArrayList<Pixel> getPixelList() {
        return this.pixels;
    }

    /**
     * You can set the arrayList of pixels after a transformation only if applicable.
     * @param newPixelList The new pixels in the layer.
     * @throws IllegalArgumentException If the newPixelList is invalid.
     */
    public void setPixels(ArrayList<Pixel> newPixelList) {
        if(newPixelList == null || newPixelList.size() > this.width * this.height) {
            throw new IllegalArgumentException("This arraylist is not applicable.");
        }
        this.pixels = newPixelList;
    }

    /**
     * Sets the new pixel position after a new transformation.
     * @param newPixelPostions The new pixels in the hashmap.
     */
    public void setPixelPositions(HashMap<Integer, ArrayList<Pixel>> newPixelPostions) {
        if(newPixelPostions == null || newPixelPostions.size() * this.width > this.width * this.height) {
            throw new IllegalArgumentException("This arraylist is not applicable.");
        }
        this.pixelPositions = newPixelPostions;
    }

    /**
     * This will get the positions of the pixels based on the width and height of the pictures.
     * @param pixels The new pixel arraylist that will label each pixel by position/row.
     * @param width The width of the image.
     * @param height The height of the image.
     * @return This will return the pixel positions of the pixel list.
     */
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

    /**
     * To change the name of the layer.
     * @param newName The new name of the layer.
     * @throws IllegalArgumentException If the new name is null.
     */
    public void changeName(String newName) {
        if (newName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = newName;
    }

    /**
     * sets the visibility of the layer.
     * @param newVisibility The new visibility of the layer.
     */
    public void toggleVisibility() {
        this.visible = !this.visible;
    }

    /**
     * If this layer is the new currently layer.
     * @param newCurrentLayer If this is the current layer of the project.
     */
    public void toggleCurrentLayer() {
        this.currentLayer = !this.currentLayer;
    }

    /**
     * To set the new pixels for the layer.
     * @param newPixels the new pixel list to set for the layer.
     */
    public void load(ArrayList<Pixel> newPixels) {
        if (newPixels == null) {
            throw new IllegalArgumentException("Cannot load a null list of pixels");
        }
        this.pixels = newPixels;
    }

    /**
     * Returns the pixels list in the layer.
     * @return returns the pixels list for the layer.
     */
    public List<Pixel> getPixels() {
        return this.pixels;
    }

    /**
     * It averages the two image colors together pixel by pixel.
     * @param imagesToBlend The pixels in the layer to blend.
     * @throws IllegalArgumentException If the inputted pixels in the layer are null.
     */
    public void blend(ArrayList<Layer> imagesToBlend) throws IllegalArgumentException {
        if (imagesToBlend == null) {
            throw new IllegalArgumentException("cannot blend null list of pixels.");
        }
        if(imagesToBlend.size() != this.pixels.size()) {
            throw new IllegalArgumentException("Cannot blend incompatible images.");
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

    public Boolean isCurrent() {
        return this.isCurrent();
    }

    /**
     * Exports the layer into the specified file type.
     * @param fileType Can be only jpeg, png or ppm
     * @return Returns a String with the path of the file.
     * @throws IOException If writing the picture fails for whatever reason
     * @throws IllegalArgumentException If the file Type is not supported.
     */
    public String exportLayer(String fileType) throws IOException, IllegalArgumentException{
        if(!fileType.equals("jpeg") && !fileType.equals("png") && !fileType.equals("ppm")) {
            throw new IllegalArgumentException("File type is not supported.");
        }
        switch (fileType) {
            case ("jpeg"):
                return pictureToJPEG(this.name, this.width, this.height, this.pixelPositions);
            case ("png"):
                return pictureToPNG(this.name, this.width, this.height, this.pixelPositions);
            default:
                return pictureToPPM(this.name, this.width, this.height, this.pixelPositions);
        }
    }

    /**
     * Scrapes the info from the Layer that we will need in order to reload the project later on.
     * @param filePath The file path of the Layer so that we can retrieve it when we/if we are reloading the project.
     * @returns A String with all of the scraped info needed from the layer.
     */
    public String scrapeInfo(String filePath) {
        return this.name + " " + this.visible + " " + this.currentLayer + " " + filePath + "\n";
    }

}
