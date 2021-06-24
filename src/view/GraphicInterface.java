package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.layermodel.Layer;

public class GraphicInterface extends JFrame {
  private final JTextField loadTextField;
  private final JButton loadButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton saveButton;
  private final JButton saveAllButton;

  public GraphicInterface(){
    super();
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JTextField txtFld = new JTextField(30);
    this.loadTextField = txtFld;
    this.loadButton = new JButton("Load");

    this.loadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    this.blurButton = new JButton("Blur");
    this.sharpenButton = new JButton("Sharpen");
    this.greyscaleButton = new JButton("Greyscale");
    this.sepiaButton = new JButton("Sepia");
    this.saveButton = new JButton("save");
    this.saveAllButton  = new JButton("saveAll");

    JPanel options = new JPanel();

    options.setLayout(new FlowLayout());

    options.add(this.loadTextField, BorderLayout.WEST);
    options.add(this.loadButton, BorderLayout.WEST);
    options.add(this.blurButton, BorderLayout.WEST);
    options.add(this.sharpenButton, BorderLayout.WEST);
    options.add(this.greyscaleButton, BorderLayout.WEST);
    options.add(this.sepiaButton, BorderLayout.WEST);
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

}
