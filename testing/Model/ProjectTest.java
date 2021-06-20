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
import static org.junit.Assert.assertTrue;

/** Class used to test public methods of the Project class.
 *
 */
public class ProjectTest {

  /**
   * Used to test the change name function.
   */
  @Test
  public void testChangeName() {
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(
        new Pixel(new Color(255, 255, 255), new Position(0, 0))));
    Layer l1 = new Layer(pixels, 1, 1);
    ArrayList<Layer> lp1 = new ArrayList<Layer>(Arrays.asList(l1));
    Project p1 = new Project("Project");
    assertEquals("Project", p1.name);

    p1.changeName("Different Name");
    assertEquals("Different Name", p1.name);
  }

  /**
   * Used to test the save method.
   * @throws IOException If the write methods fail.
   */
  @Test
  public void testSave() throws IOException { //works

    Layer test1Layer = ImageUtil.readJPEGPNG("aenami.jpeg");
    Layer test1LayerII = ImageUtil.readPPM("testRainbow.ppm");
    ArrayList<Layer> test1List = new ArrayList<>();
    test1List.add(test1Layer);
    test1List.add(test1LayerII);
    Project test1 = new Project(test1List);
    test1.save("jpeg");
  }

  /**
   * Used to test the save all method.
   * @throws IOException If the write methods fail.
   */
  @Test
  public void testSaveAll() throws IOException { //works
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

  /**
   * Used to test the load file method.
   * @throws IOException If the write methods fail.
   */
  @Test
  public void testLoadFile() throws IOException { //works
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

  /**
   * Tests the addLayer method.
   */
  @Test
  public void addLayer() {
    Project testProject = new Project();
    testProject.addLayer(new Layer(100,100));
    assertEquals(testProject.layers.size(), 1);
  }

  /**
   * Tests the makeCurrent method.
   */
  @Test
  public void makeCurrent() {
    Project testProject = new Project();
    Layer testLayer1 = new Layer(100,100);
    Layer testLayer2 = new Layer(100,100);

    testProject.addLayer(testLayer1);
    testProject.addLayer(testLayer2);
    testProject.makeCurrent("Untitled_Layer_1");
    assertTrue(testLayer2.isCurrent());
  }

  /**
   * Tests the toggleVisibility method.
   */
  @Test
  public void toggleVisible() {
    Project testProject = new Project();
    Layer testLayer1 = new Layer(100,100);
    Layer testLayer2 = new Layer(100,100);

    testProject.addLayer(testLayer1);
    testProject.addLayer(testLayer2);
    testProject.toggleVisible("Untitled_Layer_1");
    assertFalse(testLayer2.getVisibility());
  }

  /**
   * Tets the currentLayer method.
   */
  @Test
  public void getCurrentLayer() {
    Project testProject = new Project();
    Layer testLayer1 = new Layer(100,100);
    Layer testLayer2 = new Layer(100,100);

    testProject.addLayer(testLayer1);
    testProject.addLayer(testLayer2);
    assertEquals(testProject.getCurrentLayer(), testLayer2);
  }

  /**
   *Tets the applyFilter method.
   */
  @Test
  public void applyFilter() throws IOException {
    Project testProject = new Project();
    Layer testLayer1 = new Layer(100,100);
    Layer testLayer2 = ImageUtil.readJPEGPNG("spencer.jpeg");

    testProject.addLayer(testLayer1);
    testProject.addLayer(testLayer2);
    testProject.applyFilter("blur");
    assertTrue(testLayer2.getPixels() != ImageUtil.readJPEGPNG("spencer.jpeg").getPixels());
  }

}