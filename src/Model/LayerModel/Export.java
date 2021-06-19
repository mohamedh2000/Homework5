package Model.LayerModel;
import Model.PixelModel.Pixel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This holds the export class. In the case that we need another type of class instead of just a layer, we can easily
 * extend these methods and both a layer and the new class can export the ppm jpeg or png formats.
 */
public abstract class Export {

    /**
     * Exports the layer data to a ppm file.
     * @param fileName The name of the layer which will be used as the file name.
     * @param width The width of the layer.
     * @param height The height of the layer.
     * @param pixelToRow The positions of each pixel.
     * @return This returns the path for which the picture was saved.
     * @throws IOException If the writer is broken midwrite for some reason.
     */
    public String pictureToPPM(String fileName, int width, int height,
                             HashMap<Integer, ArrayList<Pixel>> pixelToRow) throws IOException {
        FileWriter ppmObjectWriter = new FileWriter(fileName + ".ppm");

        String Header = "P3" + "\n" + width + " " + height + "\n" + "255";
        ppmObjectWriter.write(Header);
        for (Integer row : pixelToRow.keySet()) {
            String rowString = "\n";
            for (Pixel nextPixel : pixelToRow.get(row)) {
                ArrayList<Integer> pixelColor = nextPixel.getColors();
                for (Integer channel : pixelColor) {
                    rowString += channel + " ";
                }
            }
            ppmObjectWriter.write(rowString);
        }
        ppmObjectWriter.close();
        return new File(fileName + ".ppm").getAbsolutePath();
    }

    /**
     * Exports the layer data to a jpeg file.
     * @param fileName The name of the layer which will be used as the file name.
     * @param width The width of the layer.
     * @param height The height of the layer.
     * @param pixelToRow The positions of each pixel in the layer.
     * @return Returns the file path of the written down layer/pixel file.
     * @throws IOException If the file writer is interrupted/crashes for whatever reason.
     */
    public String pictureToJPEG(String fileName, int width, int height,
                              HashMap<Integer, ArrayList<Pixel>> pixelToRow) throws IOException {
        BufferedImage img = writeBufferedImage(width, height, pixelToRow);
        ImageIO.write(img, "jpeg", new File(fileName + ".jpeg"));
        return new File(fileName + ".jpeg").getAbsolutePath();
    }

    /**
     * An abstracted function that handles taking the colors of each pixel and returning the binary/hex rgb int.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param pixelToRow The positions of each pixel in the layer.
     * @return A Buffered Image whose data we will use to export an image.
     */
    public BufferedImage writeBufferedImage(int width, int height, HashMap<Integer, ArrayList<Pixel>> pixelToRow) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int row : pixelToRow.keySet()) {
            for(int currentPixel = 0; currentPixel < pixelToRow.get(row).size(); currentPixel++) {
                Pixel pixel = pixelToRow.get(row).get(currentPixel);
                ArrayList<Integer> colors = pixel.getColors();
                int r = colors.get(0);
                int g = colors.get(1);
                int b = colors.get(2);
                img.setRGB(currentPixel, row, ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff));
            }
        }
        return img;
    }

    /**
     * Exports the layer into a png.
     * @param fileName The layer name which will serve as the file name.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param pixelToRow The pixel to row position mapping,which maps a row to a set of pixels.
     * @return A path for where the file was saved to.
     * @throws IOException If the write for some reason fails.
     */
    public String pictureToPNG(String fileName, int width, int height,
                             HashMap<Integer, ArrayList<Pixel>> pixelToRow) throws IOException {
        BufferedImage img = writeBufferedImage(width, height, pixelToRow);
        ImageIO.write(img, "png", new File(fileName + ".png"));
        return new File(fileName + ".png").getAbsolutePath();
    }

}
