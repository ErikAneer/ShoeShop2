public class Stock {

    private int id;
    private int shoe;
    private int inStock;

    public Stock(int id, int shoe, int inStock) {
        this.id = id;
        this.shoe = shoe;
        this.inStock = inStock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getId() {
        return id;
    }

    public int getShoe() {
        return shoe;
    }

    public int getInStock() {
        return inStock;
    }
}
