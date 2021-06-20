package Controller;

import static Controller.CommandTypes.CREATE;
import static Controller.CommandTypes.CURRENT;
import static Controller.CommandTypes.EMPTY;
import static Controller.CommandTypes.FILTER;
import static Controller.CommandTypes.LOAD;
import static Controller.CommandTypes.SAVE;
import static Controller.CommandTypes.SAVEALL;
import static Controller.CommandTypes.VISIBLE;

import Model.ImageUtil;
import Model.LayerModel.Layer;
import Model.Project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/** Controls a Project.
 *
 */
public class ProjectController implements IProjectController {

  private Readable rd;
  private Appendable ap;

  public ProjectController(String fileName, Appendable ap) throws FileNotFoundException {
    if (fileName == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Inputs are invalid.");
    }

    this.rd = new FileReader(fileName);
    this.ap = ap;
  }

  public ProjectController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Inputs are invalid.");
    }

    this.rd = rd;
    this.ap = ap;
  }


  //THEY CAN DO THIS INTERACTIVELY, SO INPUT CAN BE CONSOLE AND OUTPUT CAN BE SCANNER

  /** Evaluates Create, Filter, Current, and Visible commands.
   *
   * @param proj the project the command is being used on/
   * @param currCommand The type of command being used.
   * @param scan A scanner containing the arguments of the command.
   * @return
   */
  private Layer evaluateLine(Project proj, CommandTypes currCommand, Scanner scan) {
    switch (currCommand) {
      case EMPTY:
        break;
      case CREATE:
        if (scan.hasNext()) {
          String layerName = scan.next();
          if (scan.hasNextInt()) {
            int width = scan.nextInt();
            if (!scan.hasNextInt()) {
              throw new IllegalArgumentException("Issue with line of code");
            }
            int height = scan.nextInt();
            return new Layer(layerName, width, height);
          } else {
            throw new IllegalArgumentException("Issue with line of code");
          }
        } else {
          throw new IllegalArgumentException("No name for layer");
        }
      case FILTER: //applies to current layer
        if (scan.hasNext()) {
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
        }
      case CURRENT:
        if (scan.hasNext()) {
          String newCurrentLayerName = scan.next();
          proj.makeCurrent(newCurrentLayerName);
        }
        break;
      case VISIBLE:
        if (scan.hasNext()) {
          String layerName = scan.next();
          proj.toggleVisible(layerName);
        }
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + currCommand);
    }
    return null;
  }

  /** Saves either a single layer or all the layers of a project.
   *
   * @param currCommand The current commandType,
   * @param scan A scanner containing the fileType.
   * @param currentProject The current project being saved.
   * @throws IOException
   */
  private void saveLine(CommandTypes currCommand, Scanner scan, Project currentProject)
      throws IOException {
    if (scan.hasNext()) {
    String fileType = scan.next();
    switch (currCommand) {
      case SAVE:
        currentProject.save(fileType);
      default:
        currentProject.saveAll(fileType);
    }
    }
  }

  /** Loads an image into a new layer in a project.
   *
   * @param scan A scanner with the filename and (Optional: the name of the layer).
   * @return
   * @throws IllegalArgumentException If the file with the filename does not exist.
   * @throws IOException
   */
  private Layer loadLayerLine(Scanner scan) throws IllegalArgumentException, IOException {
    if (scan.hasNext()) {
      String fileName = scan.next();
      if (scan.hasNext()) {
        String name = scan.next();
        Layer namedLayer = ImageUtil.readFile(fileName);
        namedLayer.changeName(name);
        return namedLayer;
      }
      return ImageUtil.readFile(fileName);
    }
    return null;
  }

  /** Loads a text file into the controller/
   *
   *
   * @param proj The project being loaded into.
   * @param fileName The name of the textfile.
   * @throws IOException
   */
  private void loadTextFile(Project proj, String fileName) throws IOException {
    File file = new File(fileName);
    Scanner myReader = new Scanner(file);
    while (myReader.hasNextLine()) {
      String data = myReader.nextLine();
      if (data.trim().split("\\s+").length == 1) {
        proj.changeName(data);
      } else {
        String[] line = data.trim().split("\\s+");
        String name = line[0];
        boolean visible = Boolean.getBoolean(line[1]);
        boolean current = Boolean.getBoolean(line[2]);
        String filePlacement = line[3];
        if (name == null || filePlacement == null) {
          System.out.println("Couldn't load text file. Something in here is incorrect.");
        }
        proj.loadFile(name, visible, current, filePlacement);
      }
    }
    myReader.close();
  }

  /** Completes commands on a Project Controller.
   *
   * @throws IOException
   */
  public void commands() throws IOException {
    Project proj = new Project();
    Scanner scan = new Scanner(rd);
    CommandTypes currCommand = EMPTY;

    while (scan
        .hasNextLine()) {  //TODO: Look at loadTextFile, we should be reading every line not every word
      String nextLine = scan.nextLine();
      Scanner scanString = new Scanner(nextLine);
      while (scanString.hasNext()) {
        String nextString = scanString.next();
        switch (currCommand) {
          case EMPTY:
            switch (nextString) {
              case "create": //create new layers
                currCommand = CREATE;
                Layer newLayer = evaluateLine(proj, currCommand, scanString);
                proj.addLayer(newLayer);
                currCommand = EMPTY;
                break;
              case "load": //load a layer
                currCommand = LOAD;
                Layer loadedLayer = loadLayerLine(scanString);
                proj.addLayer(loadedLayer);
                currCommand = EMPTY;
                break;
              case "save": //save top most layer
                currCommand = SAVE;
                saveLine(currCommand, scanString, proj);
                currCommand = EMPTY;
                break;
              case "saveAll": //save all layers
                currCommand = SAVEALL;
                saveLine(currCommand, scanString, proj);
                currCommand = EMPTY;
                break;
              case "filter": //use a certain filter
                currCommand = FILTER;
                evaluateLine(proj, currCommand, scanString);
                currCommand = EMPTY;
                break;
              case "current": //toggle new current Layer
                currCommand = CURRENT;
                evaluateLine(proj, currCommand, scanString);
                currCommand = EMPTY;
                break;
              case "visible": //toggle visibility of a layer
                currCommand = VISIBLE;
                evaluateLine(proj, currCommand, scanString);
                currCommand = EMPTY;
                break;
              default:
                if (new File(nextString).exists()) {
                  //TODO:LOAD FILE from Projecft loadFILE
                  loadTextFile(proj, nextString);
                  currCommand = EMPTY;
                } else {
                  System.out.print("Invalid command");
                  currCommand = EMPTY;
                }
            }
            break;
          default:
            System.out.print("Could not complete this command");


        }
      }
    }
  }

}
