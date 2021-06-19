package Model;

import Model.LayerModel.Layer;
import Model.PixelModel.Color;
import Model.PixelModel.Pixel;
import Model.PixelModel.Position;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/** Class used to test public methods of the Project class.
 *
 */
public class ProjectTest {

  @Test
  public void changeName() {
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    Layer l1 = new Layer(pixels, 1, 1);
    ArrayList<Layer> lp1 = new ArrayList<Layer>(Arrays.asList(l1));
    Project p1 = new Project("Project");
    assertEquals("Project", p1.name);

    p1.changeName("Different Name");
    assertEquals("Different Name", p1.name);
  }

  @Test
  public void save() throws IOException { //works

    Layer test1Layer = ImageUtil.readJPEGPNG("aenami.jpeg");
    Layer test1LayerII = ImageUtil.readPPM("testRainbow.ppm");
    ArrayList<Layer> test1List = new ArrayList<>();
    test1List.add(test1Layer);
    test1List.add(test1LayerII);
    Project test1 = new Project(test1List);
    test1.save("jpeg");
  }

  @Test
  public void saveAll() throws IOException { //works
    Layer test2Layer = ImageUtil.readJPEGPNG("aenami.jpeg");
    Layer test2LayerII = ImageUtil.readPPM("testRainbow.ppm");
    ArrayList<Layer> test2List = new ArrayList<>();
    test2Layer.changeName("SAVEALLTEST");
    test2LayerII.changeName("SAVEALLTESTII");
    test2List.add(test2Layer);
    test2List.add(test2LayerII);
    Project test2 = new Project(test2List);
    test2.changeName("PROJ_TEST_2");
    test2.saveAll("png");
  }

  @Test
  public void loadFile() throws IOException { //works
    //assuming the scanner is correct and gets the right data of:
    // PROJ_TEST_2 --project name
    //SAVEALLTEST true true /Users/hussein/Desktop/AssignmentV/code/SAVEALLTEST.png
    //SAVEALLTESTII true true /Users/hussein/Desktop/AssignmentV/code/SAVEALLTESTII.png
    //scanner will get a string subset and know the file type

    Project testLoad = new Project();
    testLoad.loadFile("SAVEALLTEST", true, true,
        "/Users/hussein/Desktop/AssignmentV/code/SAVEALLTEST.jpeg");
    testLoad.loadFile("SAVEALLTESTII", true, true,
        "/Users/hussein/Desktop/AssignmentV/code/SAVEALLTESTII.png");
    testLoad.saveAll("jpeg");
  }
}