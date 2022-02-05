package lab5a;

public class TestShapes {
    public static void main(String[] args){
        Rectangle r = new Rectangle(9, 5);
        Triangle t = new Triangle(10, 8);
        Circle c = new Circle(5, 5);
        Ellipse e = new Ellipse(7, 7);
        Square s = new Square(6, 6);
        Shape figref;
        figref = r;
        System.out.println("\nInside Area for Rectangle.");
        System.out.println("Area is " + figref.area());
        figref = t;
        System.out.println("\nInside Area for Triangle.");
        System.out.println("Area is " + figref.area());
        figref = c;
        System.out.println("\nInside Area for Circle.");
        System.out.println("Area is " + figref.area());
        figref = e;
        System.out.println("\nInside Area for Ellipse.");
        System.out.println("Area is " + figref.area());
        figref = s;
        System.out.println("\nInside Area for Square.");
        System.out.println("Area is " + figref.area());
    }
}
