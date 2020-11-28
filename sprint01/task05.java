
class Point {
    private int x;
    private int y;
    
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int[] getXYPair() {
        int[] array = {x, y};
    return array;
}
public double distance(int x, int y) {
   int dx = x - this.x;
        int dy = y - this.y;
        return Math.sqrt(dx*dx + dy*dy);
}
public double distance(Point point) {
    int dx = x - point.x;
        int dy = y - point.y;
        return Math.sqrt(dx*dx + dy*dy);
}
public double distance() {
return Math.sqrt(x*x + y*y);}
}