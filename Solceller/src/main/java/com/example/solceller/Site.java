package com.example.solceller;

import java.time.LocalDate;
import java.util.ArrayList;

public class Site
{
    private int siteID;
    public ArrayList<ArrayList<ArrayList<Entry>>> siteEntry = new ArrayList<>();
    public int getSiteID()
        {return this.siteID;}
    public void createEntry(int entryID, long total, int online, int offline, String month, String day, String time)
    {
        Entry entry = new Entry(entryID,total,online,offline,month, day ,time);
        this.siteEntry.get(Integer.parseInt(month)-1).get(Integer.parseInt(day)-1).set(Integer.parseInt(time),entry);
    }

    public Site(int siteID, boolean meme)
    {
        int monthAxis = 12;
        int dayAxis = 31;
        int timeAxis = 24;
        for (int i = 0; i < monthAxis; i++)
        {
            this.siteEntry.add(new ArrayList<ArrayList<Entry>>(dayAxis));
            for (int j = 0; j < dayAxis; j++)
            {
                this.siteEntry.get(i).add(new ArrayList<Entry>(timeAxis));
                for (int k = 0; k < timeAxis; k++)
                {
                 this.siteEntry.get(i).get(j).add(null);
                }
            }
        }
        this.siteID = siteID;

    }
}
