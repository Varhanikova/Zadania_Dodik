public class Leggins extends Clothes{

    public Leggins( String p_nazov) {
        super(3, p_nazov);
    }
    @Override
    public String toString(){
        return  getId()+ ": Leggins" + getNazov();
    }
}
