package controller;

import java.io.IOException;
import model.Project;
import view.GraphicInterface;

public class GraphicInterfaceControllerMain {


  public static void main(String[] args) throws IOException {
    Project p = new Project();
    GraphicInterface v = new GraphicInterface();
    GraphicInterfaceController c = new GraphicInterfaceController(p, v);
    v.main(args,p);
  }

}
