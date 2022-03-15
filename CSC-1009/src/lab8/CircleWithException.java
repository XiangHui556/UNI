package lab8;

import java.util.Scanner;

public class CircleWithException {
    private double radius;

    public CircleWithException(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
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

    public static class TooBigException extends Exception
    {
        public TooBigException(String message)
        {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            try {
                System.out.print("Enter radius of the circle: ");
                String input = sc.nextLine();
                float radius = Float.parseFloat(input);

                if (radius < 0.f || radius == 0) {
                    throw new IllegalArgumentException("Non positive number or 0!");
                } else if (radius > 1000) {
                    throw new TooBigException("Radius is larger than 1000");
                } else {
                    CircleWithException circle = new CircleWithException(radius);
                    System.out.println("The Area of the circle is " + circle.getArea());
                    loop = false;
                }
            } catch (IllegalArgumentException | ArithmeticException | TooBigException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        }
    }
}
