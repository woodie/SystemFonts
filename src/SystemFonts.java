import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/*
 * SystemFonts 
 */
public class SystemFonts extends MIDlet {
  private Display display = null;
  private MainCanvas mainCanvas = null;
  private int width;
  private int height;
  private int cw;
  private int ch;
  private int this_x;
  private int this_y;
  private int ascii; 
  private Font smallFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
  private Font largeFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
  private final int BLACK = 0x000000;
  private final int WHITE = 0xFFFFFF;
  private final int CYAN = 0x00AAFF;
  private final int BLUE = 0x000088;

  public SystemFonts() {
    display = Display.getDisplay(this);
    mainCanvas = new MainCanvas(this);
  }

  public void startApp() throws MIDletStateChangeException {
    display.setCurrent(mainCanvas);
  }

  public void pauseApp() {}

  protected void destroyApp(boolean unconditional)
      throws MIDletStateChangeException {}

/*
 * Main Canvas
 */
  class MainCanvas extends Canvas {
    private SystemFonts parent = null;

    public MainCanvas(SystemFonts parent) {
      this.parent = parent;
      this.setFullScreenMode(true);
      width = getWidth();
      height = getHeight();
      cw = width / 12;
      ch = height / 16;      
    }

    public void paint(Graphics g) {
      g.setColor(BLACK);
      g.fillRect(0, 0, width, height);

      g.setColor(BLUE);
      for (int i = 0; i < 12; i++) {
        g.drawLine(i * cw, 0, i * cw, height);
      }
      for (int i = 0; i < 16; i++) {
        g.drawLine(0, i * ch, width, i * ch);
      }
      g.setColor(WHITE);

      g.setFont(largeFont);
      ascii = 32;
      for (int x = 0; x < 12; x++) {
        for (int y = 0; y < 8; y++) {
          this_x = x * cw + 2;
          this_y = y * ch + 1;
          String s = String.valueOf((char) ascii);  
          g.drawString(s, this_x + 2, this_y, Graphics.LEFT | Graphics.TOP);
          ++ascii;
        }
      }
      g.setFont(smallFont);
      ascii = 32;
      for (int x = 0; x < 12; x++) {
        for (int y = 0; y < 8; y++) {
          this_x = x * cw + 2;
          this_y = y * ch + (height / 2);
          String s = String.valueOf((char) ascii);  
          g.drawString(s, this_x + 2, this_y, Graphics.LEFT | Graphics.TOP);
          ++ascii;
        }
      }

    }
  }
}
