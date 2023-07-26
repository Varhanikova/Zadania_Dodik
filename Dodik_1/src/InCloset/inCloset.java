package InCloset;

import Clothes.Clothes;
import Clothes.Types;
import java.util.ArrayList;

public class inCloset  {
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
    public Storage getId(){ return  id; }
    public String toString(){
        String str = id + ": ";
        for (Clothes clothe : clothes) {
            str += clothe.toString() + "\n";
        }
        return str;
    }
    //delete method where deleted clothes will be the one on the top - type of clothes will be parameter (maybe more than one?)

    public void deleteClothes(Types... values){
        for(Types typ: values){
            int index =-1;

            for(int i=getClothes().size()-1;i>=0;i--){
                if(getClothes().get(i).getId()==typ){
                    index =i;
                }
            }
            if(index>-1){
                getClothes().remove(index);
            }
        }
    }
}
