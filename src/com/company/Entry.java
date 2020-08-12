package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

/**
 Created by jarrettphilips on 2/2/17.
 */
public class Entry {
    /*
        Variables
    */
    public String title;
    public String dateString;
    String directory;
    String txtDirectory;

    ArrayList<String> directoryOfPhotos = new ArrayList<String>();

    int dateInt;
    int numberOfPhotos = 0;

    /*
        Constructors
    */
    public Entry(String directory){
        System.out.println("Creating entry for " + directory);
        this.directory = directory;

        formatEntry();
    }

    /*
        Functions
    */
    private void formatEntry(){
        File entryFolder = new File(directory);
        File[] listOfFiles = entryFolder.listFiles();

        this.dateInt = Integer.parseInt(entryFolder.getName());

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

        this.dateString = month + " " + day + ", " + year;
        //System.out.println(dateString);

        for (File file : listOfFiles) {
            String name = file.getName();
            String extension = name.substring(name.length() - 3);
            //System.out.println(extension);

            if(extension.equals("txt")){
                //System.out.println(file.getName() + " is txt");
                this.title = name.substring(0, name.length() - 4);
                this.txtDirectory = directory + "/" + file.getName();
                //System.out.println("Setting txt directory to: " + this.txtDirectory);
                //System.out.println("Title: " + title);
            } else {
                directoryOfPhotos.add(directory + "/" + file.getName());
                //System.out.println(file.getName() + " is photo");
                //System.out.println("Setting photo directory to: " + directory + "/" + file.getName());
                numberOfPhotos ++;
            }
        }
    }

    /*
        Testing Main
    */
    public static void main(String args[]){
        System.out.println("Initiating Testing Main for Entry");
        String d = "/Users/jarrettphilips/desktop/Journals/20170202";
        System.out.println("Using directory: " + d);

        Entry e = new Entry(d);
    }
}
