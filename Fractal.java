/**
 * This class holds the recursive algorithm for the
 * Mandelbrot set algorithm. It utilized the method
 * of computing x^2 + x for complex numbers.
 */
public class Fractal {
  public int maxIterate;

  public Fractal(int maxIterate) {
    this.maxIterate = maxIterate;
  }
  
  public int iterate(ComplexNumber num) {
    return iterate(num, num, 0);
  }

  private int iterate(ComplexNumber num, ComplexNumber z, int count) {
    if (count >= maxIterate) return count;
    ComplexNumber next = iterateOnce(num, z);
    if (outside(next)) return count;
    try {
      return iterate(next, z, count+1);
    } catch (StackOverflowError e) {
      return count;
    }
  }

  public ComplexNumber iterateOnce(ComplexNumber num, ComplexNumber z) {
    return num.multiply(num).add(z);
  }

  public boolean outside(ComplexNumber num) {
    return getMagnitude(num) >= 2.82842712d;
  }

  private double getMagnitude(ComplexNumber num) {
    return Math.sqrt(num.getReal() * num.getReal() + num.getImaginary() * num.getImaginary());
  }

  public boolean isInRange(double value, double min, double max) {
    return value<=max && value>=min;
  }
}