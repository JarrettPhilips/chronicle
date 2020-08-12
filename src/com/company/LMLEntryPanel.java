package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class LMLEntryPanel extends JPanel{
    /*
        Variables
    */
    private String entryDirectory;
    private String txtEntryDirectory;

    private Color primaryColor;
    private Color secondaryColor;
    private Color highColor;
    private Color medianColor;
    private Color lowColor;

    private Font textFont = new Font("Palatino", Font.PLAIN, 16);
    private Font titleFont = new Font("Futura", Font.BOLD, 40);
    private Font ratingFont = new Font("Geneva", Font.BOLD, 80);
    private Font dateFont = new Font("Geneva", Font.BOLD, 30);

    private Dimension headerMinimumSize = new Dimension(0, 125);
    private Dimension headerMaximumSize = new Dimension(Integer.MAX_VALUE, 125);

    private ArrayList<String> directoryOfPhotos = new ArrayList<String>();

    private int rating = -1; //Int from 1-10 rating how the day was, used for visualization
    private int dateInt = -1; //YYYYMMDD
    private String dateString = "-1";

    /*
        Constructors
    */
    public LMLEntryPanel(String entryDirectory, Color pc, Color sc, Color hc, Color lc, Color mc){
        this.entryDirectory = entryDirectory;
        this.primaryColor = pc;
        this.secondaryColor = sc;
        this.highColor = hc;
        this.lowColor = lc;
        this.medianColor = mc;
        this.setPreferredSize(new Dimension(600, 400));

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JScrollPane body = createBody();
        JPanel header = createHeader();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        this.add(header, c);
        c.gridx ++;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(body, c);
        c.gridy ++;
    }

    /*
        Functions
    */
    private JScrollPane createBody(){
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        editorPane.setFont(textFont);
        editorPane.setEditable(false);
        editorPane.setBorder(new EmptyBorder(60, 60, 60, 60));
        String content = getFileContent();
        content = processFileContent(content);
        editorPane.setText(content);
        editorPane.setBackground(secondaryColor);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        return scrollPane;
    }

    private String getFileContent(){
        try {
            File[] files = txtFinder(entryDirectory);
            File file = files[0];
            txtEntryDirectory = files[0].getName();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = "<br>";

            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }

                return stringBuilder.toString();
            } finally {
                reader.close();
            }
        } catch(IOException e){
            System.out.println("Error: LMLEntryPanel/getFileContents: IOException");
        }

        return "-1";
    }

    //Transforms basic markdown to HTML readable by the editorPane
    private String processFileContent(String content){
        boolean tagOpen = false;
        int photoStartIndex = 0;
        int ratingStartIndex = 0;
        for(int i = 0; i != content.length() - 1; i ++){
            String doubleCharKey = content.substring(i, i + 2);

            if(doubleCharKey.equals("**")){
                if(tagOpen){
                    content = content.substring(0, i) + "</i>" + content.substring(i + 2, content.length());
                    tagOpen = false;
                } else {
                    content = content.substring(0, i) + "<i>" + content.substring(i + 2, content.length());
                    tagOpen = true;
                }
            } else if(doubleCharKey.equals("<<")){
                photoStartIndex = i;
            } else if(doubleCharKey.equals(">>")){
                String photoDirectory = content.substring(photoStartIndex + 2, i);
                photoDirectory = entryDirectory + "/" + photoDirectory;
                Dimension panelSize = this.getSize();
                System.out.println(panelSize);
                if(panelSize.width == 0 || panelSize.height == 0){
                    panelSize = this.getPreferredSize();
                }
                int scaledImageWidth = panelSize.width;
                int scaledImageHeight = (panelSize.width / 4) * 3; //If unable to access photo, will assume 4:3 standard photo
                try {
                    BufferedImage bi = ImageIO.read(new File(photoDirectory));
                    scaledImageHeight = (panelSize.width * bi.getHeight()) / bi.getWidth();
                } catch(IOException e){
                    System.out.println("Error: LMLEntryPanel/processfilecontents : unable to create buffered photo for dimensions");
                }
                String formatedPhotoTag = "<img src='file://"+photoDirectory+"' width="+scaledImageWidth+" height="+scaledImageHeight+"> </img>";
                content = content.substring(0, photoStartIndex) + formatedPhotoTag + content.substring(i + 2, content.length());
            } else if(doubleCharKey.equals("^^")){
                if(tagOpen){
                    String ratingString = content.substring(ratingStartIndex + 2, i);
                    int ratingInt = Integer.parseInt(ratingString);
                    if(ratingInt > 0 && ratingInt < 11){
                        rating = ratingInt;
                    }
                    content = content.substring(0, ratingStartIndex) + content.substring(i + 2, content.length());
                    tagOpen = false;
                } else {
                    ratingStartIndex = i;
                    tagOpen = true;
                }
            }
        }

        tagOpen = false;
        for(int i = 0; i != content.length(); i ++){
            String singleCharKey = content.substring(i, i + 1);

            if(singleCharKey.equals("*")){
                if(tagOpen){
                    content = content.substring(0, i) + "</b>" + content.substring(i + 1, content.length());
                    tagOpen = false;
                } else {
                    content = content.substring(0, i) + "<b>" + content.substring(i + 1, content.length());
                    tagOpen = true;
                }
            }
        }

        return content;
    }

    private JPanel createHeader(){
        //This section collects information and properly formats it
        File entryFolder = new File(entryDirectory);
        File[] listOfFiles = entryFolder.listFiles();

        dateInt = Integer.parseInt(entryFolder.getName());
        dateString = entryFolder.getName();
        int numberOfPhotos = 0;

        String date = entryFolder.getName();
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int monthInt = Integer.parseInt(month);
        month = months[monthInt - 1];

        String day = date.substring(6, 8);
        if(day.substring(0, 1).equals("0"))
            day = day.substring(1, 2);
        int dayInt = Integer.parseInt(day);
        if(dayInt == 1)
            day = day + "st";
        else if(dayInt == 2)
            day = day + "nd";
        else if(dayInt == 3)
            day = day + "rd";
        else
            day = day + "th";

        String dateString = month + " " + day + ", " + year;
        String title = "";
        String txtDirectory;

        for(File file : listOfFiles){
            String name = file.getName();
            String extension = name.substring(name.length() - 3);

            if(extension.equals("txt")){
                String titleUnedited = name.substring(0, name.length() - 4);
                title = titleUnedited.replace("_", " ");
                txtDirectory = entryDirectory + "/" + file.getName();
            } else {
                directoryOfPhotos.add(entryDirectory + "/" + file.getName());
                numberOfPhotos ++;
            }
        }

        //This section creates the header
        JPanel header = new JPanel();
        header.setMinimumSize(headerMinimumSize);
        header.setMaximumSize(headerMaximumSize);
        header.setBackground(primaryColor);
        header.setForeground(secondaryColor);
        header.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(secondaryColor);
        c.anchor = GridBagConstraints.WEST;
        header.add(titleLabel, c);
        JLabel dateLabel = new JLabel(dateString);
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(secondaryColor);
        c.gridx ++;
        header.add(dateLabel, c);

        //Spacer
        JLabel spacerLabel = new JLabel();
        c.gridx ++;
        c.gridy = 0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        header.add(spacerLabel, c);

        //Rating
        if(rating != -1) {
            JLabel ratingLabel = new JLabel(Integer.toString(rating));
            ratingLabel.setFont(ratingFont);
            ratingLabel.setForeground(secondaryColor);
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridx++;
            header.add(ratingLabel, c);
            header.setBackground(getEntryColor());
        }

        header.setBorder(new EmptyBorder(20, 60, 20, 60));

        return header;
    }

    private File[] txtFinder(String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".txt"); }
        } );

    }

    public void rescaleImages(){ //Called to adjust the image sizes to accommadate a new window size

    }

    public Color getEntryColor(){
        int r = 0;
        int g = 0;
        int b = 0;

        if(rating > 5){//Mixes median and high
            int highWeight = rating - 5;
            int medianWeight = 5 - (rating - 5);
            r = ((medianColor.getRed() * medianWeight) + (highColor.getRed() * highWeight)) / 5;
            g = ((medianColor.getGreen() * medianWeight) + (highColor.getGreen() * highWeight)) / 5;
            b = ((medianColor.getBlue() * medianWeight) + (highColor.getBlue() * highWeight)) / 5;
        } else if(rating <= 5){//Mixes median and low
            int medianWeight = rating - 1;
            int lowWeight = 5 - (rating - 1);
            r = ((medianColor.getRed() * medianWeight) + (lowColor.getRed() * lowWeight)) / 5;
            g = ((medianColor.getGreen() * medianWeight) + (lowColor.getGreen() * lowWeight)) / 5;
            b = ((medianColor.getBlue() * medianWeight) + (lowColor.getBlue() * lowWeight)) / 5;
        }

        Color entryColor;
        if(rating != -1){
            entryColor = new Color(r, g, b);
        } else {
            entryColor = secondaryColor;
        }

        return entryColor;
    }

    public String getTxtDirectory(){
        return entryDirectory + "/" + txtEntryDirectory;
    }

    public int getDate(){
        return dateInt;
    }

    public String getDateString(){
        return dateString;
    }

    /*
        Testing Main
    */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setMinimumSize(new Dimension(600, 400));
        //frame.add(new LMLEntryPanel("/Users/JarrettPhilips/Documents/Journals/20170616"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}