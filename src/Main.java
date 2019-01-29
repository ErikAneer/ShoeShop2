import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        CustomerUtil cu = new CustomerUtil();
        ShoeRepo sr = new ShoeRepo();
        ShoeUtil su = new ShoeUtil();
        OrderRepo or = new OrderRepo();
        OrderUtil ou = new OrderUtil();

        Map<Integer, ShoeColour> shoeColours = new HashMap<Integer, ShoeColour>();
        sr.getAllShoeColoursFromDatabase(shoeColours);

        Map<Integer, ShoeMaker> makers = new HashMap<Integer, ShoeMaker>();
        sr.getAllShoeMakersFromDatabase(makers);

        Map<Integer, ShoeSize> sizes = new HashMap<Integer, ShoeSize>();
        sr.getAllShoeSizesFromDatabase(sizes);

        Map<Double, ShoeSize> sizesReversed = new HashMap<Double, ShoeSize>();
        sr.getAllShoeSizesFromDatabaseReveresed(sizesReversed);

        Map<Integer, ShoeType> shoeTypes = new HashMap<Integer, ShoeType>();
        sr.getAllShoeTypesFromDatabase(shoeTypes);

        List<Stock> tempStock;

        List<Shoe> allShoes = new ArrayList<>();
        List<Shoe> selectedShoes = new ArrayList<>();
        List<ShoeCategory> shoeCategories = new ArrayList<>();

        sr.getAllCategoriesForShoesFromDatabase(shoeCategories);
        sr.getAllShoesFromDatabase(allShoes);

        Customer currentUser = null;

        Boolean loopLogin = true;
        Boolean loopLoginInner;

        while (loopLogin) {
            loopLoginInner = true;
            if (cu.askUserIfRegisteredCustomer()) {
                while (loopLoginInner) {
                    currentUser = cu.checkIfEmailBelongsToCustomer();
                    String password = cu.promptForCorrectPassword();
                    if (currentUser != null && currentUser.getPassword().equals(password)) {
                        System.out.println("Välkommen " + currentUser.getFirstName() + "! Du är inloggad.");
                        loopLogin = false;
                        loopLoginInner = false;
                    } else {

                        if (!cu.logInFailedRetryOrRegister()) {
                            cu.registerNewCustomer();
                            loopLoginInner = false;
                        }
                    }
                }
            }
            else {
                 cu.registerNewCustomer();
            }
        } // end while login

        System.out.println();
        Scanner shopScanner = new Scanner(System.in);
        Boolean continueShop = true;
        int orderId = 0;
        while (continueShop) {
            su.displayAllShoes(allShoes, selectedShoes, makers, shoeColours, sizes);
            Shoe tempShoe;
            tempShoe = su.getTheShoeToBuy(allShoes, selectedShoes, makers, shoeColours, sizes, su.receiveShoeModelFromInput());
            tempStock = sr.getStockInAllSizesForShoeModel(tempShoe);
            su.displaySizesAndStockForModel(tempStock, tempShoe, allShoes, makers, sizes);

            System.out.println("Ange storlek du vill beställa: ");
            Scanner receiveOrder = new Scanner(System.in);
            double receivedSize = receiveOrder.nextDouble();
            System.out.println("Ange antal du vill beställa: ");
            int receiveQuantity = receiveOrder.nextInt();
            Shoe shoeToOrder = null;
            int shoesAdded = 0;


            int getOrderId = 0;
            for (Shoe s : allShoes) {
                if (s.getName().equalsIgnoreCase(tempShoe.getName()) && s.getSize() == sizesReversed.get(receivedSize).getId()) {
                    shoeToOrder = s;
                }
            }
            int stockForShoeToOrder = sr.getStockForShoeModel(shoeToOrder);
            if (stockForShoeToOrder >= receiveQuantity) {
                for (int i = 0; i < receiveQuantity; i++) {
                    getOrderId = or.addToChart(currentUser.getId(), orderId, shoeToOrder.getId());
                    orderId = getOrderId;
                    shoesAdded++;
                }
                System.out.println("Sko tillagd i varukorgen: " + makers.get(shoeToOrder.getMaker()).getName()
                        + ", " + shoeToOrder.getName() + ", antal " + shoesAdded);
            } else {
                System.out.println("Antalet par du vill beställa är större än antalet i lager. Vänligen gör ett nytt val.");
            }
            System.out.println("Vill du göra ett till val? (Ja/Nej");

            String addMoreItems = shopScanner.nextLine();
            if (!addMoreItems.equalsIgnoreCase("Ja")) {continueShop = false;}

        }
        List<Orderitem> orderitems;
        orderitems = or.getAllorderItems(orderId);
        ou.displayOrderTotal(allShoes, orderitems, makers, shoeColours);

        System.out.println("Slutför order? (Ja/Nej)");
        String complete = shopScanner.nextLine();
        if (complete.equalsIgnoreCase("Ja")) {
            or.completeOrder(orderId);
            or.completeOrderSetDate(orderId);
            System.out.println("Din order är slutförd.");
        }
        else {
            System.out.println("Din order är mottagen och sparad för framtida modifikation.");
        }
    }
}
