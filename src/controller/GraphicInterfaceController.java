package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Project;
import model.filtermodel.Blur;
import view.GraphicInterface;

public class GraphicInterfaceController implements ActionListener {
  private Project p;
  private GraphicInterface v;
  public GraphicInterfaceController(Project p, GraphicInterface v) {
    this.p = p;
    this.v = v;
    v.setListener(this);

  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
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
        //save
        break;
      case "save all" :
        //save all
        break;
      default: //Do something here
     }

  }
}
