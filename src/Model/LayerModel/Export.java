package Model.LayerModel;

import Model.PixelModel.Pixel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Export {

    /**
     * Creates a PPM file corresponding to the picture.
     *
     * @param fileName The name of the ppm file.
     * @throws IOException
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
     * Creates a JPEG file corresponding to the picture.
     *
     * @param fileName The name of the ppm file.
     * @throws IOException
     */
    public String pictureToJPEG(String fileName, int width, int height,
                              HashMap<Integer, ArrayList<Pixel>> pixelToRow) throws IOException {
        BufferedImage img = writeBufferedImage(width, height, pixelToRow);
        ImageIO.write(img, "jpeg", new File(fileName + ".jpeg"));
        return new File(fileName + ".jpeg").getAbsolutePath();
    }

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
     * Creates a PNG file corresponding to the picture.
     *
     * @param fileName The name of the ppm file.
     * @throws IOException
     */
    public String pictureToPNG(String fileName, int width, int height,
                             HashMap<Integer, ArrayList<Pixel>> pixelToRow) throws IOException {
        BufferedImage img = writeBufferedImage(width, height, pixelToRow);
        ImageIO.write(img, "png", new File(fileName + ".png"));
        return new File(fileName + ".png").getAbsolutePath();
    }

}
