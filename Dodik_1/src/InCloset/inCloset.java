package InCloset;

import Clothes.Clothes;

import java.util.ArrayList;

public class inCloset {
    private Storage id;
    private ArrayList<Clothes> clothes;
    public inCloset(Storage pid){
        id = pid;
        clothes = new ArrayList<Clothes>();
    }
    public void addClothes(Clothes cl){
    }
    public ArrayList<Clothes> getClothes(){
        return clothes;
    }
    public Storage getId(){return  id;}
    public String toString(){
        String str = id + ": ";
        for (Clothes clothe : clothes) {
            str += clothe.toString() + "\n";
        }
        return str;
    }

}
