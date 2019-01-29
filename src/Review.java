public class Review {

    private int id;
    private int shoe;
    private int verdict;
    private int customer;
    private String customerComment;

    public Review (int id, int shoe, int verdict, String customerComment) {

        this.id = id;
        this.shoe = shoe;
        this.verdict = verdict;
        this.customerComment = customerComment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public void setVerdict(int verdict) {
        this.verdict = verdict;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public int getId() {
        return id;
    }

    public int getShoe() {
        return shoe;
    }

    public int getVerdict() {
        return verdict;
    }

    public int getCustomer() {
        return customer;
    }

    public String getCustomerComment() {
        return customerComment;
    }
}
