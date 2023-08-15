package com.example.demo.Classes;

public class Song {
    private int id;
    private String name;
    private String autor;
    private double fee;


     public Song( int p_id,String p_autor,String p_name){
         id = p_id;
        name =p_name;
        autor=p_autor;
         fee=0;
     }
     public Song(int p_id,String p_autor,String p_name,float pfee){
         id = p_id;
         name =p_name;
         autor=p_autor;
         fee=pfee;
     }

    public int getId() {
        return id;
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
         return this + ", fee: " + fee;
    }
}
