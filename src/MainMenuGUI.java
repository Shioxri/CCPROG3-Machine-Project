import javax.swing.*;
import java.awt.*;

public class MainMenuGUI {

    public static void main(String[] Args) { // this is temporary and for testing only, move to driver

        JFrame frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();

        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png"); // image

        titleLabel.setText("Vending Machine Main Menu");// adds label of text
        /*
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
         */
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setForeground(Color.white);// font color for label
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD,10)); // sets font style and size for the label


        titlePanel.setBackground(Color.gray);
        titlePanel.setBounds(100,0,500,250);
        titlePanel.setLayout(null);

        frame.setTitle("Vending Machine"); // frame title
        frame.setIconImage(fruitIcon.getImage()); // sets the icon image
        frame.setResizable(false);// prevents resizing
        frame.setVisible(true);// sets the frame to be visible
        frame.setSize(750,750);// size or dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes pressing x stop the program
        frame.getContentPane().setBackground(new Color(36,37,38));// sets the background color
        frame.setLayout(null);
        frame.add(titlePanel);
    }


}
