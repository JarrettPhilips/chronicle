/*
    EntryData.java

    Stores, manages, and partially processes the data for each entry
*/

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EntryData {
    /*
        Variables
    */
    private String titleString = "Title";
    private String dateString = "Date";

    private int dateInt = -1;
    private int rating = -1;

    private Color headerColor = new Color(255,255,255);

    private String entryDirectory = "-1";
    private String txtEntryDirectory = "-1";

    private ArrayList<String> directoryOfPhotos = new ArrayList<String>();

    private String entryText = "-1";

    /*
        Constructors
    */
    public EntryData(String entryDirectory, HashMap<String, Color> colorPackage){
        this.entryDirectory = entryDirectory;
        entryText = findContent();
        dateString = findDate();
        titleString = findTitle();
        rating = findRating(entryText);
        headerColor = calculateHeaderColor(colorPackage.get("secondaryColor"), colorPackage.get("highColor"), colorPackage.get("lowColor"), colorPackage.get("medianColor"));
    }

    /*
        Functions
    */

    private Color calculateHeaderColor(Color secondaryColor, Color highColor, Color lowColor, Color medianColor){
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

    private String findDate(){
        File entryFolder = new File(entryDirectory);

        dateInt = Integer.parseInt(entryFolder.getName());

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

        return month + " " + day + ", " + year;
    }

    private String findTitle(){
        String title = "";
        File entryFolder = new File(entryDirectory);
        File[] listOfFiles = entryFolder.listFiles();

        for(File file : listOfFiles){
            String name = file.getName();
            String extension = name.substring(name.length() - 3);

            if(extension.equals("txt")){
                String titleUnedited = name.substring(0, name.length() - 4);
                title = titleUnedited.replace("_", " ");
            }
        }

        return title;
    }

    private String findContent(){
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

    private File[] txtFinder(String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".txt"); }
        } );

    }

    private int findRating(String content){
        boolean tagOpen = false;
        int ratingStartIndex = 0;

        for(int i = 0; i != content.length() - 1; i ++){
            String doubleCharKey = content.substring(i, i + 2);

            if(doubleCharKey.equals("^^")){
                if(tagOpen){
                    String ratingString = content.substring(ratingStartIndex + 2, i);
                    int ratingInt = Integer.parseInt(ratingString);

                    if(ratingInt > 0 && ratingInt < 11){
                        return ratingInt;
                    }

                    content = content.substring(0, ratingStartIndex) + content.substring(i + 2, content.length());
                    tagOpen = false;
                } else {
                    ratingStartIndex = i;
                    tagOpen = true;
                }
            }
        }

        return -1;
    }

    /*
        Get Functions
    */
    public String getTitleString(){return titleString;}
    public String getDateString(){return dateString;}
    public String getEntryText(){return entryText;}
    public String getEntryDirectory(){return entryDirectory;}
    public Color getHeaderColor(){return headerColor;}
    public int getDateInt(){return dateInt;}
    public int getRating(){return rating;}
}
