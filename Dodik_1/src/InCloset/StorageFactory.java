package InCloset;

public class StorageFactory {
    public static inCloset createStorage(Storage st){
        inCloset cl = null;
        switch (st){
            case DRAWER:
                cl = new Drawer();
                break;
            case RACK:
                cl  = new Rack();
                break;
            case SHELF:
                cl = new Shelf();
                break;
        }
        return cl;
    }
}
