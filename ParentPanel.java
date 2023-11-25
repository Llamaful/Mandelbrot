import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParentPanel extends JPanel {
  public ParentPanel() {
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(700, 800));
    setLayout(new BorderLayout());

    Panel panel = new Panel();
    add(panel, BorderLayout.CENTER);

    JPanel settings = new JPanel();
    settings.setSize(700, 100);
    
    /*
    JButton reset = new JButton("Reset Zoom");
    reset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        panel.screen = new Screen(-2.5, 1.5, -2, 2);
        repaint();
      }
    });
    settings.add(reset);

    settings.add(new JLabel("Resolution:"));
    JTextField res = new JTextField(2);
    res.setText(Integer.toString(panel.resolution));
    res.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          panel.resolution = Integer.parseInt(res.getText());
          panel.repaint();
        } catch (NumberFormatException err) {}
      }
    });
    settings.add(res);

    settings.add(new JLabel("Max-Iteration:"));
    JTextField maxIter = new JTextField(4);
    maxIter.setText(Integer.toString(panel.maxIterate));
    maxIter.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          panel.setMaxIterate(Integer.parseInt(maxIter.getText()));
          panel.repaint();
        } catch (NumberFormatException err) {}
      }
    });
    settings.add(maxIter);

    settings.add(new JLabel("Show Recursion:"));
    JCheckBox showRecursion = new JCheckBox();
    showRecursion.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("hi");
        panel.showRecursion = showRecursion.isSelected();
      }
    });
    settings.add(showRecursion);

    settings.add(new JLabel("Presets:"));
    JComboBox<Presets> presets = new JComboBox<Presets>(Presets.values());
    presets.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          final Presets preset = (Presets)presets.getSelectedItem();
          panel.resolution = preset.res;
          res.setText(Integer.toString(preset.res));
          panel.setMaxIterate(preset.maxIter);
          maxIter.setText(Integer.toString(preset.maxIter));
          panel.showRecursion = preset.showRecursion;
          showRecursion.setSelected(preset.showRecursion);
          panel.repaint();
        } catch (NumberFormatException err) {}
      }
    });
    settings.add(presets);
    */

    add(settings, BorderLayout.SOUTH);
  }

  enum Presets {
    Default(1, 40, false),
    Fast(2, 200, false), 
    Detailed(2, 400, false), 
    UltraHigh(1, 500, false),
    Debug(2, 20, true);

    public final int res, maxIter;
    public final boolean showRecursion;
    Presets(int res, int maxIter, boolean showRecursion) {
      this.res = res; this.maxIter = maxIter; this.showRecursion = showRecursion;
    }
  }
}
