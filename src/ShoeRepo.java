import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoeRepo {

    PropteriesUtil pu = new PropteriesUtil();

    public void getAllShoesFromDatabase(List<Shoe> shoes){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoes.id, shoes.maker, shoes.name, shoes.shoesize, shoes.colour, shoes.price " +
                "from shoes;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoes.id");
                int maker = rs.getInt("shoes.maker");
                String name = rs.getString("shoes.name");
                int size = rs.getInt("shoes.shoesize");
                int colour = rs.getInt("shoes.colour");
                int price = rs.getInt("shoes.price");

                shoes.add(new Shoe(id, maker, name, size, colour, price));
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


    public void getAllShoeColoursFromDatabase(Map<Integer, ShoeColour> shoeColours) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoecolours.id, shoecolours.colour from shoecolours;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoecolours.id");
                String colour = rs.getString("shoecolours.colour");

                shoeColours.put(id, new ShoeColour(id, colour));
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

    public void getAllShoeMakersFromDatabase(Map<Integer, ShoeMaker> shoeMakers) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoemakers.id, shoemakers.name from shoemakers;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoemakers.id");
                String name = rs.getString("shoemakers.name");

                shoeMakers.put(id, new ShoeMaker(id, name));
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

    public void getAllShoeTypesFromDatabase (Map<Integer, ShoeType> shoeTypes) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoetypes.id, shoetypes.name from shoetypes;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoetypes.id");
                String shoeType = rs.getString("shoetypes.name");
                shoeTypes.put(id, new ShoeType(id, shoeType));
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

    public void getAllShoeSizesFromDatabase(Map<Integer, ShoeSize> shoeSizes) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoesizes.id, shoesizes.euroSize from shoesizes;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoesizes.id");
                Double euroSize = rs.getDouble("shoesizes.euroSize");

                shoeSizes.put(id, new ShoeSize(id, euroSize));
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

    public void getAllShoeSizesFromDatabaseReveresed(Map<Double, ShoeSize> sizesReversed) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoesizes.id, shoesizes.euroSize from shoesizes;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoesizes.id");
                Double euroSize = rs.getDouble("shoesizes.euroSize");

                sizesReversed.put(euroSize, new ShoeSize(id, euroSize));
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

    public void getAllCategoriesForShoesFromDatabase (List<ShoeCategory> shoeCategories) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select shoecategories.id, shoecategories.shoe, shoecategories.shoeType from shoecategories;";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("shoecategories.id");
                int shoe = rs.getInt("shoecategories.shoe");
                int shoeType = rs.getInt("shoecategories.shoetype");
                shoeCategories.add(new ShoeCategory(id, shoe, shoeType));
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

    public List<Stock> getStockInAllSizesForShoeModel(Shoe shoeToCheck) {
        List<Stock> stockForTheModel = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "Select stock.id, stock.shoe, stock.inStock from stock inner join shoes on shoes.id = stock.shoe where shoes.name = ?";

        try (Connection con = pu.createConnectionFromProperty(pu.loadProperties()))
        {
            stmt = con.prepareStatement(sqlQuery);
            stmt.setString(1, shoeToCheck.getName());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("stock.id");
                int shoe = rs.getInt("stock.shoe");
                int inStock = rs.getInt("stock.inStock");
                stockForTheModel.add(new Stock(id, shoe, inStock));
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
        return stockForTheModel;
    }


}
