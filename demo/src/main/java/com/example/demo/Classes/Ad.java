package com.example.demo.Classes;

public class Ad {
    private String sponzor;
    private double profit;
    private double probability;
    private int used=0;

    public Ad(String sp, double p_gee, double prob){
        sponzor = sp;
        profit = p_gee;
        probability = prob;
    }
    public Ad(String sp, double p_gee, double prob,int pused){
        sponzor = sp;
        profit = p_gee;
        probability = prob;
        used = pused;
    }
    public String getSponzor(){return sponzor;}

    public double getProfit() {
        return profit;
    }

    public void setUsed() {
        this.used++;
    }

    public int getUsed() {
        return used;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "sponzor='" + sponzor + '\'' +
                ", fee=" + profit +
                ", probability=" + probability +
                '}';
    }
}
