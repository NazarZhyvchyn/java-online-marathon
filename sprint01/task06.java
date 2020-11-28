
class Product {
    private String name;
    private double price;
    private static int instanceCounter;
    {
         instanceCounter++;
    }
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
  
    
    public Product() {
         

    }
    public static int getInstanceCounter()
{
    return instanceCounter;
}

    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public static int count() {
        
    return instanceCounter;
}
    }
