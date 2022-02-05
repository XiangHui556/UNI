package lab5a;

public class Rectangle extends Shape {
    private double dim1, dim2;

    public Rectangle(double dim1, double dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    @Override
    public double area() {
        return dim1*dim2;
    }
}
