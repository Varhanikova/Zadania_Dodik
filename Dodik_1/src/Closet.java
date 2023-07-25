import java.util.ArrayList;

public class Closet {
    private int id;
    private ArrayList<inCloset> inClosets;

    public Closet(int p_id){
        id = p_id;
        inClosets = new ArrayList<inCloset>();
    }

    public inCloset getInClosets(int index)
    {
        return inClosets.get(index);
    }
    public void addinClosets(inCloset incl){
        inClosets.add(incl);
    }

    public String toString(){
        String str = "";
        for (inCloset inCloset : inClosets) {
            str += inCloset.toString();
        }
        return str;
    }
}
