package Model;

import Model.FilterModel.Blur;
import Model.FilterModel.Greyscale;
import Model.FilterModel.Sepia;
import Model.FilterModel.Sharpen;
import Model.LayerModel.Layer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a new Project that will hold all of the layers. This will be used as a shell to keep
 * all of the information of each layer together, making it easier to retrieve and save the current
 * state of the layers.
 */
public class Project {

    public ArrayList<Layer> layers; //TODO: NOT SURE IF WE ARE ALLOWED TO HAVE ANY PUBLIC VARIABLES
    public String name;

    /**
     * If you pass in nothing it will initalize a project with no layers and a generic name.
     */
    public Project() {
        this.layers = new ArrayList<>();
        this.name = "Untitled_Project";
    }

    /**
     * If you pass in a project name, it will initialize a new project with the given name.
     *
     * @param projectName A string representing the name of the project.
     */
    public Project(String projectName) {
        this.name = projectName;
        this.layers = new ArrayList<>();
    }

    /**
     * If you pass in an array of layers, then the project will consistent of those layers.
     *
     * @param layers The array list of layers in the project.
     * @throws IllegalArgumentException If layers is null.
     */
    public Project(ArrayList<Layer> layers) {
        if (layers == null) {
            throw new IllegalArgumentException("Layers cannot be null");
        }
        this.layers = layers;
        this.name = "Untitled_Project";
    }

    /**
     * This method is to be used in order to change the name of the Project.
     *
     * @param newProjectName A string representing the name of the new given project.
     */
    public void changeName(String newProjectName) {
        this.name = newProjectName;
    }

    /**
     * This will save the topmost layer in the project. It needs to know the file type for the
     * export.
     *
     * @param filetype A string representing the type of file that we want to export: ppm, jpeg or
     *                 png.
     * @throws IOException If the write process for some reason fails.
     */
    public void save(String filetype) throws IOException {
        if (filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }
        this.layers.get(this.layers.size() - 1).exportLayer(filetype);
    }

    /**
     * Exports all of the layers and creates a text file with: the project name, the name of the
     * layer, the visibility, if its the current layer, and the file path it was written to during the
     * export.
     *
     * @param filetype A string that can be either: "jpeg", "png", or ".ppm"
     * @throws IOException If the write fails for whatever reason.
     */
    public void saveAll(String filetype) throws IOException {
        if (filetype == null) {
            throw new IllegalArgumentException("filetype cannot be null.");
        }

        File txtFile = new File(this.name + ".txt");
        FileWriter writer = new FileWriter(txtFile);
        writer.write(this.name + "\n");
        for (Layer lyr : this.layers) {
            String filePath = lyr.exportLayer(filetype);
            writer.write(lyr.scrapeInfo(filePath));
        }
        writer.close();
    }

    /**
     * This takes in a name, whether its visible or not, whether its the current layer or not, the
     * file path and file type. It will load the data from the filePath, pass the new parameters into
     * the layer generated and then add that to the current stack of layers.
     *
     * @param name         A string of the name of the layer.
     * @param visible      The visibility of the layer.
     * @param currentLayer A boolean of whether or not its the current layer.
     * @param filePath     The file Path of the saved Layer.
     * @throws IOException If the reading of the file fails for some reason.
     */
    public void loadFile(String name, boolean visible, boolean currentLayer, String filePath)
            throws IOException {
        Layer newLayer = ImageUtil.readFile(filePath);
        newLayer.changeName(name);
        if (!visible) {
            newLayer.toggleVisibility();
        }
        if (!currentLayer) {
            makeCurrent(newLayer.name);
            newLayer.toggleCurrentLayer();
        }
        this.layers.add(newLayer);
    }

    /**
     * This will add a layer to the project.
     *
     * @param lyr The layer to be added.
     */
    public void addLayer(Layer lyr) { //fix the naming when adding
        if (lyr == null) {
            throw new IllegalArgumentException("Cannot add a null layer");
        }
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isCurrent()) {
                layers.get(i).toggleCurrentLayer();
            }
        }
        for (Layer projectLyr : this.layers) {
            if (lyr.name.equals(projectLyr.name)) {
                lyr.changeName("Untitled_Layer_" + this.layers.size());
            }
        }
        if (lyr.name.equals("Untitled Layer")) {
            lyr.changeName("Untitled_Layer_" + this.layers.size());
        }

        if (!lyr.isCurrent()) {
            makeCurrent(lyr.name);
            lyr.toggleCurrentLayer();
        }
        this.layers.add(lyr);
    }

    /**
     * This will traverse the lsit of layers and make the named layer current.
     *
     * @param layerName The name of the layer.
     */
    public void makeCurrent(String layerName) {
        getCurrentLayer().toggleCurrentLayer();
        for (Layer lyr : this.layers) {
            if (lyr.name.equals(layerName)) {
                lyr.toggleCurrentLayer();
                break;
            }
        }
    }

    /**
     * This will toggle the visibility of layer given.
     *
     * @param layerName The layerName of the layer we want to change.
     */
    public void toggleVisible(String layerName) {
        for (Layer lyr : this.layers) {
            if (lyr.name.equals(layerName)) {
                lyr.toggleVisibility();
                break;
            }
        }
    }

    /**
     * This will get the current layer of the project.
     *
     * @return Returns the currentLayer.
     */
    public Layer getCurrentLayer() {
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isCurrent()) {
                return layers.get(i);
            }
        }
        throw new IllegalArgumentException("no current layer!");
    }

    /**
     * Applies a filter on the current layer given a file type.
     *
     * @param filterType A file type is any that are supported in the FilterModel.
     */
    public void applyFilter(String filterType) {
        switch (filterType) {
            case "blur":
                new Blur().filter(getCurrentLayer());
                break;
            case "greyscale":
                new Greyscale().filter(getCurrentLayer());
                break;
            case "sepia":
                new Sepia().filter(getCurrentLayer());
                break;
            case "sharpen":
                new Sharpen().filter(getCurrentLayer());
                break;
            default:
                throw new IllegalArgumentException("filterType is not valid!");
        }
    }

}