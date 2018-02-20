import javax.swing.*;
import java.awt.*;

public class EntryButton extends JButton {
    /*
        Variables
    */
    Dimension defaultSize = new Dimension(100, 50);
    private Font titleFont = new Font("Futura", Font.BOLD, 14);
    Font dateFont = new Font("Futura", Font.PLAIN, 10);


    /*
        Constructors
    */
    public EntryButton(EntryData data){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel(data.getTitle());
        titleLabel.setFont(titleFont);
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(titleLabel, c);

        JLabel dateLabel = new JLabel(data.getDateString());
        dateLabel.setFont(dateFont);
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(dateLabel, c);

        this.add(infoPanel);
    }

    /*
        Functions
    */
}
