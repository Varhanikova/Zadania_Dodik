public class Drawer extends inCloset{
    public Drawer(int pid){
        super(pid);
    }
    @Override
    public void addClothes(Clothes cl){
        if( cl.getId()==1 || cl.getId()==2 ) {
            super.getClothes().add(cl);
            System.out.println("Clothes added!");
        } else {
            System.out.println("Invalid clothes!");
        }
    }
    public String toString(){
        String str = getId() + " - Drawer: \n";
        for (Clothes clothe : getClothes()) {
            str += clothe.toString() + "\n";
        }
        str+="\n";
        return str;
    }

}
