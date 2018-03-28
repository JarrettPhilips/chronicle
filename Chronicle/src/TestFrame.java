import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TestFrame {
    public static void main(String args[]){
        //This creates the test panel
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //This adds the element I want to test
        //EntryButton eb = new EntryButton();
        /*
        EditingPanel e = new EditingPanel();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 1.0;
        frame.add(e, c);
        */
    }
}
