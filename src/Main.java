import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        CustomerUtil cu = new CustomerUtil();
        CustomerRepo cr = new CustomerRepo();
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

        List<Stock> tempStock = new ArrayList<>();

        List<Shoe> allShoes = new ArrayList<>();
        List<Shoe> selectedShoes = new ArrayList<>();
        List<ShoeCategory> shoeCategories = new ArrayList<>();

        sr.getAllCategoriesForShoesFromDatabase(shoeCategories);
        sr.getAllShoesFromDatabase(allShoes); // get all shoes

        //selectedShoes = su.getAllShoesByCategory(allShoes, shoeCategories, shoeTypes, "Löparsko");

        //System.out.println(selectedShoes.size());

        //selectedShoes  = su.getAllshoesByMaker(allShoes, makers, "Altra");
        //selectedShoes = su.getAllshoesByColour(allShoes, shoeColours, "Svart");
        //selectedShoes = su.getAllshoesByPrice(allShoes, 800, 1000);
        //selectedShoes = su.getAllshoesBySize(allShoes, sizes,42);

        //su.displayMatchingShoesByCatergory(allShoes, selectedShoes, makers, shoeColours, sizes);

        /*
        selectedShoes.forEach(s-> System.out.println(s.getName() + " " + makers.get(s.getMaker()).getName()+ " "
                + shoeColours.get(s.getColour()).getColour() + " " + sizes.get(s.getSize()).getEuroSize() + " " + s.getPrice()));
        */

        //allShoes.forEach(s-> System.out.println(s.getName() + " " + makers.get(s.getMaker()).getName()+ " "
        //        + shoeColours.get(s.getColour()).getColour() + " " + sizes.get(s.getSize()).getEuroSize() + " " + s.getPrice()));

        Customer currentUser = null;
        // Ask if registered customer 1.Yes 2. No

        Boolean loopLogin = true;
        Boolean loopLoginInner;
        //Boolean passwordMatches;

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
        System.out.println();
        List<Orderitem> customerBasket = new ArrayList<>();
        //System.out.println("Vad vill du söka skor efter?");  // Ask how to search for shoes(brand, type, colour, price). Ask for input. show result. Show all?
        //System.out.println("Märke (1)\nFärg (2)\nKategori (3)\nStorlek (4)\nPris (5)\nVisa alla skor! (6)");
        /*
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default: su.displayAllShoes(allShoes,selectedShoes, makers, shoeColours, sizes );
        }
        */
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
            System.out.println(receiveQuantity);
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
                    System.out.println(shoesAdded);
                }
                System.out.println("Sko tillagd i varukorgen: " + makers.get(shoeToOrder.getMaker()).getName()
                        + ", " + shoeToOrder.getName() + ", antal " + shoesAdded);
            } else {
                System.out.println("Antalet par du vill beställa är större än antalet i lager. Vänligen gör ett nytt val.");
            }
            System.out.println("Vill du göra ett till val? (Ja/Nej");
            Scanner shopScanner = new Scanner(System.in);
            String addMoreItems = shopScanner.nextLine();
            if (!addMoreItems.equalsIgnoreCase("Ja")) {continueShop = false;}

        }
        //Metod visa varukorg och saldo
        List<Orderitem> orderitems = new ArrayList<>();
        orderitems = or.getAllorderItems(orderId);
        System.out.println(orderitems.size());
        ou.displayOrderTotal(allShoes, orderitems, makers, shoeColours); //MÅSTE FIXAS!!!!



        // Loop till customer is pleased.



        //Go to checkout. Review

        /*





        List<Customer> customers = new ArrayList<>();
                cr.getAllCustomersAsList(customers);






        allShoes.stream().forEach(s-> System.out.println(s.getName() + " " + makers.get(s.getMaker()).getName()+ " "
                + shoeColours.get(s.getColour()).getColour() + " " + sizes.get(s.getSize()).getEuroSize() + " " + s.getPrice()));

        //customers.stream().forEach(c -> System.out.println(c.getFirstName() + " " + c.getFamilyName()));

        Integer max = allShoes.stream().map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get();
        Integer min = allShoes.stream().map(Shoe::getSize).min(Comparator.comparing(Integer::valueOf)).get();

        System.out.println("Största storleken är : " + max + " " + sizes.get(max).getEuroSize());
        System.out.println("Minsta storleken är : " + min + " " + sizes.get(min).getEuroSize());
        */

        //


    }


}
