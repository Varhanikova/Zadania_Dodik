
public class main {
    public static void main(String[] args) {
        Closet closet = new Closet(1);

        Tshirt t1 = new Tshirt("a");
        Tshirt t2 = new Tshirt("b");
        Socks s1 = new Socks("a");
        Socks s2 = new Socks("b");
        Hoodies h1 = new Hoodies("a");
        Hoodies h2 = new Hoodies("b");

        Shelf sh1 = new Shelf(1);
        Rack r1 = new Rack(1);
        Drawer dr1 = new Drawer(1);

        closet.addinClosets(sh1);
        closet.addinClosets(r1);
        closet.addinClosets(dr1);
        closet.getInClosets(0).addClothes(t1);
        closet.getInClosets(2).addClothes(h2);
        closet.getInClosets(2).addClothes(s1);
        closet.getInClosets(1).addClothes(h2);
        closet.getInClosets(1).addClothes(t2);


        System.out.println(closet.toString());

    }
}