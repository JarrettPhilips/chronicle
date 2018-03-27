import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Assembly extends JFrame {
    /*
        Variables
    */
    Color primaryColor;
    Color secondaryColor;

    Font logoFont = new Font("Futura", Font.BOLD, 40);
    Font buttonFont = new Font("Futura", Font.PLAIN, 20);

    Dimension defaultSize = new Dimension(600, 400);

    JPanel contentPanel;
    JPanel auxillaryPanel;
    JPanel controlPanel;

    /*
        Constructors
    */
    public Assembly(Color primaryColor, Color secondaryColor){
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        this.setSize(defaultSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setTitle("Chronicle");
        GridBagConstraints c = new GridBagConstraints();

        contentPanel = createContentPanel();
        controlPanel = createControlPanel();
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

    private JPanel createControlPanel(){
        JPanel cp = new JPanel();
        cp.setBackground(primaryColor);
        cp.setBorder(new EmptyBorder(20, 20, 20, 20));
        cp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel logoLabel = new JLabel("Chronicle");
        logoLabel.setBackground(primaryColor);
        logoLabel.setForeground(secondaryColor);
        logoLabel.setFont(logoFont);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        cp.add(logoLabel, c);

        JButton createEntryButton = new JButton("Add");
        createEntryButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        createEntryButton.setBackground(primaryColor);
        createEntryButton.setForeground(secondaryColor);
        createEntryButton.setFont(buttonFont);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        cp.add(createEntryButton, c);

        JButton editEntryButton = new JButton("Edit");
        editEntryButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        editEntryButton.setBackground(primaryColor);
        editEntryButton.setForeground(secondaryColor);
        editEntryButton.setFont(buttonFont);
        c.gridx = 1;
        c.gridy = 1;
        cp.add(editEntryButton, c);

        return cp;
    }
}
