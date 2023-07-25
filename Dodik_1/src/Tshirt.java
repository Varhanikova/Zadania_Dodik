public class Tshirt extends Clothes{

    public Tshirt(String p_nazov) {
        super(Types.TSHIRT, p_nazov);
    }
    @Override
    public String toString(){
        return getId()+ ": Tshirt " + getNazov();
    }
}
