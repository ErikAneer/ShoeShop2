public class GradeLevel {

    private  int id;
    private String name;
    private int rate;

    public GradeLevel(int id, String name, int rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }
}
