package com.example.solceller;

public class results {

    private final int min;
    private final int max;
    private final int indexMin;
    private final int indexMax;


    public results(int min, int max, int indexMin, int indexMax)
    {
        this.min = min;
        this.max = max;
        this.indexMin = indexMin;
        this.indexMax = indexMax;

    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }


    public int getIndexMin() {
        return indexMin;
    }

    public int getIndexMax() {
        return indexMax;
    }
}
