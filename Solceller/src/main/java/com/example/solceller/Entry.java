package com.example.solceller;

import java.time.LocalDate;

public class Entry
{
    private int entryID;
    private long total;
    private int online;
    private int offline;
    private String month;
    private String day;
    private String time;


    public Entry(int entryID, long total, int online, int offline, String month,String day, String time)
    {
        this.entryID = entryID;
        this.total = total;
        this.online = online;
        this.offline = offline;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public int getEntryID() {
        return entryID;
    }

    public long getTotal() {
        return total;
    }

    public int getOnline() {
        return online;
    }

    public int getOffline() {
        return offline;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
