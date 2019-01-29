public class OutOfStock {

    private int id;
    private int shoe;

    public OutOfStock (int id, int shoe) {

        this.id = id;
        this.shoe = shoe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public int getId() {
        return id;
    }

    public int getShoe() {
        return shoe;
    }

}

