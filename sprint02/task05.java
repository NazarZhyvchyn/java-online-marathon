import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class Rectang extends Figure {
    // Code
    public double height;
    public double width;
      public Rectang() {
    }

    public Rectang(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    
    public double getPerimeter() {
    double perimeter = 2*(width+height);
    return perimeter;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectang rectang = (Rectang) o;
        return Double.compare(rectang.height, height) == 0 &&
                Double.compare(rectang.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
}
class Square extends Figure {
    public double width;
    public Square(double width) {
        this.width = width;
    }
    public Square() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    public double getPerimeter() {
      double perimeter = width*4;
    return perimeter;
    
}
 @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width);
    }
}
abstract class Figure {
    abstract double getPerimeter();
}
public class MyUtils {
        public double sumPerimeter(List<?> firures) {
            double sum=0;
            
                for (int i = 0; i < firures.size(); i++) {
                    if (firures.get(i) instanceof Square){
                        sum+=((Square) firures.get(i)).getPerimeter();
                    }
                    if (firures.get(i) instanceof Rectang){
                        sum+=((Rectang) firures.get(i)).getPerimeter();
                    }
                    else {
                        continue;
                    }
                }     return sum;       }
            
        }
    
