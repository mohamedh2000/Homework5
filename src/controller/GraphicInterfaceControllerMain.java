package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import model.ImageUtil;
import model.Project;
import view.GraphicInterface;

public class GraphicInterfaceControllerMain {


  public static void main(String[] args) throws IOException {
    Project p = new Project();
    GraphicInterface v = new GraphicInterface();
    GraphicInterfaceController c = new GraphicInterfaceController(p, v);
    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    v.setVisible(true);

    /*
    p.addLayer(ImageUtil.readJPEGPNG("masuka.jpeg"));

    BufferedImage image = p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
        p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions());
    final JLabel imageLabel = new JLabel(new ImageIcon(image));
    final JScrollPane scroll = new JScrollPane(imageLabel);
    v.setScrollPane(scroll);
    */

  }

}
