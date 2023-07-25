public class Hoodies extends Clothes {

    public Hoodies( String p_nazov) {
        super(4, p_nazov);
    }
    @Override
    public String toString(){
        return  getId()+ ": Hoodie " + getNazov();
    }
}
