import java.awt.Color; 

public class Gradient {
  public final Color[] colors;
  
  public Gradient(Color ...c) {
    colors = c; 
  }

  public Color color(double i) {
    if (i == 1) return colors[colors.length-1];
    int portion = (int) (i / (1.0 / (colors.length-1)));
    double newI = i % (1.0 / (colors.length-1)) * (colors.length-1);
    return color(colors[portion], colors[portion+1], newI);
  }

  static Color color(Color c1, Color c2, double i) {
    i = clamp(i);
    int r = (int)((1-i) * c1.getRed() + i * c2.getRed());
    int g = (int)((1-i) * c1.getGreen() + i * c2.getGreen());
    int b = (int)((1-i) * c1.getBlue() + i * c2.getBlue());
    return new Color(r, g, b);
  }
  
  private static double clamp(double i) {
    return i < 0 ? 0 : (i > 1 ? 1 : i);
  }
}

