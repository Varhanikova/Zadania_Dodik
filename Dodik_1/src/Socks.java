public class Socks extends Clothes{


    public Socks(String p_nazov) {
        super(1, p_nazov);

    }
    @Override
    public String toString(){
        return  getId() + ": Socks " + getNazov();
    }
}
