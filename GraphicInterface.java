package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;

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

    setLayout(new BorderLayout());

    this.loadTextField = new JTextField(30);
    this.loadButton = new JButton("Load");
    this.blurButton = new JButton("Blur");
    this.sharpenButton = new JButton("Sharpen");
    this.greyscaleButton = new JButton("Greyscale");
    this.sepiaButton = new JButton("Sepia");
    this.saveButton = new JButton("save");
    this.saveAllButton  = new JButton("saveAll");

    add(this.loadTextField, BorderLayout.NORTH);
    add(this.loadButton, BorderLayout.NORTH);
    add(this.blurButton, BorderLayout.WEST);
    add(this.sharpenButton, BorderLayout.WEST);
    add(this.greyscaleButton, BorderLayout.WEST);
    add(this.sepiaButton, BorderLayout.WEST);
    add(this.saveButton, BorderLayout.EAST);
    add(this.saveAllButton, BorderLayout.EAST);

    pack();



  }

}
