import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShoeUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<Shoe> getAllShoesByCategory(List<Shoe> allShoes, List<ShoeCategory> shoeCategories, Map<Integer, ShoeType> shoeTypes, String category) {
        List<Integer> matchingShoes;
        List<Shoe> selectedShoes = new ArrayList<>();
        matchingShoes = shoeCategories.stream()
                .filter(sc-> shoeTypes.get(sc.getShoeType()).getName().equals(category)).map(ShoeCategory::getShoe).collect(Collectors.toList());

        for (Integer i : matchingShoes) {
            for (Shoe s : allShoes) {

                if (s.getId() == i) {
                    selectedShoes.add(s);
                }
            }
        }
        return selectedShoes;
    }

    public List<Shoe> getAllshoesByMaker(List<Shoe> allShoes, Map<Integer, ShoeMaker> shoeMakers, String maker) {
        return allShoes.stream()
                .filter(sc-> shoeMakers.get(sc.getMaker()).getName().equals(maker)).collect(Collectors.toList());
    }
    public List<Shoe> getAllshoesByColour(List<Shoe> allShoes, Map<Integer, ShoeColour> shoeColours,String colour) {

        return allShoes.stream()
                .filter(sc-> shoeColours.get(sc.getColour()).getColour().equals(colour)).collect(Collectors.toList());
    }
    public List<Shoe> getAllshoesByPrice(List<Shoe> allShoes, int minPrice, int maxPrice) {
        return allShoes.stream()
                .filter(s-> s.getPrice() >= minPrice && s.getPrice() <= maxPrice).collect(Collectors.toList());

    }
    public List<Shoe> getAllshoesBySize(List<Shoe> allShoes, Map<Integer, ShoeSize> sizes, int size) {
        return allShoes.stream()
                .filter(s-> sizes.get(s.getSize()).getEuroSize() == size).collect(Collectors.toList());

    }

    public void displayMatchingShoesByCatergory(List<Shoe> allShoes, List<Shoe> searchHits,
                                                Map<Integer, ShoeMaker> shoeMakers, Map<Integer, ShoeColour> shoeColours, Map<Integer, ShoeSize> sizes) {

        List<Shoe> shoesForDisplay;

        Integer maxSize = searchHits.stream().map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get();
        Integer minSize = searchHits.stream().map(Shoe::getSize).min(Comparator.comparing(Integer::valueOf)).get();


        shoesForDisplay = searchHits.stream()
                .filter(distinctByKey(s -> s.getName())).collect(Collectors.toList());


        for (Shoe s : shoesForDisplay) {
            System.out.println("Största storlek" + allShoes.stream().filter(sc -> sc.getName().equals(s.getName())).map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get());
                    System.out.println("Minsta storlek" + allShoes.stream().filter(sc -> sc.getName().equals(s.getName())).map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get());
        }
        shoesForDisplay.forEach(s ->System.out.println(s.getName() + " " + shoeMakers.get(s.getMaker()).getName()+ " "
                + shoeColours.get(s.getColour()).getColour()+ " " + s.getPrice() + " kr" + "Finns i storlek " +
                allShoes.stream().filter(sc -> sc.getName().equals(s.getName())).map(Shoe::getSize).min(Comparator.comparing(Integer::valueOf)).get() +
                         " till " +allShoes.stream().filter(sc -> sc.getName().equals(s.getName())).map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get()));


    }

    public void displayAllShoes(List<Shoe> allShoes, List<Shoe> searchHits,
                                Map<Integer, ShoeMaker> shoeMakers, Map<Integer, ShoeColour> shoeColours, Map<Integer, ShoeSize> sizes) {
        List<Shoe> shoesForDisplay;
        ShoeRepo shoeRepo = new ShoeRepo();
        shoesForDisplay = allShoes.stream()
                .filter(distinctByKey(s -> s.getName())).collect(Collectors.toList());
        System.out.println("Följande skor finns");
        System.out.println("-------------------------------------------------------");
        System.out.println("Märke\t\tModell\t\tFärg\t\tPris\t\tStorlekar\t\tMedelbetyg\t\tOmdöme");
        shoesForDisplay.forEach(s -> {
                    System.out.print(shoeMakers.get(s.getMaker()).getName() + ", " + s.getName() + ", "
                            + shoeColours.get(s.getColour()).getColour() + ", " + s.getPrice() + " kr." + " Finns i storlek " +
                            sizes.get(allShoes.stream().filter(sc -> sc.getName().equals(s.getName()))
                                    .map(Shoe::getSize).min(Comparator.comparing(Integer::valueOf)).get()).getEuroSize() +
                            " till " + sizes.get(allShoes.stream().filter(sc -> sc.getName().equals(s.getName()))
                            .map(Shoe::getSize).max(Comparator.comparing(Integer::valueOf)).get()).getEuroSize()+ "\t");
                    shoeRepo.displayVerdictForShoeModel(s);
                }

        );
        System.out.println("-------------------------------------------------------");
    }

    public Shoe getTheShoeToBuy (List<Shoe> allShoes, List<Shoe> searchHits,
                                     Map<Integer, ShoeMaker> shoeMakers, Map<Integer, ShoeColour> shoeColours, Map<Integer, ShoeSize> sizes, String modelName){
        Shoe temp = null;
        List<Shoe> shoesForDisplay;

        shoesForDisplay = allShoes.stream()
                .filter(distinctByKey(s -> s.getName())).collect(Collectors.toList());

        for ( Shoe s: shoesForDisplay) {
            if (s.getName().equalsIgnoreCase(modelName)) {temp = s;}
        }
        return temp;
    }

    public String receiveShoeModelFromInput() {
        Scanner scanner = new Scanner(System.in);
        String whatShoe = "";
        System.out.println("Vilken sko vill du beställa? (Ange modellnamn):");
        //System.out.println("-------------------------------------------------");
        return whatShoe = scanner.nextLine();
    }

    public void displaySizesAndStockForModel (List<Stock> stockForModel, Shoe tempShoe, List<Shoe> allShoes, Map<Integer, ShoeMaker> shoeMakers, Map<Integer, ShoeSize> sizes) {

        System.out.println(shoeMakers.get(tempShoe.getMaker()).getName() + " " + tempShoe.getName() + " finns i följande storlekar och antal: ");


        for (Stock stock: stockForModel) {
            for (Shoe s : allShoes) {

                if (s.getId() == stock.getShoe()) {
                    if (stock.getInStock() >= 1) System.out.println("Storlek " + sizes.get(s.getSize()).getEuroSize() + " finns " + stock.getInStock() + " i lager");
                }
            }
        }
        System.out.println("-------------------------------------------------");
    }


}
