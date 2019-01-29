public class Orderitem {

    private int id;
    private int orderId;
    private int shoe;
    private int quantity;

    public Orderitem (int id, int orderId,int shoe, int quantity ) {

        this.id = id;
        this.orderId = orderId;
        this.shoe = shoe;
        this.quantity = quantity;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getShoe() {
        return shoe;
    }

    public int getQuantity() {
        return quantity;
    }
}
