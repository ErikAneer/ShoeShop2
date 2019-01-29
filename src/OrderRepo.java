
import jdk.internal.org.objectweb.asm.Type;

import javax.swing.*;
import java.sql.*;

public class OrderRepo {

    PropteriesUtil pu = new PropteriesUtil();

    public int addToChart(int customerId, int orderId, int shoeId) {

        ResultSet rs = null;
        int receivedOrderId = 0;

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties())) {
            CallableStatement stmt = con.prepareCall("call addToChart(?,?,?);");

            //in customerId int, in thisorderId int, in shoeId int, out IdOfOrder int
            stmt.setInt(1, customerId);
            stmt.setInt(2, orderId);
            stmt.setInt(3, shoeId);
            stmt.registerOutParameter(2, Type.INT);
            stmt.execute();
            receivedOrderId = stmt.getInt(2);
            System.out.println("receivedOrderId inne i metod " + receivedOrderId );

            rs = stmt.getResultSet();

            while (rs != null && rs.next()) {
                String errorMessage = rs.getString("error");
                if (errorMessage != null) {
                    System.out.println(errorMessage);
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fel! " + e.getMessage());
        }
        return receivedOrderId;
    }
}
