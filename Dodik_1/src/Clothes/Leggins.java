package Clothes;

public class Leggins extends Clothes {

    public Leggins( String p_nazov) {
        super(Types.LEGGINS, p_nazov);
    }
    @Override
    public String toString(){
        return  getId()+ ": Clothes.Clothes.Leggins " + getNazov();
    }
}
