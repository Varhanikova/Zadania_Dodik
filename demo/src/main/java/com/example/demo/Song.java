package com.example.demo;

public class Song {
    private String name;
    private String autor;
    private double fee;


     public Song( String p_autor,String p_name){
        name =p_name;
        autor=p_autor;
         fee=0;
     }
    public String toString()
    {
        return autor + ": " + name;
    }

    public String getAutor() {
        return autor;
    }

    public String getName() {
        return name;
    }

    public double getFee() {
        return fee;
    }
    public void addFee(double p_fee){
         fee+=p_fee;
    }
    public String songAd(){
         return toString() + ", fee: " + fee;
    }
}
