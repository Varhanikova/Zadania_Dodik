public class Pyjamas extends Clothes{
    public Pyjamas(String p_nazov) {
        super(5, p_nazov);
    }
    @Override
    public String toString(){
        return  getId()+ ": Pyjamas " + getNazov();
    }
}
