import java.util.ArrayList;

public class inCloset {
    private int id;
    private ArrayList<Clothes> clothes;
    public inCloset(int pid){
        id = pid;
        clothes = new ArrayList<Clothes>();
    }
    public void addClothes(Clothes cl){

    }
    public ArrayList<Clothes> getClothes(){
        return clothes;
    }
    public int getId(){return  id;}
    public String toString(){
        String str = id + ": ";
        for (Clothes clothe : clothes) {
            str += clothe.toString() + "\n";
        }
        return str;
    }

}
