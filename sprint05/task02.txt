//type your code here
enum Color {WHITE, RED, BLUE}

enum Type {RARE, ORDINARY}
class ColorException extends Exception {
    public ColorException(String message) {
        super(message);
    }
}

class TypeException extends Exception {
    public TypeException(String message) {
        super(message);
    }
}
class Plant{
    private String name;
    Color color;
    Type type;
    
    public Plant(String type, String color, String name) throws ColorException, TypeException{
        this.name = name;
         try {
            this.color = Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ColorException("Invalid value " + color + " for field color");
        }
        try {
            this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeException("Invalid value " + type + " for field type");
        }
    }
    
    
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("{type: %s, color: %s, name: %s}",
                type, color, name);
    }
}
