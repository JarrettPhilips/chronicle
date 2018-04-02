import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class EntryButton extends JButton {
    /*
        Variables
    */
    private HashMap<String, Color> colorPackage;

    private EntryData entryData;

    private int buttonHeight = 50;
    private Dimension defaultSize = new Dimension(100, buttonHeight);

    private Font titleFont = new Font("Futura", Font.BOLD, 14);
    private Font dateFont = new Font("Futura", Font.PLAIN, 10);
    private Font ratingFont = new Font("Futura", Font.PLAIN, 18);

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
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(0,0,0,0));

        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(255,128,0));
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        titleLabel = new JLabel(entryData.getTitleString());
        titleLabel.setForeground(colorPackage.get("primaryColor"));
        titleLabel.setFont(titleFont);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        infoPanel.add(titleLabel, c);

        dateLabel = new JLabel(entryData.getDateString());
        dateLabel.setForeground(colorPackage.get("primaryColor"));
        dateLabel.setFont(dateFont);
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(dateLabel, c);

        JPanel colorBarPanel = new JPanel();
        colorBarPanel.setBackground(entryData.getHeaderColor());
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.add(colorBarPanel, c);

        ratingLabel = createRatingLabel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        infoPanel.add(ratingLabel, c);

        this.add(infoPanel);
    }

    /*
        Functions
    */
    private JLabel createRatingLabel(){
        JLabel ratingLabel = new JLabel();
        ratingLabel.setOpaque(true);
        ratingLabel.setSize(buttonHeight, buttonHeight);
        ratingLabel.setBackground(new Color(0,0,0));
        ratingLabel.setText(Integer.toString(entryData.getRating()));
        ratingLabel.setForeground(colorPackage.get("whiteColor"));
        ratingLabel.setFont(ratingFont);

        return ratingLabel;
    }

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

    public EntryData getEntryData(){return entryData;}
}
