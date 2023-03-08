package com.example.solceller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper
{
    public static ArrayList<Site> sites = new ArrayList<>();
    private static ArrayList<Integer> onlineArray = new ArrayList<>();
    private static ArrayList<String> sitesArray = new ArrayList<>();
    private static int onlineInt =0;
    public static int getOnline(Site site, int month, int day, int hour)
    {
        onlineInt =0;
        try
        {
           onlineInt = site.siteEntry.get(month-1).get(day-1).get(hour).getOnline();
        }
        catch (Exception e)
        {
            //System.out.println(e);
        }


        return onlineInt;
    }
    public static int getOnline(Site site, int month, int day)
    {
        onlineInt = 0;
        int i = 0;
        for (Entry entry:site.siteEntry.get(month-1).get(day-1))
        {
            try
            {
                onlineInt += site.siteEntry.get(month-1).get(day-1).get(i).getOnline();
                i++;
            }
            catch (Exception e)
            {
                i++;
                //System.out.println(e);
            }

        }
        return onlineInt;
    }
    public static int getOnline(Site site, int month)
    {
        onlineInt =0;
        int i=0;
        try
        {
            for (ArrayList<Entry> e: site.siteEntry.get(month))
            {
                onlineInt += getOnline(site,month-1,i);
                i++;
            }
        }
        catch (Exception e)
        {
            i++;
            //System.out.println(e);
        }
        return onlineInt;
    }
    public static int getOnline(Site site)
    {
        onlineInt =0;
        int i=0;
        try
        {
            for (ArrayList<ArrayList<Entry>> e: site.siteEntry)
            {
             onlineInt += getOnline(site,i);
             i++;
            }
        }
        catch (Exception e)
        {
            i++;
            //System.out.println(e);
        }
        return onlineInt;
    }

    public static ArrayList<Integer> getOnlineInterval(Site site, int month, int day, int hourStart, int hourEnd)
    {
        onlineArray =null;
        if (hourEnd == 0) {hourEnd = 24;}
        int j = 0;
        try
        {
            for (int i = 0; i < hourEnd-hourStart; i++)
            {
                onlineArray.add(getOffline(site,month-1,day-1,j));
                j++;
            }
        }
        catch (Exception e)
        {
            j++;
        }
        return onlineArray;
    }
    public static ArrayList<Integer> getOnlineInterval(Site site, int month, int dayStart, int DayEnd)
    {
        onlineArray =null;
        int j =0;
        for (int i = 0; i < DayEnd-dayStart; i++)
        {
         try
         {
            onlineArray.add(getOnline(site,month-1,j));
         }
         catch (Exception e)
         {j++;}
        }
        return onlineArray;
    }
    public static ArrayList<Integer> getOnlineInterval(Site site, int monthStart,int monthEnd)
    {
        onlineArray =null;
        int j =0;
        for (int i = 0; i < monthEnd-monthStart; i++)
        {
            try
            {
                onlineArray.add(getOnline(site,j));
            }
            catch (Exception e)
            {j++;}
        }
        return onlineArray;
    }

    public static int getOffline(Site site, int month, int day, int hour)
    {
        int offline=0;
        try {
            site.siteEntry.get(month-1).get(day-1).get(hour).getOffline();
        }
        catch (Exception e)
        {}
        return offline;
    }
    public static int getOffline(Site site, int month, int day)
    {
        int offline = 0;
        int i = 0;
        try
        {
            for (Entry entry:site.siteEntry.get(month-1).get(day-1))
            {
                offline += site.siteEntry.get(month-1).get(day-1).get(i).getOffline();
                i++;
            }
        }
        catch (Exception e)
        {
            i++;
        }

        return offline;
    }
    public static int getOffline(Site site, int month)
    {
        int offline =0;
        int i=0;
        try
        {
            for (ArrayList<Entry> e: site.siteEntry.get(month))
            {
                offline += getOffline(site,month-1,i);
                i++;
            }
        }
        catch (Exception e)
        {i++;}
        return offline;
    }
    public static int getOffline(Site site)
    {
        int offline =0;
        int i=0;
        try
        {
            for (ArrayList<ArrayList<Entry>> e: site.siteEntry)
            {
                offline += getOffline(site,i);
                i++;
            }
        }
        catch (Exception e)
        {
            i++;
            //System.out.println(e);
        }
        return offline;
    }

    public static long getTotal(Site site, int month, int day, int hour)
    {
        long total=0;
        try {
            site.siteEntry.get(month-1).get(day-1).get(hour).getTotal();
        }
        catch (Exception e)
        {}
        return total;
    }
    public static ArrayList<Entry> getEntry(Site site, int month, int day, int hour)
    {
        int monthTest = month-1;
        int dayTest = day-1;
        ArrayList<Entry> entry = new ArrayList<>();
        try {
            entry.add(site.siteEntry.get(monthTest).get(dayTest).get(hour));
        }
        catch (Exception e)
        {}
        return entry;
    }
    public static ArrayList<Entry> getEntry(Site site, int month, int day)
    {
        ArrayList<Entry> entry = new ArrayList<>();
        for (int i = 0; i < 25; i++)
        {
            try {
               entry.add(getEntry(site,month-1,day-1,i).get(0));
            }
            catch (Exception e)
            {}

        }
        return entry;
    }
    public static ArrayList<Entry> getEntry(Site site, int month)
    {
        ArrayList<Entry> entry =new ArrayList<>();
        for (int i = 0; i < 32; i++)
        {
            for ( Entry entr :getEntry(site,month-1,i))
            {
                try
                {
                    entry.add(entr);
                }
                catch (Exception e)
                {}

            }


        }

        return entry;
    }


    public static void initializeFromFile() {
        String filePath = new File("SolcelleData.tsv").getAbsolutePath();
        File file = new File(filePath);
        Scanner fileIn;
        try {
            fileIn = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        Site site = new Site(16019,true);
        sites.add(site);
        boolean sitesChecker;
        fileIn.useDelimiter("\r\n");
        fileIn.next();
        while (fileIn.hasNext())
        {
            sitesChecker = false;
            String entry = fileIn.next();
            String[] fullEntry = entry.split("\t");

            String[] date = fullEntry[stringIndex.DATE.getColumnType()].split("-");

            int id = Integer.parseInt(fullEntry[stringIndex.ENTRYID.getColumnType()]);
            long total = Long.parseLong(fullEntry[stringIndex.TOTAL.getColumnType()]);
            int online = Integer.parseInt(fullEntry[stringIndex.ONLINE.getColumnType()]);
            int offline = Integer.parseInt((fullEntry[stringIndex.OFFLINE.getColumnType()]));
            String month = date[dateIndex.MONTH.getdateIndex()];
            String day = date[dateIndex.DAY.getdateIndex()].substring(0, 2);
            int meme = entry.lastIndexOf("T");
            String time = entry.substring(meme + 1, meme + 3); // hard coded index of the hour entry

            for (int j = 0; j <= sites.size(); j++)
            {
                if (sites.size() == j)
                {
                    sitesChecker = true;
                    break;
                }
                if (Integer.parseInt(fullEntry[stringIndex.SID.getColumnType()]) == sites.get(j).getSiteID()) {
                    sites.get(j).createEntry(id, total, online, offline, month, day, time);
                    break;
                }

            }
            if (sitesChecker ==true)
            {
                site = new Site(Integer.parseInt(fullEntry[stringIndex.SID.getColumnType()]),true);
                sites.add(site);
                site.createEntry(id, total, online, offline, month, day, time);
            }
        }
        int tempp = FileHelper.getOnline(sites.get(0),1,1);
        int temp = FileHelper.getOffline(sites.get(0),1,1);
        System.out.println("end ini");
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
