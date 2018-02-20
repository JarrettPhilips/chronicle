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


        //This adds the element I want to test
        //EntryButton eb = new EntryButton();
    }
}
