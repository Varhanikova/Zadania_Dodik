package Testy;
import Closet.Closet;
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
    public void followRulesDrawer(){
        Clothes tsh = ClothesFactory.createClothes(Types.TSHIRT,"pyj");
        Clothes soc = ClothesFactory.createClothes(Types.SOCKS,"pyj");
        Clothes hod = ClothesFactory.createClothes(Types.HOODIES,"pyj");
        Clothes leg = ClothesFactory.createClothes(Types.LEGGINS,"pyj");
        inCloset dr = StorageFactory.createStorage(Storage.DRAWER);

        dr.addClothes(soc);
        assert(dr.getClothes().size()==1);
        dr.addClothes(tsh);
        assert(dr.getClothes().size()==2);
        dr.addClothes(hod);
        assert(dr.getClothes().size()==2);
        dr.addClothes(leg);
        assert(dr.getClothes().size()==2);

    }
    @Test
    public void followRulesRack(){
        Clothes tsh = ClothesFactory.createClothes(Types.TSHIRT,"pyj");
        Clothes soc = ClothesFactory.createClothes(Types.SOCKS,"pyj");
        Clothes hod = ClothesFactory.createClothes(Types.HOODIES,"pyj");
        Clothes leg = ClothesFactory.createClothes(Types.LEGGINS,"pyj");
        inCloset ra = StorageFactory.createStorage(Storage.RACK);

        ra.addClothes(hod);
        assert(ra.getClothes().size()==1);
        ra.addClothes(soc);
        assert(ra.getClothes().size()==1);
        ra.addClothes(tsh);
        assert(ra.getClothes().size()==1);
        ra.addClothes(leg);
        assert(ra.getClothes().size()==1);
    }
    @Test
    public void testDeleteFromRack()
    {
        Closet closet = Closet.getInstance();
        inCloset r1 = StorageFactory.createStorage(Storage.RACK);
        closet.addinClosets(r1);
        ClothesFactory.fillClosetRandom(closet,1);
        closet.getInCloset(Storage.RACK).deleteClothes(Types.HOODIES);
        assert(closet.getInCloset(Storage.RACK).getClothes().size()==1);

    }
    @Test
    public void testDeleteFromDrawer()
    {
        Closet closet = Closet.getInstance();
        inCloset dr1 = StorageFactory.createStorage(Storage.DRAWER);
        closet.addinClosets(dr1);
        ClothesFactory.fillClosetRandom(closet,1);
        closet.getInCloset(Storage.DRAWER).deleteClothes(Types.PYJAMAS);
        assert(closet.getInCloset(Storage.DRAWER).getClothes().size()==2);

    }
    @Test
    public void testDeleteFromShelf()
    {
        Closet closet = Closet.getInstance();
        inCloset sh1 = StorageFactory.createStorage(Storage.SHELF);
        closet.addinClosets(sh1);
        ClothesFactory.fillClosetRandom(closet,1);

        closet.getInCloset(Storage.SHELF).deleteClothes(Types.HOODIES,Types.PYJAMAS);
        assert(closet.getInCloset(Storage.SHELF).getClothes().size()==3);

    }

}
