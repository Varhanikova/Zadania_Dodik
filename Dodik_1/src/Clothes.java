public class Clothes {
    private int id;
    private String nazov;

    public Clothes(int p_id,String p_nazov) {
        nazov = p_nazov;
        id = p_id;
    }

    public String toString() {
        return "id: " + id;
    }
    public int getId(){
        return id;
    }
    public String getNazov(){return nazov;}
}
