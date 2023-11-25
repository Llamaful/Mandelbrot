import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Panel extends JPanel {
  double focusDistance = 4, diameter = 8;
  int maxIterate = 40;

  final int margin = 80;

  Timer timer;
  final int updateMS = 200;

  Point prevMouse = null;
  
  public Panel() {
    setBackground(new Color(20, 20, 20));
    setPreferredSize(new Dimension(800, 800));

    Mouse mouse = new Mouse();
    addMouseListener(mouse);
    addMouseMotionListener(mouse);

    timer = new Timer(updateMS, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        update(updateMS / 1000.0);
      }
    });
    timer.setRepeats(true);
    // timer.start();
  }

  public void setMaxIterate(int iterate) {
    this.maxIterate = iterate;
  }

  public void update(double dt) {
    repaint();
  }
  private class Mouse implements MouseListener, MouseMotionListener {
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Add anti-aliasing
    ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.translate(getWidth()/2, getHeight()/2);

    final int height = getHeight() - margin*2;
    final double scale = height / height();
    final int width = (int)(diameter * scale);

    // draw ellipse
    g.setColor(Color.WHITE);
    g.drawOval(-width/2, -height/2, width, height);

    // draw foci
    final int fociSize = 4;
    g.fillOval((int)(-focusDistance/2 * scale - fociSize), -fociSize, fociSize*2, fociSize*2);
    g.fillOval((int)(focusDistance/2 * scale - fociSize), -fociSize, fociSize*2, fociSize*2);
  }

  double height() {
    return Math.sqrt( diameter*diameter - focusDistance*focusDistance );
  }

  Point2D.Double getMousePoint(MouseEvent e) {
    return new Point2D.Double(e.getX(), e.getY());
  }
}