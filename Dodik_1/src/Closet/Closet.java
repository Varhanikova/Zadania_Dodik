package Closet;

import InCloset.Storage;
import InCloset.inCloset;

import java.util.ArrayList;

public class Closet {
    private static Closet single_instance=null;
    private ArrayList<inCloset> inClosets;

    private Closet(){
        inClosets = new ArrayList<inCloset>();
    }
    public static synchronized Closet getInstance()
    {
        if (single_instance == null)
            single_instance = new Closet();

        return single_instance;
    }
    public ArrayList<inCloset> getInClosets()
    {
        return inClosets;
    }
    public inCloset getInCloset(Storage st){
        inCloset in = null;
        for(inCloset incl: inClosets){
            in= incl.getId() != st ? null : incl;
        }
        return in;
    }

    public void addinClosets(inCloset incl){
        inClosets.add(incl);
    }

    public String toString(){
        String str = "";
        for (inCloset inCloset : inClosets) {
            str += inCloset.toString();
        }
        return str;
    }
}
