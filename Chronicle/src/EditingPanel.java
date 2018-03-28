import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class EditingPanel extends JPanel{
    /*
        Variables
    */
    HashMap<String, Color> colorPackage;
    Color backgroundColor;

    Dimension desiredSize = new Dimension(600, 400);

    //Components
    JPanel headerPanel;
    JPanel textPanel;

    JTextArea textArea;
    JTextField titleTextField;
    JTextField dateTextField;

    JButton saveButton;

    /*
        Constructors
    */
    public EditingPanel(HashMap<String, Color> cp){
        this.colorPackage = cp;
        backgroundColor = new Color(colorPackage.get("secondaryColor").getRed() - 10, colorPackage.get("secondaryColor").getGreen() - 10, colorPackage.get("secondaryColor").getBlue() - 10);
        this.setBorder(new EmptyBorder(40, 40, 40, 40));
        this.setLayout(new GridBagLayout());
        this.setBackground(colorPackage.get("secondaryColor"));
        GridBagConstraints c = new GridBagConstraints();

        headerPanel = createHeaderPanel();
        textPanel = createTextPanel();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(textPanel, c);
    }

    /*
        Primary Functions
    */
    private JPanel createHeaderPanel(){
        JPanel hp = new JPanel();
        hp.setBackground(colorPackage.get("secondaryColor"));
        hp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        titleTextField = new JTextField();
        titleTextField.setText("Title");
        titleTextField.setBackground(backgroundColor);
        titleTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.ipadx = 10;
        c.ipady = 10;
        hp.add(titleTextField, c);

        dateTextField = new JTextField();
        dateTextField.setText("YYYYMMDD");
        dateTextField.setBackground(backgroundColor);
        dateTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        hp.add(dateTextField, c);

        saveButton = new JButton("Save Entry");
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        hp.add(saveButton, c);

        return hp;
    }

    private JPanel createTextPanel(){
        JPanel tp = new JPanel();
        tp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        textArea = new JTextArea();
        textArea.setBackground(backgroundColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        tp.add(textArea, c);

        return tp;
    }

    private void saveNewEntry(){

    }

    private void saveEditedEntry(){

    }

    /*
        Auxillary Functions
    */
}
