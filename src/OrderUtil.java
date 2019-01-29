import java.util.List;
import java.util.Map;

public class OrderUtil {

    public void displayOrderTotal(List<Shoe> allShoes, List<Orderitem> orderItems,
                                  Map<Integer, ShoeMaker> shoeMakers, Map<Integer, ShoeColour> shoeColours) {
        int orderSum = 0;
        int numberOfItems = 0;

        for (Orderitem oi : orderItems) {
            for (Shoe s : allShoes) {
                if (s.getId() == oi.getShoe()) {
                    System.out.println(shoeMakers.get(s.getMaker()).getName()+ ", " + s.getName() + ", "
                            + shoeColours.get(s.getColour()).getColour()+ ", á " + s.getPrice()
                            + " kr. Antal " + oi.getQuantity() + ", pris " +(oi.getQuantity()* s.getPrice()));
                    orderSum = orderSum + (oi.getQuantity()* s.getPrice());
                    numberOfItems = numberOfItems + oi.getQuantity();
                }
            }
        }
        System.out.println("Antal varor: " + numberOfItems);
        System.out.println("Totalpris: " + orderSum + " kr");
    }

}
