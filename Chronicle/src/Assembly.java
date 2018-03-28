/*
    Assembly.java

    Is the frame for the UI. Holds all other components
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class Assembly extends JFrame {
    /*
        Variables
    */
    private HashMap<String, Color> colorPackage = new HashMap<String, Color>();

    private Font logoFont = new Font("Futura", Font.BOLD, 40);
    private Font buttonFont = new Font("Futura", Font.PLAIN, 20);

    private Dimension defaultSize = new Dimension(600, 400);

    private JPanel contentPanel;
    private JPanel auxillaryPanel;
    private JPanel controlPanel;

    /*
        Constructors
    */
    public Assembly(HashMap<String, Color> cp){
        //Sets up the JFrame settings
        colorPackage = cp;
        this.setSize(defaultSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setTitle("Chronicle");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        GridBagConstraints c = new GridBagConstraints();

        //Adds other components
        contentPanel = createContentPanel();
        controlPanel = createButtonPanel();
        auxillaryPanel = createAuxillaryPanel();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        this.add(controlPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(auxillaryPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        this.add(contentPanel, c);

        //Updates the frame
        this.revalidate();
        this.repaint();
    }

    /*
        Functions
    */
    private JPanel createContentPanel(){
        JPanel cp = new JPanel();
        cp.setBackground(new Color(0, 255, 0));
        return cp;
    }

    private JPanel createAuxillaryPanel(){
        JPanel ap = new JPanel();
        ap.setBackground(new Color(0,0,255));
        return ap;
    }

    private JPanel createButtonPanel(){
        JPanel cp = new JPanel();
        cp.setBackground(colorPackage.get("primaryColor"));
        cp.setBorder(new EmptyBorder(20, 20, 20, 20));
        cp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel logoLabel = new JLabel("Chronicle");
        logoLabel.setBackground(colorPackage.get("primaryColor"));
        logoLabel.setForeground(colorPackage.get("secondaryColor"));
        logoLabel.setFont(logoFont);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        cp.add(logoLabel, c);

        JButton createEntryButton = new JButton("Add");
        createEntryButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        createEntryButton.setBackground(colorPackage.get("primaryColor"));
        createEntryButton.setForeground(colorPackage.get("secondaryColor"));
        createEntryButton.setFont(buttonFont);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        cp.add(createEntryButton, c);

        JButton editEntryButton = new JButton("Edit");
        editEntryButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        editEntryButton.setBackground(colorPackage.get("primaryColor"));
        editEntryButton.setForeground(colorPackage.get("secondaryColor"));
        editEntryButton.setFont(buttonFont);
        c.gridx = 1;
        c.gridy = 1;
        cp.add(editEntryButton, c);

        return cp;
    }

    private JPanel createLogoPanel(){
        JPanel panel = new JPanel();
        return panel;
    }
}
