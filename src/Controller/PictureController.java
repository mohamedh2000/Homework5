package Controller;

import java.util.Scanner;

public class PictureController implements IPictureController {

  private final Picture model;
  private final Readable rd;
  private final Appendable ap;

  public PictureController(Picture model, Readable rd, Appendable ap) {
    this.model = model;
    this.rd = rd;
    this.ap = ap;
  }

  private void commands() {
    Scanner scan = new Scanner(rd);
    CommandTypes currCommand = CommandTypes.EMPTY;

    while (scan.hasNext()) {
      String nextString = scan.next();
      switch (currCommand) {
        case EMPTY:
          switch (nextString) {
            case "create":
              currCommand = CommandTypes.CREATE;
              break;
            case "load":
              currCommand = CommandTypes.LOAD;
              break;
            case "save":
              currCommand = CommandTypes.SAVE;
            case "filter":
              currCommand = CommandTypes.FILTER;
              break;
            case "current":
              currCommand = CommandTypes.CURRENT;
            default:
              System.out.print("Invalid command");
          }
          break;

        case CREATE:
          if (nextString.equals("layer")) {
            currCommand = CommandTypes.NEWLAYER;
          }
          else {
            currCommand = CommandTypes.EMPTY;
          }
          break;
        case NEWLAYER:
          //TODO: make a new layer with the nextString as the layer name.

        case LOAD:
          //TODO: make this load a project with the nextString as the fileName.
          currCommand = CommandTypes.EMPTY;
          break;

        case SAVE:
          //TODO: make this save a project with the nextString as the filename.
          currCommand = CommandTypes.EMPTY;
          break;
        case FILTER:
          switch (nextString) {
            case "blur":
              //TODO: apply blur to the current layer
              break;
            case "greyscale":
              //TODO: apply greyscale to the current layer
              break;
            case "sepia":
              //TODO: apply sepia to the current layer
              break;
            case "sharpen":
              //TODO: apply sharpen to the current layer
              break;
            default:
              System.out.print("Could not apply this filter");

          }
          currCommand = CommandTypes.EMPTY;
          break;
        case CURRENT :
          //TODO: make the layer with the name of nextString the current layer
          currCommand = CommandTypes.EMPTY;
          break;

        default:
          System.out.print("Could not ccomplete this command");


      }
    }
  }

}
