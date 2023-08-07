import Closet.Closet;
import Closet.ClothesFactory;
import InCloset.*;
import Testy.InClosetTest;

public class main {
    public static void main(String[] args) {
        Closet closet = Closet.getInstance();

        inCloset sh1 = StorageFactory.createStorage(Storage.SHELF);
        inCloset r1 = StorageFactory.createStorage(Storage.RACK);
        inCloset dr1 = StorageFactory.createStorage(Storage.DRAWER);

        closet.addinClosets(sh1);
        closet.addinClosets(r1);
        closet.addinClosets(dr1);

        ClothesFactory.fillClosetRandom(closet,5);
        System.out.println(closet.allFromCloset());
    }
}