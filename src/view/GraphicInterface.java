package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.ImageUtil;
import model.Project;
import model.layermodel.Layer;


public class GraphicInterface extends JFrame {

  private final JMenuBar fileTypeMenuBar;
  private final JMenu fileTypeMenu;
  private final JMenuItem ppmMenu;
  private final JMenuItem pngMenu;
  private final JMenuItem jpegMenu;

  private final JMenuBar layersMenuBar;
  private final JMenu layersMenu;

  private final JTextField loadFileNameField;
  private final JTextField loadLayerNameField;
  private final JButton loadButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton saveButton;
  private final JButton saveAllButton;

  private JScrollPane scrollPane;

  public GraphicInterface() {
    super();
    setSize(10, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.fileTypeMenuBar = new JMenuBar();
    this.fileTypeMenu = new JMenu("Save fileType");
    this.ppmMenu = new JMenuItem("ppm");
    ppmMenu.setActionCommand("ppmType");
    this.pngMenu = new JMenuItem("png");
    pngMenu.setActionCommand("pngType");
    this.jpegMenu = new JMenuItem("jpeg");
    jpegMenu.setActionCommand("jpegType");

    this.layersMenuBar = new JMenuBar();
    this.layersMenu = new JMenu("Current Layer");
    this.layersMenuBar.add(layersMenu);

    this.fileTypeMenu.add(this.ppmMenu);
    this.fileTypeMenu.add(pngMenu);
    this.fileTypeMenu.add(jpegMenu);
    this.fileTypeMenuBar.add(fileTypeMenu);

    JTextField loadFileName = new JTextField("Load File name", 20);
    JTextField loadLayerName = new JTextField("Load Layer name", 20);
    this.loadFileNameField = loadFileName;
    this.loadLayerNameField = loadLayerName;

    this.loadButton = new JButton("Load");
    loadButton.setActionCommand("load");
    this.blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    this.sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    this.greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("sharpen");
    this.sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    this.saveButton = new JButton("Save");
    saveButton.setActionCommand("save");

    this.saveAllButton = new JButton("Save All");
    saveAllButton.setActionCommand("save all");

    JPanel options = new JPanel();

    options.setLayout(new FlowLayout());

    options.add(this.loadFileNameField, BorderLayout.WEST);
    options.add(this.loadLayerNameField, BorderLayout.WEST);
    options.add(this.loadButton, BorderLayout.WEST);
    options.add(this.blurButton, BorderLayout.WEST);
    options.add(this.sharpenButton, BorderLayout.WEST);
    options.add(this.greyscaleButton, BorderLayout.WEST);
    options.add(this.sepiaButton, BorderLayout.WEST);
    options.add(this.fileTypeMenuBar, BorderLayout.NORTH);
    options.add(this.saveButton, BorderLayout.WEST);
    options.add(this.saveAllButton, BorderLayout.WEST);
    options.add(this.layersMenuBar, BorderLayout.NORTH);

    JList<Layer> listScrollPane = new JList<Layer>();

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        listScrollPane, scrollPane);

    splitPane.setDividerLocation(150);

    add(options, BorderLayout.NORTH);
    add(splitPane, BorderLayout.CENTER);
    pack();
  }

  public static void main(String[] args, Project p) {
    GraphicInterface frame = new GraphicInterface();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      p.addLayer(ImageUtil.readJPEGPNG("pic1.jpg"));

      BufferedImage image = p.getCurrentLayer().writeBufferedImage(p.getCurrentLayer().getWidth(),
          p.getCurrentLayer().getHeight(), p.getCurrentLayer().getPixelPositions());
      final JLabel imageLabel = new JLabel(new ImageIcon(image));
      final JScrollPane scroll = new JScrollPane(imageLabel);
      frame.add(scroll);


      //frame.add(new ImageIcon(image));

      //setViewportView(new ImageIcon(image));

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

  }

  public void setListener(ActionListener listener) {
    ppmMenu.addActionListener(listener);
    pngMenu.addActionListener(listener);
    jpegMenu.addActionListener(listener);

    loadFileNameField.addActionListener(listener);
    loadLayerNameField.addActionListener(listener);
    loadButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    saveAllButton.addActionListener(listener);
  }

  public String getLoadFileName() {
    return loadFileNameField.getText();
  }

  public String getLoadLayerName() {
    return loadLayerNameField.getText();
  }

  public void setScrollPane(JScrollPane sp) {
    this.scrollPane = sp;
  }

  public void addLayerMenuItem(String layerName, ActionListener listener) {
    JButton newButton = new JButton(getLoadLayerName());
    newButton.setActionCommand("Layers" + layerName);
    newButton.addActionListener(listener);
    this.layersMenu.add(newButton);
  }


}
