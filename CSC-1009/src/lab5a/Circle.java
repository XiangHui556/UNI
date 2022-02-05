package lab5a;

public class Circle extends Shape {
    private double dim1, dim2;

    public Circle(double dim1, double dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    @Override
    public double area() {
        return pi*dim1*dim2;
    }
}
