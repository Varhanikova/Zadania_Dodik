package InCloset;
import Clothes.Types;
import Clothes.Clothes;

public class Rack extends inCloset {
    public Rack(){
        super(Storage.RACK);
    }
    @Override
    public void addClothes(Clothes cl){
        if(cl.getId()== Types.HOODIES || cl.getId()== Types.PYJAMAS) {
            super.getClothes().add(cl);
            System.out.println("Clothes.Clothes added!");
        } else {
            System.out.println("Invalid clothes!");
        }
    }
    public String toString(){
        String str = getId() + " \n";
        for (Clothes clothe : getClothes()) {
            str += clothe.toString() + "\n";
        }
        str+="\n";
        return str;
    }

}
