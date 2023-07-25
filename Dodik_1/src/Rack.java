public class Rack extends inCloset {
    public Rack(int pid){
        super(pid);
    }
    @Override
    public void addClothes(Clothes cl){
        if(cl.getId()==4 || cl.getId()==5) {
            super.getClothes().add(cl);
            System.out.println("Clothes added!");
        } else {
            System.out.println("Invalid clothes!");
        }
    }
    public String toString(){
        String str = getId() + " - Rack: \n";
        for (Clothes clothe : getClothes()) {
            str += clothe.toString() + "\n";
        }
        str+="\n";
        return str;
    }

}
