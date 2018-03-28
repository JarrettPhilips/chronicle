import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EntryButton extends JButton {
    /*
        Variables
    */
    private HashMap<String, Color> colorPackage;

    private EntryData entryData;

    private Dimension defaultSize = new Dimension(100, 50);

    private Font titleFont = new Font("Futura", Font.BOLD, 14);
    private Font dateFont = new Font("Futura", Font.PLAIN, 10);
    private Font ratingFont = new Font("Futura", Font.PLAIN, 10);

    JPanel infoPanel;
    JLabel titleLabel;
    JLabel dateLabel;
    JLabel ratingLabel;

    /*
        Constructors
    */
    public EntryButton(HashMap<String, Color> cp, EntryData ed){
        this.colorPackage = cp;
        this.entryData = ed;
        this.setSize(defaultSize);

        infoPanel = new JPanel();
        infoPanel.setBackground(colorPackage.get("secondaryColor"));
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        titleLabel = new JLabel(entryData.getTitleString());
        titleLabel.setForeground(colorPackage.get("primaryColor"));
        titleLabel.setFont(titleFont);
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(titleLabel, c);

        dateLabel = new JLabel(entryData.getDateString());
        dateLabel.setForeground(colorPackage.get("primaryColor"));
        dateLabel.setFont(dateFont);
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(dateLabel, c);

        ratingLabel = new JLabel(Integer.toString(entryData.getRating()));
        ratingLabel.setBackground(entryData.getHeaderColor());
        ratingLabel.setForeground(colorPackage.get("secondaryColor"));
        ratingLabel.setFont(ratingFont);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        infoPanel.add(ratingLabel, c);

        this.add(infoPanel);
    }

    /*
        Functions
    */
    //Toggles visual settings if the button has been selected
    public void buttonSelected(){
        infoPanel.setBackground(entryData.getHeaderColor());
        titleLabel.setForeground(colorPackage.get("secondaryColor"));
        dateLabel.setForeground(colorPackage.get("secondaryColor"));
    }

    //Toggles visual settings if the button has been unselected
    public void buttonUnselected(){
        infoPanel.setBackground(colorPackage.get("secondaryColor"));
        titleLabel.setForeground(colorPackage.get("primaryColor"));
        dateLabel.setForeground(colorPackage.get("primaryColor"));
    }
}
