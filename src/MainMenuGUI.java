import javax.swing.*;
import java.awt.*;

public class MainMenuGUI {

    public static void main(String[] Args) { // this is temporary and for testing only, move to driver

        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JPanel mainPanel = new JPanel();

        ImageIcon fruitIcon = new ImageIcon("323528583_464396995737157_7527402304455667779_n.png"); // image

        frame.setTitle("Vending Machine"); // frame title
        frame.setIconImage(fruitIcon.getImage()); // sets the icon image
        frame.setResizable(false);// prevents resizing
        frame.setVisible(true);// sets the frame to be visible
        frame.setSize(500,500);// size or dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes pressing x stop the program
        frame.getContentPane().setBackground(new Color(36,37,38));// sets the background color
        label.setText("Vending Machine Main Menu");// adds label of text
        label.setForeground(Color.white);// font color for label
        label.setFont(new Font("Century Gothic", Font.BOLD,20)); // sets font style and size for the label
        frame.add(label);// adds the label to the frame
    }


}
