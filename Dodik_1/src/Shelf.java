public class Shelf extends inCloset {

    public Shelf(int pid){
       super(pid);
    }
    @Override
    public void addClothes(Clothes cl)
    {
        super.getClothes().add(cl);
        System.out.println("Clothes added!");
    }

    public String toString(){
        String str = getId() + " - Shelf: \n";
        for (Clothes clothe : this.getClothes()) {
            str += clothe.toString() + "\n";
        }
            str+="\n";
        return str;
    }
}
