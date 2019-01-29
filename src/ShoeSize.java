public class ShoeSize {

    private int id;
    private double euroSize;

    public ShoeSize(int id, double euroSize) {
        this.id = id;
        this.euroSize = euroSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEuroSize(double euroSize) {
        this.euroSize = euroSize;
    }

    public int getId() {
        return id;
    }

    public double getEuroSize() {
        return euroSize;
    }
}
