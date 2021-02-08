import java.util.ArrayList;
import java.util.List;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    DecisionMethod goShopping =
            (name, discount) -> 
            "product1".equals(name) && discount > 10;
}

@FunctionalInterface
interface DecisionMethod {
    boolean decide(String name, int discountPercent);
}

class Shop {
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        return (int) clients.stream()
                .filter(client -> client.decide(product, percent))
                .count();
    }
}