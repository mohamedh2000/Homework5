package controller;
import java.io.IOException;
import javax.swing.JFrame;
import model.Project;
import view.GraphicInterface;

public class GraphicInterfaceControllerMain {


  public static void main(String[] args) throws IOException {
    Project p = new Project();
    GraphicInterface v = new GraphicInterface();
    GraphicInterfaceController c = new GraphicInterfaceController(p, v);
    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    v.setVisible(true);

  }

}
