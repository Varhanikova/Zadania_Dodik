package InCloset;

import Clothes.Clothes;
import InCloset.inCloset;

public class Shelf extends inCloset {

    public Shelf(){
       super(Storage.SHELF);
    }
    @Override
    public void addClothes(Clothes cl)
    {
        super.getClothes().add(cl);
        System.out.println(cl.getNazov() + " added!");
    }

    public String toString(){
        String str = getId() + "\n";
        for (Clothes clothe : this.getClothes()) {
            str += clothe.toString() + "\n";
        }
            str+="\n";
        return str;
    }
}
