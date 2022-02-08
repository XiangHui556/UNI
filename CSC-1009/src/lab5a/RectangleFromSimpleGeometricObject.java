package lab5a;

public class RectangleFromSimpleGeometricObject extends GeometricObject{
    private double width;
    private double height;

    public RectangleFromSimpleGeometricObject() {

    }

    public RectangleFromSimpleGeometricObject(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public RectangleFromSimpleGeometricObject(double width, double height, String color, boolean filled) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return (this.height * this.width);
    }

    public double getPerimeter() {
        return ((this.height * 2) + (this.width * 2));
    }
}
