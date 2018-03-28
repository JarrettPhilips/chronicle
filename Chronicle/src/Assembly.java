/*
    Assembly.java

    Is the frame for the UI. Holds all other components
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel buttonPanel;
    private JPanel logoPanel;

    private JButton createEntryButton;
    private JButton editEntryButton;
    private JButton helpButton;

    private boolean createEntryButtonToggled = false;
    private boolean editEntryButtonToggled = false;
    private boolean helpButtonToggled = false;

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
        buttonPanel = createButtonPanel();
        auxillaryPanel = createAuxillaryPanel();
        logoPanel = createLogoPanel();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        this.add(logoPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        this.add(auxillaryPanel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.0;
        this.add(buttonPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
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
        //cp.setBorder(new EmptyBorder(20, 20, 20, 20));
        cp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        createEntryButton = new JButton("Add");
        createEntryButton.setBorder(new EmptyBorder(15, 22, 15, 22));
        createEntryButton.setBackground(colorPackage.get("primaryColor"));
        createEntryButton.setForeground(colorPackage.get("secondaryColor"));
        createEntryButton.setFont(buttonFont);
        createEntryButton.setOpaque(true);
        createEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(createEntryButtonToggled){
                    resetButtons();
                    createEntryButtonToggled = false;
                    createEntryButton.setBackground(colorPackage.get("primaryColor"));
                    createEntryButton.setForeground(colorPackage.get("secondaryColor"));
                } else {
                    resetButtons();
                    createEntryButtonToggled = true;
                    createEntryButton.setBackground(colorPackage.get("secondaryColor"));
                    createEntryButton.setForeground(colorPackage.get("primaryColor"));
                }
            }
        } );
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        cp.add(createEntryButton, c);

        editEntryButton = new JButton("Edit");
        editEntryButton.setBorder(new EmptyBorder(15, 22, 15, 22));
        editEntryButton.setBackground(colorPackage.get("primaryColor"));
        editEntryButton.setForeground(colorPackage.get("secondaryColor"));
        editEntryButton.setFont(buttonFont);
        editEntryButton.setOpaque(true);
        editEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(editEntryButtonToggled){
                    resetButtons();
                    editEntryButtonToggled = false;
                    editEntryButton.setBackground(colorPackage.get("primaryColor"));
                    editEntryButton.setForeground(colorPackage.get("secondaryColor"));
                } else {
                    resetButtons();
                    editEntryButtonToggled = true;
                    editEntryButton.setBackground(colorPackage.get("secondaryColor"));
                    editEntryButton.setForeground(colorPackage.get("primaryColor"));
                }
            }
        } );
        c.gridx = 1;
        c.gridy = 1;
        cp.add(editEntryButton, c);

        helpButton = new JButton("Help");
        helpButton.setBorder(new EmptyBorder(15, 22, 15, 22));
        helpButton.setBackground(colorPackage.get("primaryColor"));
        helpButton.setForeground(colorPackage.get("secondaryColor"));
        helpButton.setFont(buttonFont);
        helpButton.setOpaque(true);
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(helpButtonToggled){
                    resetButtons();
                    helpButtonToggled = false;
                    helpButton.setBackground(colorPackage.get("primaryColor"));
                    helpButton.setForeground(colorPackage.get("secondaryColor"));
                } else {
                    resetButtons();
                    helpButtonToggled = true;
                    helpButton.setBackground(colorPackage.get("secondaryColor"));
                    helpButton.setForeground(colorPackage.get("primaryColor"));
                }
            }
        } );
        c.gridx = 2;
        c.gridy = 1;
        cp.add(helpButton, c);

        return cp;
    }

    private void resetButtons(){
        createEntryButton.setBackground(colorPackage.get("primaryColor"));
        createEntryButton.setForeground(colorPackage.get("secondaryColor"));
        editEntryButton.setBackground(colorPackage.get("primaryColor"));
        editEntryButton.setForeground(colorPackage.get("secondaryColor"));
        helpButton.setBackground(colorPackage.get("primaryColor"));
        helpButton.setForeground(colorPackage.get("secondaryColor"));

        createEntryButtonToggled = false;
        editEntryButtonToggled = false;
        helpButtonToggled = false;
    }

    private JPanel createLogoPanel(){
        JPanel lp = new JPanel();
        lp.setBackground(colorPackage.get("primaryColor"));
        lp.setBorder(new EmptyBorder(20, 20, 20, 20));
        lp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel logoLabel = new JLabel("Chronicle");
        logoLabel.setBackground(colorPackage.get("primaryColor"));
        logoLabel.setForeground(colorPackage.get("secondaryColor"));
        logoLabel.setFont(logoFont);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        lp.add(logoLabel, c);

        return lp;
    }
}
