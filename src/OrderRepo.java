
import jdk.internal.org.objectweb.asm.Type;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderRepo {

    PropteriesUtil pu = new PropteriesUtil();

    public int addToChart(int customerId, int orderId, int shoeId) {

        ResultSet rs = null;
        int receivedOrderId = 0;

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties())) {
            CallableStatement stmt = con.prepareCall("call addToChart(?,?,?);");
            stmt.setInt(1, customerId);
            stmt.setInt(2, orderId);
            stmt.setInt(3, shoeId);
            stmt.registerOutParameter(2, Type.INT);
            stmt.execute();
            receivedOrderId = stmt.getInt(2);

            /*
            rs = stmt.getResultSet();

            while (rs != null && rs.next()) {
                String errorMessage = rs.getString("error");
                    if (errorMessage != null) {
                        System.out.println(errorMessage);
                    }
                }
                */
            }
        catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Fel! " + e.getMessage());
        }
        return receivedOrderId;
    }

    public List<Orderitem> getAllorderItems(int orderId) {
        List<Orderitem> orderitems = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "select orderitems.id, orderitems.orderid, orderitems.shoe, " +
                "orderitems.quantity from orderitems where orderitems.orderId = ?";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            stmt.setInt(1, orderId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("orderitems.id");
                int order = rs.getInt("orderitems.orderid");
                int shoe = rs.getInt("orderitems.shoe");
                int quantity = rs.getInt("orderitems.shoe");
                orderitems.add(new Orderitem(id, order, shoe, quantity));
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
        return orderitems;
    }
}
