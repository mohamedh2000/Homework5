package Controller;

import Model.ImageUtil;
import Model.LayerModel.Layer;
import Model.Project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static Controller.CommandTypes.*;

public class ProjectController implements IProjectController {

    private Readable rd;
    private Appendable ap;

    public ProjectController(Readable rd, Appendable ap) {
        this.rd = rd;
        this.ap = ap;
    }

    public ProjectController(String pathToTextFile, Appendable ap) {
       File inputtedTextFile = new File(pathToTextFile);
       if(!inputtedTextFile.exists()) {
           throw new IllegalArgumentException("This text file does not exist");
       }
       this.ap = ap;
    }

    //THEY CAN DO THIS INTERACTIVELY, SO INPUT CAN BE CONSOLE AND OUTPUT CAN BE SCANNER
    private Layer evaluateLine(Project proj, CommandTypes currCommand, Scanner scan) {
            switch(currCommand) {
                case EMPTY:
                    break;
                case CREATE:
                    if(scan.hasNextInt()) {
                        int width = scan.nextInt();
                        if(!scan.hasNextInt()) {
                            throw new IllegalArgumentException("Issue with line of code");
                        }
                        int height = scan.nextInt();
                        return new Layer(width, height);
                    }
                    else {
                        throw new IllegalArgumentException("Issue with line of code");
                    }
                case FILTER: //applies to current layer
                    String filterType = scan.next();
                    switch (filterType) {
                        case "blur":
                            //TODO: apply blur to the current layer
                            proj.applyFilter("blur");
                            break;
                        case "greyscale":
                            //TODO: apply greyscale to the current layer
                            proj.applyFilter("greyscale");
                            break;
                        case "sepia":
                            //TODO: apply sepia to the current layer
                            proj.applyFilter("sepia");
                            break;
                        case "sharpen":
                            //TODO: apply sharpen to the current layer
                            proj.applyFilter("sharpen");
                            break;
                        default:
                            System.out.print("Could not apply this filter");
                    }
                case CURRENT:
                    String newCurrentLayerName = scan.next();
                    proj.makeCurrent(newCurrentLayerName);
                    break;
                case VISIBLE:
                    String layerName = scan.next();
                    proj.toggleVisible(layerName);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currCommand);
            }
        return null;
    }

    private void saveLine(CommandTypes currCommand, Scanner scan, Project currentProject) throws IOException {
        String fileType = scan.next();
        switch (currCommand) {
            case SAVE:
                currentProject.save(fileType);
            default:
                currentProject.saveAll(fileType);
        }
    }

    private Layer loadLayerLine(Scanner scan) throws IllegalArgumentException, IOException {
        if(scan.hasNext()) {
            String fileName = scan.next();
            return ImageUtil.readFile(fileName);
        }
        return null;
    }

    private void loadTextFile(Project proj, String fileName) throws IOException {
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(data.trim().split("\\s+").length == 1) {
                proj.changeName(data);
            }
            else {
                String[] line = data.trim().split("\\s+");
                String name = line[0];
                boolean visible = Boolean.getBoolean(line[1]);
                boolean current = Boolean.getBoolean(line[2]);
                String filePlacement = line[3];
                if(name == null || filePlacement == null) {
                    System.out.println("Couldn't load text file. Something in here is incorrect.");
                }
                proj.loadFile(name, visible, current, filePlacement);
            }
        }
        myReader.close();
    }


    public void commands() throws IOException {
        Project proj = new Project();
        Scanner scan = new Scanner(rd);
        CommandTypes currCommand = EMPTY;

        while (scan.hasNextLine()) {  //TODO: Look at loadTextFile, we should be reading every line not every word
            String nextString = scan.nextLine();
            switch (currCommand) {
                case EMPTY:
                    switch (nextString) {
                        case "create": //create new layers
                            currCommand = CREATE;
                            Layer newLayer = evaluateLine(proj, currCommand, scan);
                            proj.addLayer(newLayer);
                            break;
                        case "load": //load a layer
                            currCommand = LOAD;
                            Layer loadedLayer = loadLayerLine(scan);
                            proj.addLayer(loadedLayer);
                            break;
                        case "save": //save top most layer
                            currCommand = SAVE;
                            saveLine(currCommand, scan, proj);
                        case "saveAll": //save all layers
                            currCommand = SAVEALL;
                            saveLine(currCommand, scan, proj);
                        case "filter": //use a certain filter
                            currCommand = FILTER;
                            evaluateLine(proj, currCommand, scan);
                            break;
                        case "current": //toggle new current Layer
                            currCommand = CURRENT;
                            evaluateLine(proj, currCommand, scan);
                        case "visible": //toggle visibility of a layer
                            currCommand = VISIBLE;
                            evaluateLine(proj, currCommand, scan);
                        default:
                            if(new File(nextString).exists()) {
                                //TODO:LOAD FILE from Project loadFILE
                                loadTextFile(proj, nextString);
                            }
                            else {
                                System.out.print("Invalid command");
                            }
                    }
                    break;
                default:
                    System.out.print("Could not ccomplete this command");
            }
        }
    }

}
