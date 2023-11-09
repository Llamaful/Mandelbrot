/**
 * This class describes a conversion between screen coordinates.
 * It gives ranges for x and y as double values.
 */
public class Screen {
  public double minX, maxX, minY, maxY;

  public Screen(double minX, double maxX, double minY, double maxY) {
    this.minX = minX; this.maxX = maxX;
    this.minY = minY; this.maxY = maxY;
  }
}