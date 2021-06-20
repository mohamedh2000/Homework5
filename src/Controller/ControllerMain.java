package Controller;

import Model.Project;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerMain {

  /** Runs Commands on a Project Controller.
   *
   * @param args The commands inputted.
   */
  public static void main(String[] args) {
    Project model = new Project();
    ProjectController controller = new ProjectController(new InputStreamReader(System.in), System.out);
    try{
      controller.commands();
    }
    catch (IOException e) {

    }

    }
}
