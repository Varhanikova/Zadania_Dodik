package Clothes;

public class Socks extends Clothes {


    public Socks(String p_nazov) {
        super(Types.SOCKS, p_nazov);

    }
    @Override
    public String toString(){
        return  getId() + ": Clothes.Clothes.Socks " + getNazov();
    }
}
