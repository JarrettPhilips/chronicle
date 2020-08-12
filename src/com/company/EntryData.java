package com.company;

import java.awt.*;
import java.io.File;

public class EntryData {
    /*
        Variables
    */
    private String title = "-1";
    private String dateString = "-1";
    private int dateInt = -1;
    private int rating = -1;

    private Color headerColor = new Color(255,255,255);

    private String entryDirectory = "-1";
    private String txtEntryDirectory = "-1";

    private String entryText = "-1";
    /*
        Constructors
    */
    public EntryData(String entryDirectory, Color pc, Color sc, Color hc, Color lc, Color mc){
        this.entryDirectory = entryDirectory;
        headerColor = calculateHeaderColor(sc, hc, lc, mc);
        findDate();
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

    private void findDate(){
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

        dateString = month + " " + day + ", " + year;
    }

    private void findRating(){

    }
    /*
        Get Functions
    */
    public String getTitle(){return title;}
    public String getDateString(){return dateString;}
    public String getEntryText(){return entryText;}
    public Color getHeaderColor(){return headerColor;}
    public int getDateInt(){return dateInt;}
}
