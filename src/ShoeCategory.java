public class ShoeCategory {

    private int id;
    private int shoe;
    private int shoeType;

    public ShoeCategory (int id, int shoe, int shoeType) {
        this.id = id;
        this.shoe = shoe;
        this.shoeType = shoeType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public void setShoeType(int shoeType) {
        this.shoeType = shoeType;
    }

    public int getId() {
        return id;
    }

    public int getShoe() {
        return shoe;
    }

    public int getShoeType() {
        return shoeType;
    }
}
