public class Customer {

    private int id;
    private String firstName;
    private String familyName;
    private String email;
    private String password;
    private int phoneNumber;
    private String streetAddress;
    private int postalCode;
    private String postalAddress;

    //Use this constructor?
    public Customer(int id, String firstName, String familyName, String email,
                    String password, int phoneNumber, String streetAddress, int postalCode, String postalAddress) {
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.postalAddress = postalAddress;


    }

    public Customer(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }
}
