import javax.swing.*;
import java.awt.*;

public class MainMenuGUI {

    public static void main(String[] Args) { // this is temporary and for testing only, move to driver

        JFrame frame = new JFrame(); // declaration for the main frame
        JLabel titleLabel = new JLabel(); // declaration for the Title
        JPanel titlePanel = new JPanel(); // Declaration for the title Panel
        JPanel selectionPanel = new JPanel(); // declaration for the selection panel

        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png"); // image
        ImageIcon titleIcon = new ImageIcon("resized.jpg"); // title icon must be <= 400x100

        titleLabel.setText("Vending Machine Main Menu");// adds label of text
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // aligns label to center
        titleLabel.setVerticalAlignment(JLabel.CENTER); // aligns label to bottom
        titleLabel.setForeground(Color.white);// font color for label
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD,20)); // sets font style and size for the label


        titlePanel.setBackground(Color.gray); // sets panel bg color
        titlePanel.setBounds(125,0,500,150); // sets panel size and location

        selectionPanel.setBackground(Color.lightGray);
        selectionPanel.setBounds(125,150,500,400);
        selectionPanel.setLayout(null);

        frame.setTitle("Vending Machine"); // frame title
        frame.setIconImage(fruitIcon.getImage()); // sets the icon image
        frame.setResizable(false);// prevents resizing
        frame.setVisible(true);// sets the frame to be visible
        frame.setSize(750,750);// size or dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes pressing x stop the program
        frame.getContentPane().setBackground(new Color(36,37,38));// sets the background color
        frame.setLayout(null); // sets the frame layout to null
        titlePanel.add(titleLabel); // adds title label to title panel
        frame.add(titlePanel); // adds title panel to main frame
        frame.add(selectionPanel);
    }


}
