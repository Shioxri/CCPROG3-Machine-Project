package View;

import javax.swing.*;
import java.awt.*;

public class RegularVMMenu {
    public static void main(String[] Args) { // this is temporary and for testing only, move to driver

        //Declarations
        JFrame frame = new JFrame(); // declaration for the main frame
        JLabel titleLabel = new JLabel(); // declaration for the Title
        JPanel titlePanel = new JPanel(); // Declaration for the title Panel
        JPanel selectionPanel = new JPanel(); // declaration for the selection panel
        JButton regularVMbutton = new JButton(); // declaration for regularVM button
        JButton specialVMbutton = new JButton(); // declaration for specialVM button
        JButton exitButton = new JButton(); // declaration for exit button

        //Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png"); // image
        ImageIcon titleIcon = new ImageIcon("resized.jpg"); // title icon must be <= 400x100
        ImageIcon sampleIcon = new ImageIcon("pngtree-pixel-art-cherry-icon-design-vector-png-image_6122197.png");
        ImageIcon sampleBG = new ImageIcon("gifm.gif");


        //buttons
        regularVMbutton.setBounds(100,75,300,50);
        regularVMbutton.setHorizontalAlignment(JButton.CENTER);
        regularVMbutton.setText("Use Vending Machine");
        regularVMbutton.addActionListener(e -> System.out.println("show regular VM"));

        specialVMbutton.setBounds(100,175,300,50);
        specialVMbutton.setHorizontalAlignment(JButton.CENTER);
        specialVMbutton.setText("Open Maintenance Mode");
        specialVMbutton.setHorizontalTextPosition(JButton.CENTER);
        specialVMbutton.setIcon(sampleIcon);
        specialVMbutton.addActionListener(e -> System.out.println("show special VM"));

        exitButton.setBounds(100,275,300,50);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Back");
        exitButton.addActionListener(e -> System.exit(0));

        //Label
        titleLabel.setText("Regular Vending Machine");// adds label of text
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // aligns label to center
        titleLabel.setVerticalAlignment(JLabel.CENTER); // aligns label to bottom
        titleLabel.setForeground(Color.white);// font color for label
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD,20)); // sets font style and size for the label

        //Panels
        titlePanel.setBackground(Color.gray); // sets panel bg color
        titlePanel.setBounds(125,0,500,150); // sets panel size and location

        selectionPanel.setBackground(Color.lightGray);
        selectionPanel.setBounds(125,150,500,400);
        selectionPanel.setLayout(null);

        //Frame
        frame.setTitle("Vending Machine"); // frame title
        frame.setIconImage(fruitIcon.getImage()); // sets the icon image
        frame.setResizable(true);// prevents resizing
        frame.setVisible(true);// sets the frame to be visible
        frame.setSize(750,750);// size or dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// makes pressing x stop the program
        frame.getContentPane().setBackground(new Color(36,37,38));// sets the background color
        frame.setLayout(null); // sets the frame layout to null

        //Additions
        titlePanel.add(titleLabel); // adds title label to title panel
        selectionPanel.add(regularVMbutton);
        selectionPanel.add(specialVMbutton);
        selectionPanel.add(exitButton);
        frame.add(titlePanel); // adds title panel to main frame
        frame.add(selectionPanel);
    }

}
