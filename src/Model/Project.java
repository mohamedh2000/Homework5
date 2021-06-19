package Model;

import Model.LayerModel.Layer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Project {
    public ArrayList<Layer> layers;
    public String name;

    public Project() {
        this.layers = new ArrayList<>();
        this.name = "Untitled_Project";
    }

    //Exports top most layer
    public void save(String filetype) throws IOException {
        if(filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }
        this.layers.get(this.layers.size() - 1).exportLayer(filetype);
    }

    //Exports all layers and creates a txt file with their info
    public void saveAll(String filetype) throws IOException {
        if (filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }

        File txtFile = new File(this.name + ".txt");
        for(Layer lyr : this.layers) {
            String filePath = lyr.exportLayer(filetype);
            lyr.scrapeInfo(filePath, txtFile);
        }
    }

    //in the controller we will read the name, visibility, currentLayer, filePath and fileType and then pass it into a project
    public void loadFile(String name, boolean visible, boolean currentLayer, String filePath, String fileType)
            throws IOException {
        new ImageUtil();
        Layer newLayer;
        switch (fileType) {
            case("ppm"):
                newLayer = ImageUtil.readPPM(filePath);
                break;
            default:
                newLayer = ImageUtil.readJPEGPNG(filePath);
                break;
        }
        newLayer.changeName(name);
        newLayer.toggleVisibility(visible);
        newLayer.toggleCurrentLayer(currentLayer);
        this.layers.add(newLayer);
    }

}