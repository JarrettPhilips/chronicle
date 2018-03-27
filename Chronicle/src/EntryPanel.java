import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class EntryPanel extends JPanel {
    /*
        Variables
    */
    private String titleString = "Morro Rock";
    private String dateString = "March 20th, 2017";
    /*
        Constructors
    */
    public EntryPanel(){

    }

    /*
        Functions
    */
    private JPanel createHeader(){
        JPanel header = new JPanel();
        Dimension headerSize = new Dimension(800, 200);

        JLabel titleLabel = new JLabel(titleString);
        JLabel dateLabel = new JLabel(dateString);

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_INDEXED);
        Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
        BufferedImageOp op = new ConvolveOp(kernel);
        bufferedImage = op.filter(bufferedImage, null);


        return header;
    }

    private void loadEntry(EntryData data){

    }
}
