package Model;

import Model.LayerModel.Layer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectTest {

    @Test
    public void changeName() {
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
        testLoad.loadFile("SAVEALLTEST", true, true, "/Users/hussein/Desktop/AssignmentV/code/SAVEALLTEST.jpeg");
        testLoad.loadFile("SAVEALLTESTII", true, true, "/Users/hussein/Desktop/AssignmentV/code/SAVEALLTESTII.png");
        testLoad.saveAll("jpeg");
    }
}