package InCloset;

import Clothes.Clothes;
import Clothes.Types;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public String allClothes(){
//        return clothes.stream()
//                .reduce(id + " ",(summary,element)->summary+"\n \t" + element,String::concat);
        return id + "\n \t" + clothes.stream()
                .map(Clothes::toString)
                .collect(Collectors.joining("\n \t"));


    }

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
