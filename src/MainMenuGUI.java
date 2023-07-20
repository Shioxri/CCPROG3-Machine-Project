import javax.swing.*;
import java.awt.*;

public class MainMenuGUI {

    public static void main(String[] Args) {

        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JPanel mainPanel = new JPanel();

        ImageIcon fruitIcon = new ImageIcon("323528583_464396995737157_7527402304455667779_n.png");

        frame.setTitle("Vending Machine");
        frame.setIconImage(fruitIcon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(36,37,38));
        label.setText("Vending Machine Main Menu");
        frame.add(label);
    }


}
