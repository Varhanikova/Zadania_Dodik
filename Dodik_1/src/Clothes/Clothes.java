package Clothes;

public class Clothes {
    private Types id;
    private String nazov;

    public Clothes(Types p_id, String p_nazov) {
        nazov = p_nazov;
        id = p_id;
    }

    public String toString() {
        return "id: " + id;
    }
    public Types getId(){
        return id;
    }
    public String getNazov(){return nazov;}

}
