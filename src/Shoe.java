public class Shoe {

    private int id;
    private int maker;
    private String name;
    private int size;
    private int colour;
    private int price;

    public Shoe(int id, int maker, String name, int size, int colour, int price) {
        this.id = id;
        this.maker = maker;
        this.name = name;
        this.size = size;
        this.colour = colour;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaker(int maker) {
        this.maker = maker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getMaker() {
        return maker;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getColour() {
        return colour;
    }

    public int getPrice() {
        return price;
    }


}
