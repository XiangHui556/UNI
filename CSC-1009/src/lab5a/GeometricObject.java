package lab5a;

import java.util.Date;

public class GeometricObject {

    private String color = "White";
    private boolean filled = false;
    private long millis = System.currentTimeMillis();
    private java.util.Date dateCreated = new java.util.Date(this.millis);

    public GeometricObject() {

    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + this.color + " and filled: " + this.isFilled();
    }
}

