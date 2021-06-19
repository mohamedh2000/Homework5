package Model.LayerModel;

import Model.ImageUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class LayerTest {

    @Test
    public void toggleVisibility() {
    }

    @Test
    public void load() {
    }

    @Test
    public void getPixels() {
    }

    @Test
    public void testJpegPngRead() throws IOException { //export top most layer
        Layer aenamiLayer = new ImageUtil().readJPEGPNG("aenami.jpeg");
        aenamiLayer.exportLayer("png");
    }

    @Test
    public void blend() throws IOException {
        Layer rainbowLayer = new ImageUtil().readPPM("rainbow.ppm");
        Layer koalaScaledLayer = new ImageUtil().readPPM("koalaScaled.ppm");
        ArrayList<Layer> blendLayers = new ArrayList<>();
        blendLayers.add(rainbowLayer);
        koalaScaledLayer.blend(blendLayers);
        koalaScaledLayer.exportLayer("ppm");
    }
}