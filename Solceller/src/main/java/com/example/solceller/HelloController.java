package com.example.solceller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HelloController
{
    private static ArrayList<Site> sites = new ArrayList<>();
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public static void initializeFromFile()
    {
        String filePath = new File("SolcelleData.tsv").getAbsolutePath();
        File file = new File(filePath);
        Scanner fileIn;
        try
        {
            fileIn = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100; i++)
        {
            fileIn.useDelimiter("\n");
            fileIn.next();
            String entry = fileIn.next();
            String[] fullEntry = entry.split("\t");

            String[] date = fullEntry[stringIndex.DATE.getColumnType()].split("-");

            int id = Integer.parseInt(fullEntry[stringIndex.SID.getColumnType()]);
            long total = Long.parseLong(fullEntry[stringIndex.TOTAL.getColumnType()]);
            int online = Integer.parseInt(fullEntry[stringIndex.ONLINE.getColumnType()]);
            int offline = Integer.parseInt((fullEntry[stringIndex.OFFLINE.getColumnType()]));
            String month =date[dateIndex.MONTH.getdateIndex()];
            String day =date[dateIndex.DAY.getdateIndex()].substring(0,2); // needs fix

            int meme = entry.lastIndexOf("T");
            String time = entry.substring(meme+1,meme+3); // hard coded index of the hour entry

            Site site = new Site(Integer.parseInt(fullEntry[stringIndex.SID.getColumnType()]));
            sites.add(site);
            site.createEntry(id,total,online,offline,month,day,time);
        }
        System.out.println("meem");
    }
    public enum stringIndex{
        ENTRYID (0),
        DATE (1),
        SID (2),
        TOTAL (3),
        ONLINE (4),
        OFFLINE (5);
        private int columnType;
        stringIndex(int s )
        {
            this.columnType = s;
        }
        public int getColumnType()
        {
            return columnType;
        }

    }
    public enum dateIndex {
        YEAR (0),
        MONTH (1),
        DAY (2);
        private int index;
        dateIndex(int i)
        {
            this.index = i;
        }
        public int getdateIndex()
        {
            return index;
        }
    }
}