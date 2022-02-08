package lab5a;

public class CircleFromSimpleGeometricObject extends GeometricObject{
    private double radius;

    public CircleFromSimpleGeometricObject() {

    }

    public CircleFromSimpleGeometricObject(double radius) {
        this.radius = radius;
    }

    public CircleFromSimpleGeometricObject(double radius, String color, boolean filled) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return (Math.PI * Math.pow(this.radius, 2));
    }

    public double getPerimeter() {
        return (2 * Math.PI * this.radius);
    }

    public double getDiameter() {
        return (2 * this.radius);
    }

    public void printCircle() {
    }
}
