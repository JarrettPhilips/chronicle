import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class WallpaperPanel extends JPanel{
    /*
        Variables
    */
    private HashMap<String, Color> colorPackage;

    /*
        Constructors
    */
    public WallpaperPanel(HashMap<String, Color> cp){
        colorPackage = cp;

        this.setBackground(colorPackage.get("secondaryColor"));
    }

    /*
        Functions
    */
}
