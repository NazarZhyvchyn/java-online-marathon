import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

interface DrinkReceipt {
    // Code
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}
interface DrinkPreparation {
    // Code
    Map<String, Integer> makeDrink();
}
interface Rating {
    // Code
     int getRating();
}
class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    // Code
    private String name;
    private int rating;
    private Map<String, Integer> ingredients = new HashMap<String, Integer>();
    public Map<String,Integer> makeDrink() {
        addComponent("Water", 100).addComponent("Arabica", 20);

        return ingredients;
    }
    
    public DrinkReceipt addComponent(String componentName, int componentCount) {
            ingredients.put(componentName, componentCount);

        return this;
    }
    
    public String getName() {
        return name;
    }
    public int getRating() {
        return rating;
    }
     public Caffee() {

    }
    
    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }
    public Caffee(String name, int rating, Map<String, Integer> ingredients) {
        this.name = name;
        this.rating = rating;
        this.ingredients = ingredients;
    }
    
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caffee caffee = (Caffee) o;
        return rating == caffee.rating &&
                Objects.equals(name, caffee.name) &&
                Objects.equals(ingredients, caffee.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, ingredients);
    }

    
    
}
class Espresso extends Caffee {
    private Map<String, Integer> espressoIngredients = new HashMap<String, Integer>();
    // Code
    @Override
    public Map<String,Integer> makeDrink() {
        espressoIngredients.put("Water", 50);
        espressoIngredients.put("Arabica", 20);
        return espressoIngredients;
    }
     public Espresso(String name, int rating, Map<String, Integer> espressoIngredients) {
        super(name, rating, espressoIngredients);
        
    }

   
    
    public Espresso() {
        super();
    }
    public Espresso(String name, int rating) {
        super(name, rating);
    }
    
               @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                if (!super.equals(o)) return false;
                Espresso espresso = (Espresso) o;
                return Objects.equals(espressoIngredients, espresso.espressoIngredients);
        }

        @Override
        public int hashCode() {
                return Objects.hash(super.hashCode(), espressoIngredients);
        }
}
class Cappuccino extends Caffee {
    // Code
    private Map<String, Integer> cappuccinoIngredients = new HashMap<String, Integer>();
    @Override
    public Map<String,Integer> makeDrink() {
        cappuccinoIngredients.put("Water", 100);
        cappuccinoIngredients.put("Arabica", 20);
        cappuccinoIngredients.put("Milk", 50);
        return cappuccinoIngredients;

        
    }
     public Cappuccino(String name, int rating) {
        super(name, rating);
    }
     public Cappuccino(String name, int rating, Map<String, Integer> ingredients, Map<String, Integer> cappuccinoIngredients) {
        super(name, rating, ingredients);
        this.cappuccinoIngredients = cappuccinoIngredients;
    }
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cappuccino that = (Cappuccino) o;
        return Objects.equals(cappuccinoIngredients, that.cappuccinoIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cappuccinoIngredients);
    }
}

public class MyUtils {
    public Map<String, Double> averageRating (List<Caffee> coffees) {
       
            Map<String, Double> averageRating = new HashMap<String, Double>();

            double caffeeRating = 0;
            double cappuccinoRating = 0;
            double espressoRating = 0;
            int caffeeCount = 0;
            int cappuccinoCount = 0;
            int espressoCount = 0 ;


            for (int i = 0; i < coffees.size(); i++) {
                
                if (coffees.get(i) instanceof Cappuccino) {
                    cappuccinoRating =+(coffees.get(i)).getRating();
                    cappuccinoCount ++;
                }
                if (coffees.get(i) instanceof Espresso) {
                    espressoRating =+(coffees.get(i)).getRating();
                    espressoCount ++;
                }
                 else {
                    caffeeRating =+coffees.get(i).getRating();
                    caffeeCount ++;
                }

                }


            if(espressoRating != 0.0){
             averageRating.put("Espresso", espressoRating );
         }
        if(cappuccinoRating != 0.0){
            averageRating.put("Cappuccino", cappuccinoRating );
        }
        if(caffeeRating != 0.0){
            averageRating.put("Caffee", caffeeRating  );
        }



            return averageRating;
        }
    }

