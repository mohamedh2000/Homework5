package controller;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main method.
 */
public class ControllerMain {

  /**
   * Runs Commands on a Project Controller.
   *
   * @param args The commands inputted.
   */
  public static void main(String[] args) throws IOException {
    ProjectController controller = new ProjectController(new InputStreamReader(System.in),
        System.out);
    //controller.view.main(args);
    controller.commands();
  }
}
