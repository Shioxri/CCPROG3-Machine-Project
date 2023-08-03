package View;



import javax.swing.*;
import java.awt.*;

/**
 * This class represents the graphical user interface (GUI) for the regular vending machine menu.
 * It provides options for the user to either use the vending machine or enter maintenance mode.
 */
public class RegularVMMenu {
    // Declare the GUI components
    private JFrame frame;
    private JButton UseVMButton;
    private JButton maintenanceButton;
    private JButton backButton;

    /**
     * Constructor for the RegularVMMenu class.
     * Initializes the GUI by calling the init() method.
     */
    public RegularVMMenu() {
        init();
    }

    /**
     * Initializes the graphical user interface (GUI) components and sets up the regular vending machine menu view.
     * Creates various panels and adds components to them, sets their properties, and arranges them using layout managers.
     * The GUI is composed of background images, labels, and buttons.
     */
    private void init()
    {
        UseVMButton = new JButton();
        maintenanceButton = new JButton();
        backButton = new JButton();
        // Declarations
        frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();


        // Images
        ImageIcon titleIcon = new ImageIcon("assets/trueregvm.png");
        ImageIcon sampleIcon = new ImageIcon("assets/Maintenance.png");
        ImageIcon sampleBG = new ImageIcon("assets/VM.gif");
        ImageIcon cola = new ImageIcon("assets/orange.png");

        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panels
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Buttons

        UseVMButton.setBounds(100, 75, 300, 50);
        UseVMButton.setHorizontalAlignment(JButton.CENTER);
        UseVMButton.setIcon(cola);
        UseVMButton.setText("Use Vending Machine");
        UseVMButton.setHorizontalTextPosition(JButton.CENTER);


        maintenanceButton.setBounds(100, 175, 300, 50);
        maintenanceButton.setHorizontalAlignment(JButton.CENTER);
        maintenanceButton.setText("Enter Maintenance Mode");
        maintenanceButton.setHorizontalTextPosition(JButton.CENTER);
        maintenanceButton.setIcon(sampleIcon);

        backButton.setBounds(100, 275, 300, 50);
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.setText("Back");

        // Label
        titleLabel.setText("\u200E");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));

        // Panels
        titlePanel.setBackground(new Color(25, 25, 112, 123));
        titlePanel.setBounds(125, 100, 500, 150);
        titlePanel.setOpaque(true);
        titlePanel.add(titleLabel);

        selectionPanel.setBackground(new Color(25, 25, 112, 123));
        selectionPanel.setBounds(125, 250, 500, 400);
        selectionPanel.setLayout(null);
        selectionPanel.setOpaque(true);
        selectionPanel.add(UseVMButton);
        selectionPanel.add(maintenanceButton);
        selectionPanel.add(backButton);

        // Layered Pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(750, 750));
        layeredPane.add(bgpanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(1));
        layeredPane.add(selectionPanel, Integer.valueOf(1));

        // Frame
        frame.setTitle("Vending Machine");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(layeredPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Getter method to access the JFrame from other classes.
     *
     * @return The JFrame instance.
     */
    public JFrame getFrame() {
        return frame;
    }
    /**
     * Getter method to access the "Use Vending Machine" button from other classes.
     *
     * @return The "Use Vending Machine" button.
     */
    public JButton getUseVMButton() {
        return UseVMButton;
    }
    /**
     * Getter method to access the "Enter Maintenance Mode" button from other classes.
     *
     * @return The "Enter Maintenance Mode" button.
     */
    public JButton getMaintenanceButton() {
        return maintenanceButton;
    }
    /**
     * Getter method to access the "Back" button from other classes.
     *
     * @return The "Back" button.
     */
    public JButton getBackButton() {
        return backButton;
    }
}