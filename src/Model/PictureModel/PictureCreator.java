package Model.PictureModel;

import Model.LayerModel.Layer;
import Model.PixelModel.Pixel;
import Model.PixelModel.Color;
import Model.PixelModel.Position;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class containing methods to create pictures.
 */
public class PictureCreator {

    /**
     * Creates a picture given a width, height, and list of Colors.
     *
     * @param width   The width of the picture in pixels
     * @param height  The height of the picture in pixels
     * @param loColor An ArrayList of Colors with one color for each pixel.
     * @return A picture.
     */
    public Layer makePicture(int width, int height, ArrayList<Color> loColor) {
        ArrayList<Pixel> loPixel = new ArrayList<Pixel>();
        HashMap<Integer, ArrayList<Pixel>> pixelToRow = new HashMap<>();
        int k = 0;
        for (int i = 0; i < loColor.size(); i++) {
            Pixel newPixel = new Pixel(loColor.get(i), new Position((i % width), (i / width)));
            loPixel.add(newPixel);
            if (loPixel.size() % width == 0 && loPixel.size() != 0) {
                pixelToRow.put(k, loPixel);
                k++;
                loPixel = new ArrayList<Pixel>();
            }
        }
        ArrayList<Pixel> picturePixels = new ArrayList<>();
        for (Integer i : pixelToRow.keySet()) {
            picturePixels.addAll(pixelToRow.get(i));
        }
        return new Layer(picturePixels, pixelToRow, width, height);
    }

    /**
     * Returns a new picture with a checkerboard pattern, alternating colors each pixel.
     * The tileSize should handle the number of tiles as well.
     *
     * @param color1   The first color in the pattern.
     * @param color2   The second color in the pattern.
     * @param width    The width of the picture in pixels.
     * @param height   The height of the picture in pixels.
     * @param tileSize The size of a tile. The tile Size will be applied to height and width of a tile.
     * @return The checkerboard picture.
     */

    public Layer checkerBoard(Color color1, Color color2, int width, int height, int tileSize) {
        if ((width % tileSize) != 0 || (height % tileSize) != 0) {
            throw new IllegalArgumentException("tileSize is invalid");
        }

        ArrayList<Color> loColor = new ArrayList<Color>();
        HashMap<Integer, ArrayList<Color>> map = new HashMap<>();
        Boolean applyColor1 = true;
        for (int h = 0; h < height; h++) {
            map.put(h, new ArrayList<>());
            if (h % tileSize == 0) {
                applyColor1 = !applyColor1;
            }
            for (int j = 0; j < width; j++) {
                if (j % tileSize == 0 && j != 0) {
                    applyColor1 = !applyColor1;
                }
                if (applyColor1) {
                    map.get(h).add(color1);
                } else {
                    map.get(h).add(color2);
                }
            }
        }
        for (int column : map.keySet()) {
            for (Color currentColor : map.get(column)) {
                loColor.add(currentColor);
            }
        }

        return makePicture(width, height, loColor);
    }
}
