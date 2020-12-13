public static Plant tryCreatePlant(String type, String color, String name) {
    
    try{
        return new Plant(type, color, name);
    }
        catch(ColorException e) {
            return tryCreatePlant(type, "Red", name);
            
        }
        catch(TypeException e) {
            return tryCreatePlant("Ordinary", color, name);
        }
    }
    
