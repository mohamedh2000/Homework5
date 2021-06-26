package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.Project;
import model.layermodel.Layer;
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
          v.addLayerMenuItem(layerName, this);
          v.updateImage(p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
              p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions()));
        }
        catch (IOException invfile) {
          System.out.print("Could not load this file.\n");
        }
        break;
      case "blur" :
        p.applyFilter("blur");
        v.updateImage(p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
            p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions()));
        break;
      case "sharpen" :
        p.applyFilter("sharpen");
        v.updateImage(p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
            p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions()));
        break;
      case "greyscale" :
        p.applyFilter("greyscale");
        v.updateImage(p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
            p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions()));
        break;
      case "sepia" :
        p.applyFilter("sepia");
        v.updateImage(p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
            p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions()));
        break;
      case "save" :
        this.saveHelper();
        break;
      case "save all" :
        this.saveAllHelper();
        break;
      default:
        for (Layer l: p.layers) {
          if (l.name.equals(e.getActionCommand())) {
            p.makeCurrent(e.getActionCommand());
          }
        }
     }

  }

  private void saveAllHelper() {
    try {
      switch (this.fileType) {
        case PPM:
          p.saveAll("ppm");
          break;
        case PNG:
          p.saveAll("png");
          break;
        case JPEG:
          p.saveAll("jpeg");
          break;
        default: //Do nothing
      }
    }
    catch(IOException saveException) {
      System.out.print("Could not save file");
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
