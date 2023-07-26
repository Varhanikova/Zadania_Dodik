package Testy;
import Clothes.Clothes;
import InCloset.inCloset;
import Clothes.Types;
public class InClosetTest {

    public static boolean testStorage(inCloset incl){
       boolean result = false;
        switch (incl.getId()){
            case DRAWER:
                for(Clothes cl: incl.getClothes()){
                    result= cl.getId() == Types.PYJAMAS || cl.getId() == Types.SOCKS || cl.getId() == Types.TSHIRT;
                }
                break;
            case RACK:
                for(Clothes cl: incl.getClothes()){
                    result= cl.getId() == Types.PYJAMAS || cl.getId() == Types.HOODIES;
                }
                break;

            default:
                result = true;
        }
        return result;
    }
}
