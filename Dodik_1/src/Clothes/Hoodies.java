package Clothes;

public class Hoodies extends Clothes {

    public Hoodies( String p_nazov) {
        super(Types.HOODIES, p_nazov);
    }
    @Override
    public String toString(){
        return  getId()+ " " + getNazov();
    }
}
