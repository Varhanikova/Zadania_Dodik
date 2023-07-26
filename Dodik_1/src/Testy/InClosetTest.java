package Testy;
import Clothes.Clothes;
import InCloset.Storage;
import InCloset.StorageFactory;
import InCloset.inCloset;
import Clothes.Types;
import org.junit.Test;
import Closet.ClothesFactory;

public class InClosetTest {
    @Test
    public void testFillingInStorages(){
        Clothes pyj = ClothesFactory.createClothes(Types.PYJAMAS,"pyj");
        inCloset dr = StorageFactory.createStorage(Storage.DRAWER);
        inCloset sh = StorageFactory.createStorage(Storage.SHELF);
        inCloset ra = StorageFactory.createStorage(Storage.RACK);

        dr.addClothes(pyj);
        sh.addClothes(pyj);
        ra.addClothes(pyj);
        assert(dr.getClothes().size()!=0);
        assert(sh.getClothes().size()!=0);
        assert(ra.getClothes().size()!=0);
    }
    @Test
    public void followRules(){
        Clothes tsh = ClothesFactory.createClothes(Types.TSHIRT,"pyj");
        Clothes soc = ClothesFactory.createClothes(Types.SOCKS,"pyj");
        Clothes hod = ClothesFactory.createClothes(Types.HOODIES,"pyj");
        Clothes leg = ClothesFactory.createClothes(Types.LEGGINS,"pyj");
        inCloset dr = StorageFactory.createStorage(Storage.DRAWER);
        inCloset ra = StorageFactory.createStorage(Storage.RACK);

        dr.addClothes(soc);
        assert(dr.getClothes().size()==1);
        dr.addClothes(tsh);
        assert(dr.getClothes().size()==2);
        dr.addClothes(hod);
        assert(dr.getClothes().size()==2);
        dr.addClothes(leg);
        assert(dr.getClothes().size()==2);

        ra.addClothes(hod);
        assert(ra.getClothes().size()==1);
        ra.addClothes(soc);
        assert(ra.getClothes().size()==1);
        ra.addClothes(tsh);
        assert(ra.getClothes().size()==1);
        ra.addClothes(leg);
        assert(ra.getClothes().size()==1);

    }

    //       boolean result = false;
//        switch (incl.getId()){
//            case DRAWER:
//                for(Clothes cl: incl.getClothes()){
//                    result= cl.getId() == Types.PYJAMAS || cl.getId() == Types.SOCKS || cl.getId() == Types.TSHIRT;
//                }
//                break;
//            case RACK:
//                for(Clothes cl: incl.getClothes()){
//                    result= cl.getId() == Types.PYJAMAS || cl.getId() == Types.HOODIES;
//                }
//                break;
//
//            default:
//                result = true;
//        }
//        return result;
}
