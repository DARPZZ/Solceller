package com.example.solceller;


import java.util.ArrayList;

public class DataProcessing {

    //method for analysing the data entries, to find the lowest+highest production value
    public static results minMaxProdValues(Site site, int month) {

        int maxDay;
        int minDay;
        ArrayList<Integer> minMaxValues = new ArrayList<>();
        int sumDay = 0;
        for (int i = 1; i < 32; i++) {

            sumDay = FileHelper.getOnline(site, month, i) + FileHelper.getOffline(site, month, i);

            minMaxValues.add(sumDay);

        }
        int max = 0;

        for (int prod : minMaxValues) {

            if (prod > max) {
                max = prod;

            }

        }
        int indexMax = 1;
        for (int prod :minMaxValues)
        {
            if (prod == max)
            {
                maxDay = indexMax;
                break;
            }
            indexMax++;
        }
        int min = max;

        for (int prod : minMaxValues) {
            if (prod < min) {

                min = prod;
            }
        }
        int indexMin =1;
        for (int prod :minMaxValues )
        {
            if (prod == min)
            {
                minDay = indexMin;
                break;
            }
            indexMin++;
        }

        results mm = new results(min, max, indexMin,indexMax);
        return mm;

    }
}





