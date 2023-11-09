public class ComplexNumber {
  private double real, imaginary;

  public ComplexNumber(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public ComplexNumber add(ComplexNumber cn) {
    return new ComplexNumber(real + cn.real, imaginary + cn.imaginary);
  }

  public ComplexNumber multiply(ComplexNumber cn) {
    return new ComplexNumber(real * cn.real - imaginary * cn.imaginary, real * cn.imaginary + imaginary * cn.real);
  }

  public double getReal() {
    return real;
  }

  public double getImaginary() {
    return imaginary;
  }

  public String toString() {
    // if (imaginary == 0) return Integer.toString(real);
    // if (real == 0) return imaginary + "i";
    return real + (imaginary < 0 ? " - " : " + ") + Math.abs(imaginary) + "i";
  }
}