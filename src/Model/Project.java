package Model;

import Model.LayerModel.Layer;

import java.io.IOException;
import java.util.ArrayList;

public class Project {
    public ArrayList<Layer> layers;

    public Project() {
        this.layers = new ArrayList<>();
    }

    /*
    NOTE:
    You need to be able to both export the top layer as a .ppm/.png/.jpg, and export the entire image as a
    collection of single images along with one .txt file.
    Hunter: Think of it like a save vs saveAll, where the save would provide the behavior you described while
    saveAll exports all of the layers, regardless of transparency or not. 

     */

    public void exportTopLayer(String filetype) throws IOException {
        if(filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }
        this.layers.get(this.layers.size() - 1).exportLayer(filetype);
    }

    public void exportAllLayers(String filetype) throws IOException {
        if (filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }
        for(Layer lyr : this.layers) {
            lyr.exportLayer(filetype);
        }
    }


}
