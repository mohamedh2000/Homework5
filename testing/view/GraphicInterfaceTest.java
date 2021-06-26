package view;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import org.junit.Test;

public class GraphicInterfaceTest {

  @Test
  public void testLoadListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "load");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(450, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

  }

  @Test
  public void testBlurListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "blur");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(500, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testSharpenListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "sharpen");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(600, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

  }

  @Test
  public void testGreyscaleListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "greyscale");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(700, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

  }

  @Test
  public void testSepiaListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "sepia");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(800, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testSaveListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "save");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(920, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testSaveAllListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "save all");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(1050, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testPPMListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "ppm");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(850, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    bot.mouseMove(850, 70);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testPNGListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "png");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(850, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    bot.mouseMove(850, 90);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testJPEGListener() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "jpeg");
      }
    };
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(850, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    bot.mouseMove(850, 110);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testMenuButton() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    ActionListener actionListener = new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        assertEquals(e.getActionCommand(), "Layers new");
      }
    };
    gui.addLayerMenuItem("new", actionListener);
    gui.setListener(actionListener);
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(1100, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }

    bot.mouseMove(1100, 70);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  @Test
  public void testGetLoadFileName() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(80, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }

    bot.keyPress(65);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.keyRelease(65);


    assertEquals("Load File a", gui.getLoadFileName());
  }

  @Test
  public void testGetLoadLayerName() throws AWTException {
    GraphicInterface gui = new GraphicInterface();
    gui.setVisible(true);
    Robot bot = new Robot();

    bot.mouseMove(300, 50);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }

    bot.keyPress(65);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    bot.keyRelease(65);


    assertEquals("Load Layer a", gui.getLoadLayerName());
  }




}