package lab5a;

public abstract class Shape {
    double pi;
    double dim1;
    double dim2;

    public Shape(double dim1, double dim2) {
        this.pi = Math.PI;
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    public Shape() {
        this.pi = Math.PI;
    }

    public abstract double area();
}

