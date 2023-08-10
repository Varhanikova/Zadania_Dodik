package com.example.demo;

public class Song {
    private String name;
    private String autor;

    public String getAutor() {
        return autor;
    }

    public String getName() {
        return name;
    }
     public Song( String p_autor,String p_name){
        name =p_name;
        autor=p_autor;
     }
public String toString()
{
    return autor + ": " + name;
}}
