package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import model.layermodel.Layer;

public class GraphicInterface extends JFrame {

  private final JMenuBar fileTypeMenuBar;
  private final JMenu fileTypeMenu;
  private final JMenuItem ppmMenu;
  private final JMenuItem pngMenu;
  private final JMenuItem jpegMenu;

  private final JTextField loadFileNameField;
  private final JTextField loadLayerNameField;
  private final JButton loadButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton saveButton;
  private final JButton saveAllButton;


  public GraphicInterface() {
    super();
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.fileTypeMenuBar = new JMenuBar();
    this.fileTypeMenu = new JMenu("Save fileType");
    this.ppmMenu = new JMenuItem("ppm");
    ppmMenu.setActionCommand("ppmType");
    this.pngMenu = new JMenuItem("png");
    pngMenu.setActionCommand("pngType");
    this.jpegMenu = new JMenuItem("jpeg");
    jpegMenu.setActionCommand("jpegType");
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

    JList<Layer> listScrollPane = new JList<Layer>();
    JScrollPane pictureScrollPane = new JScrollPane();

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        listScrollPane, pictureScrollPane);

    splitPane.setDividerLocation(150);

    add(options, BorderLayout.NORTH);
    add(splitPane, BorderLayout.CENTER);
    pack();
  }


  public static void main(String[] args) {
    GraphicInterface frame = new GraphicInterface();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

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


}
