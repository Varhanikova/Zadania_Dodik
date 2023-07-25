public class Tshirt extends Clothes{

    public Tshirt(String p_nazov) {
        super(2, p_nazov);
    }
    @Override
    public String toString(){
        return getId()+ ": Tshirt " + getNazov();
    }
}
