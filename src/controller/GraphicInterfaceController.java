package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.Project;
import view.GraphicInterface;

public class GraphicInterfaceController implements ActionListener {
  private final Project p;
  private final GraphicInterface v;
  private FileTypes fileType;
  public GraphicInterfaceController(Project p, GraphicInterface v) {
    this.p = p;
    this.v = v;
    this.fileType = FileTypes.PPM;
    v.setListener(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "ppmType" :
        this.fileType = FileTypes.PPM;
        break;
      case "pngType" :
        this.fileType = FileTypes.PNG;
        break;
      case "jpegType" :
        this.fileType = FileTypes.JPEG;
        break;
      case "load" :
        String filename = v.getLoadFileName();
        String layerName = v.getLoadLayerName();
        try {
          p.loadFile(layerName, true, true, filename);
        }
        catch (IOException invfile) {
          System.out.print("Could not load this file.");
        }
        break;
      case "blur" :
        p.applyFilter("blur");
        break;
      case "sharpen" :
        p.applyFilter("sharpen");
        break;
      case "greyscale" :
        p.applyFilter("greyscale");
        break;
      case "sepia" :
        p.applyFilter("sepia");
        break;
      case "save" :
        saveHelper();
        break;
      case "save all" :
        //save all
        break;
      default: //Do something here
     }

  }

  private void saveHelper() {
    try {
      switch (this.fileType) {
        case PPM:
          p.save("ppm");
          break;
        case PNG:
          p.save("png");
          break;
        case JPEG:
          p.save("jpeg");
          break;
        default: //Do nothing
      }
    }
    catch(IOException saveException) {
      System.out.print("Could not save file");
    }
  }

}
