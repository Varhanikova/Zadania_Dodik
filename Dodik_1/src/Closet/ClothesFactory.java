package Closet;

import Clothes.Clothes;
import Clothes.Types;
import Clothes.Socks;
import Clothes.Hoodies;
import Clothes.Pyjamas;
import Clothes.Tshirt;
import Clothes.Leggins;

public class ClothesFactory {

    public static Clothes createClothes(Types typ, String nazov){
        Clothes c = null;
        switch (typ){
            case SOCKS:
                c = new Socks(nazov);
                break;
            case TSHIRT:
                c= new Tshirt(nazov);
                break;
            case LEGGINS:
                c= new Leggins(nazov);
                break;
            case HOODIES:
                c= new Hoodies(nazov);
                break;
            case PYJAMAS:
                c= new Pyjamas(nazov);
                break;
        }
        return c;
    }
    public static void fillClosetRandom(Closet cl, int sum){
        Clothes c=null;

        for(Types typ: Types.values()){
            for(int i=0;i<sum;i++){
                c =  ClothesFactory.createClothes(typ, typ.name() +i);
                for(int j = 0; j < cl.getInClosets().size(); j++){
                    cl.getInClosets().get(j).addClothes(c);
                }

            }
        }
        //return cl;
    }
}
