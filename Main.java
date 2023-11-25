// import java.awt.*;
import javax.swing.*;

public class Main {
  private static void createAndShowGUI() {
    JFrame jFrame = new JFrame("Ellipse Reflections Demonstration");
    jFrame.setSize(800, 800);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jFrame.add(new ParentPanel());
    jFrame.setVisible(true);
  }
    
  public static void main(String[] args) {
    createAndShowGUI();
  }
}
