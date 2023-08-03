package View;

import javax.swing.*;
import java.awt.*;

/**
 * SpecialVMMenu class represents a special menu for a vending machine GUI.
 * This menu provides options to either use the vending machine or enter maintenance mode.
 */
public class SpecialVMMenu {
    private JFrame frame = new JFrame();
    private JButton UseVMButton = new JButton();
    private JButton maintenanceButton = new JButton();
    private JButton backButton = new JButton();

    // Constructor to initialize the special menu.
    public SpecialVMMenu() {
        init();
    }

    /**
     * Initializes the Special Menu GUI components.
     * This method sets up various panels and adds components to them, sets their properties,
     * and arranges them using layout managers. The GUI is composed of background images, labels,
     * buttons, and panels.
     */
    public void init() {

        // Declarations for labels and panels.
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();

        // Images used in the GUI.
        ImageIcon titleIcon = new ImageIcon("assets/SPECIALVM1.png");
        ImageIcon sampleIcon = new ImageIcon("assets/Maintenance.png");
        ImageIcon sampleBG = new ImageIcon("assets/VM.gif");
        ImageIcon cola = new ImageIcon("assets/orangeJuice.png");

        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panel to contain the background image.
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Setting up the "Use Vending Machine" button.
        UseVMButton.setBounds(100, 75, 300, 50);
        UseVMButton.setHorizontalAlignment(JButton.CENTER);
        UseVMButton.setIcon(cola);
        UseVMButton.setText("Use Vending Machine");
        UseVMButton.setHorizontalTextPosition(JButton.CENTER);

        // Setting up the "Enter Maintenance Mode" button.
        maintenanceButton.setBounds(100, 175, 300, 50);
        maintenanceButton.setHorizontalAlignment(JButton.CENTER);
        maintenanceButton.setText("Enter Maintenance Mode");
        maintenanceButton.setHorizontalTextPosition(JButton.CENTER);
        maintenanceButton.setIcon(sampleIcon);

        // Setting up the "Back" button.
        backButton.setBounds(100, 275, 300, 50);
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.setText("Back");

        // Label to display the title with an icon and custom styling.
        titleLabel.setText("\u200E");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));

        // Panel for the title with a translucent background.
        titlePanel.setBackground(new Color(25, 25, 112, 123));
        titlePanel.setBounds(125, 100, 500, 150);
        titlePanel.setOpaque(true);
        titlePanel.add(titleLabel);

        // Panel for the buttons with a translucent background.
        selectionPanel.setBackground(new Color(25, 25, 112, 123));
        selectionPanel.setBounds(125, 250, 500, 400);
        selectionPanel.setLayout(null);
        selectionPanel.setOpaque(true);
        selectionPanel.add(UseVMButton);
        selectionPanel.add(maintenanceButton);
        selectionPanel.add(backButton);

        // Layered Pane to manage overlapping components.
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(750, 750));
        layeredPane.add(bgpanel, Integer.valueOf(0)); // Background image at bottom layer (z-index 0).
        layeredPane.add(titlePanel, Integer.valueOf(1)); // Title panel at the upper layer (z-index 1).
        layeredPane.add(selectionPanel, Integer.valueOf(1)); // Button panel at the upper layer (z-index 1).

        // Frame configuration.
        frame.setTitle("Vending Machine");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(layeredPane); // Set the layered pane as the content pane.
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Getter method to access the main JFrame of the Special Menu.
     * @return The main JFrame object representing the Special Menu.
     */
    public JFrame getFrame() {
        return frame;
    }
    /**
     * Getter method to access the "Use Vending Machine" button of the Special Menu.
     * @return The "Use Vending Machine" button object.
     */
    public JButton getUseVMButton() {
        return UseVMButton;
    }
    /**
     * Getter method to access the "Enter Maintenance Mode" button of the Special Menu.
     * @return The "Enter Maintenance Mode" button object.
     */
    public JButton getMaintenanceButton() {
        return maintenanceButton;
    }
    /**
     * Getter method to access the "Back" button of the Special Menu.
     * @return The "Back" button object.
     */
    public JButton getBackButton() {
        return backButton;
    }
}
