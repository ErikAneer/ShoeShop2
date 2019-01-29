
import java.sql.*;
import java.util.List;

public class CustomerRepo {

    PropteriesUtil pu = new PropteriesUtil();

    public void getAllCustomersAsList(List<Customer> customers) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select customers.id, customers.firstName, customers.familyName, customers.email, " +
                "customers.password, customers.phoneNumber,\n" +
                "      customers.streetAddress, customers.postalCode, customers.postalAddress from customers;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("customers.id");
                String firstName = rs.getString("customers.firstName");
                System.out.println(firstName);
                String familyName = rs.getString("customers.familyName");
                String email = rs.getString("customers.email");
                String password = rs.getString("customers.password");
                int phoneNumber = rs.getInt("customers.phoneNumber");
                String streetAddress = rs.getString("customers.streetAddress");
                int postalCode = rs.getInt("customers.postalCode");
                String postalAddress = rs.getString("customers.postalAddress");

               customers.add(new Customer(id, firstName, familyName, email, password, phoneNumber, streetAddress,
                       postalCode, postalAddress));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select customers.id, customers.firstName, customers.familyName, customers.email, " +
                "customers.password, customers.phoneNumber,\n" +
                "      customers.streetAddress, customers.postalCode, customers.postalAddress from customers " +
                "where customers.email = ?";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties())
        ) {
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("customers.id");
                String firstName = rs.getString("customers.firstName");
                String familyName = rs.getString("customers.familyName");
                String emailAddress = rs.getString("customers.email");
                String password = rs.getString("customers.password");
                int phoneNumber = rs.getInt("customers.phoneNumber");
                String streetAddress = rs.getString("customers.streetAddress");
                int postalCode = rs.getInt("customers.postalCode");
                String postalAddress = rs.getString("customers.postalAddress");

                customer = new Customer(id, firstName, familyName, emailAddress, password, phoneNumber, streetAddress,
                        postalCode, postalAddress);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public Boolean checkIfRegisteredCustomerByEmail(String email) {
        Boolean isCustomer = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select count(*) from customers where customers.email = ?";
        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties())
        ) {
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            int customerFound = 0;

            while (rs.next()) {
                customerFound = rs.getInt("count(*)");
            }

            if (customerFound > 0) {
                isCustomer = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCustomer;
    }

    public void addNewCustomer(String firstname, String familyName, String emailAddress, String password,
                               int phoneNumber, String streetAddress, int postalCode, String postalAddress) {

        ResultSet rs = null;
        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            CallableStatement stmt = con.prepareCall("call addCustomer(?,?,?,?,?,?,?,?)");
            stmt.setString(1, firstname);
            stmt.setString(2, familyName);
            stmt.setString(3, emailAddress);
            stmt.setString(4, password);
            stmt.setInt(5, phoneNumber);
            stmt.setString(6, streetAddress);
            stmt.setInt(7, postalCode);
            stmt.setString(8, postalAddress);

            stmt.execute();
            rs = stmt.getResultSet();

            while (rs!= null && rs.next() ) {
                String  errorMessage = rs.getString("error");
                if (errorMessage != null) {
                    System.out.println(errorMessage);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
