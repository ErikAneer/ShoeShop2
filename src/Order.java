import java.util.Date;

public class Order {

    private int id;
    private int customer;
    private Date orderDate;
    private Boolean dispatched;
    private Date dispatchedDate;

    public Order(int id, int customer, Date orderDate, Boolean dispatched, Date dispatchedDate) {
        this.id = id;
        this.customer=customer;
        this.orderDate=orderDate;
        this.dispatched=dispatched;
        this.dispatchedDate=dispatchedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDispatched(Boolean dispatched) {
        this.dispatched = dispatched;
    }

    public Date getDispatchedDate() {
        return dispatchedDate;
    }

    public int getId() {
        return id;
    }

    public int getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Boolean getDispatched() {
        return dispatched;
    }

    public void setDispatchedDate(Date dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }
}
