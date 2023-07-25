
public class main {
    public static void main(String[] args) {
        Closet closet = new Closet(1);

        Pyjamas p1 = new Pyjamas("p");

        Clothes c1 = new Clothes(Types.PYJAMAS, "pyj");
        Clothes c2 = new Clothes(Types.TSHIRT, "pyj");
        Clothes c3 = new Clothes(Types.SOCKS, "pyj");
        Clothes c4 = new Clothes(Types.HOODIES, "pyj");
        Clothes c5 = new Clothes(Types.HOODIES, "pyj");
        Clothes c6 = new Clothes(Types.TSHIRT, "pyj");

        Shelf sh1 = new Shelf(1);
        Rack r1 = new Rack(1);
        Drawer dr1 = new Drawer(1);

        closet.addinClosets(sh1);
        closet.addinClosets(r1);
        closet.addinClosets(dr1);
        closet.getInClosets(0).addClothes(c1);
        closet.getInClosets(2).addClothes(c2);
        closet.getInClosets(2).addClothes(c3);
        closet.getInClosets(2).addClothes(c4);
        closet.getInClosets(0).addClothes(c5);
        closet.getInClosets(1).addClothes(c6);
        closet.getInClosets(1).addClothes(p1);


        System.out.println(closet.toString());

    }
}