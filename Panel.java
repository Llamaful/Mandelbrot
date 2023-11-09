import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Panel extends JPanel {
  Screen screen = new Screen(-2.5, 1.5, -2, 2);
  int maxIterate = 40;
  Fractal fractal = new Fractal(maxIterate);
  Gradient gradient = new Gradient(
    new Color(0, 0, 0), new Color(10, 0, 40), new Color(60, 0, 80), new Color(100, 20, 90), new Color(200, 40, 30), new Color(250, 180, 20), new Color(255, 255, 0), new Color(255, 255, 100)
  );

  Timer timer;
  final int updateMS = 200;

  Point prevMouse = null;

  public boolean AUTO_ZOOM = false;
  final Point2D.Double zoomPoint = new Point2D.Double(-0.7695111434952743, 0.11317967171424534);

  int resolution = 1;

  public boolean showRecursion = false;
  ComplexNumber selectedPoint = null;
  
  public Panel() {
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(800, 800));

    Mouse mouse = new Mouse();
    addMouseListener(mouse);
    addMouseMotionListener(mouse);
    addMouseWheelListener(mouse);

    timer = new Timer(updateMS, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        update(updateMS / 1000.0);
      }
    });
    timer.setRepeats(true);
    if (AUTO_ZOOM) timer.start();
  }

  public void setMaxIterate(int iterate) {
    this.maxIterate = iterate;
    fractal.maxIterate = iterate;
  }

  public void update(double dt) {
    zoom(zoomPoint, dt * 0.4);
    repaint();
  }

  public void zoom(Point2D.Double p, double amt) {
    final double width = screen.maxX - screen.minX, height = screen.maxY - screen.minY;
    double px = (p.x - screen.minX) / (width), py = (p.y - screen.minY) / (height);
    screen.minX += px * amt * width;
    screen.maxX -= (1 - px) * amt * width;
    screen.minY += py * amt * height;
    screen.maxY -= (1 - py) * amt * height;
  }

  public void offset(Point2D.Double offset) {
    screen.minX += offset.x; screen.maxX += offset.x;
    screen.minY += offset.y; screen.maxY += offset.y;
  }

  private class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    public void mouseWheelMoved(MouseWheelEvent e) {
      zoom(getMousePoint(e), -0.01 * (double)e.getWheelRotation());
      repaint();
    }
    public void mouseDragged(MouseEvent e) {
      Point mouse = e.getPoint();
      double sx = (screen.maxX - screen.minX) / getWidth(), sy = (screen.maxY - screen.minY) / getHeight();
      if (prevMouse == null) return;
      offset(new Point2D.Double((prevMouse.x - mouse.x) * sx, -(prevMouse.y - mouse.y) * sy));
      prevMouse = mouse;
      repaint();
    }
    public void mouseMoved(MouseEvent e) {
      if (!showRecursion) return;
      Point2D.Double mouse = getMousePoint(e);
      ComplexNumber num = new ComplexNumber(mouse.x, mouse.y);
      selectedPoint = num;
      repaint();
    }
    public void mouseClicked(MouseEvent e) {
      Point2D.Double mouse = getMousePoint(e);
      ComplexNumber num = new ComplexNumber(mouse.x, mouse.y);
      // debug:
      int it = fractal.iterate(num);
      double itDec = (double)it / maxIterate;
      System.out.println("(" + mouse.x + ", " + mouse.y + "): " + it + " | " + itDec);
      System.out.println("  " + gradient.color(itDec));
    }
    public void mousePressed(MouseEvent e) {
      prevMouse = e.getPoint();
    }
    public void mouseReleased(MouseEvent e) {
      prevMouse = null;
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {
      selectedPoint = null;
      prevMouse = null;
      if (showRecursion) repaint();
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Add anti-aliasing
    // ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    for (int x = 0; x <= getWidth(); x += resolution) {
      for (int y = 0; y <= getHeight(); y += resolution) {
        // Get center point
        Point2D.Double p = convertPoint(x + resolution/2.0, y + resolution/2.0);
        ComplexNumber num = new ComplexNumber(p.x, p.y);
        double iterate = (double) fractal.iterate(num) / maxIterate;
        Color color = gradient.color(iterate);

        g.setColor(color);
        g.fillRect(x, y, resolution, resolution);

        // debug:
        // int per = (int)(100 * ((double)x / getWidth()));
        // System.out.print("\rDrawing (" + x + ", " + y + ")... " + per + "%");
      }
    }

    if (!showRecursion) return;
    if (selectedPoint == null) return;
    int i = 0;
    ComplexNumber num = selectedPoint;
    while (i < maxIterate && !fractal.outside(num)) {
      ComplexNumber next = fractal.iterateOnce(num, selectedPoint);
      Point p1 = unconvertPoint(num), p2 = unconvertPoint(next);
      g.setColor(Color.WHITE);
      g.drawLine(p1.x, p1.y, p2.x, p2.y);
      g.setColor(gradient.color((double)i / maxIterate));
      g.fillOval(p2.x - 4, p2.y - 4, 8, 8);
      num = next;
      i++;
    }
  }

  Point2D.Double getMousePoint(MouseEvent e) {
    return convertPoint(e.getX(), e.getY());
  }

  Point2D.Double convertPoint(double x, double y) {
    return new Point2D.Double(
      x / getWidth() * (screen.maxX - screen.minX) + screen.minX,
      y / getHeight() * (screen.minY - screen.maxY) + screen.maxY
    );
  }

  Point unconvertPoint(ComplexNumber num) {
    return new Point(
      (int)((num.getReal() - screen.minX) / (screen.maxX - screen.minX) * getWidth()),
      (int)((num.getImaginary() - screen.maxY) / (screen.minY - screen.maxY) * getHeight())
    );
  }
}