package lab4;

import java.util.Scanner;

public class StackOfIntegers {
    private int[] elements;
    private int size;

    public StackOfIntegers() {
        this.size = 16;
        this.elements = new int[this.size];
    }
    public StackOfIntegers(int size) {
        this.size = size;
        this.elements = new int[this.size];
    }

    public boolean empty(){
        if(elements.length == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int peek(){
        return(this.elements[this.elements.length-1]);
    }

    public void push(int value){
        this.elements[this.elements.length-1] = value;

        System.out.println(value);
        System.out.println(this.elements[this.elements.length-1]);
    }

    public int pop(){
        int temp = this.elements[this.elements.length-1];
        int[] copy = new int[this.elements.length-1];
        for (int i = 0, j = 0; i < this.elements.length; i++) {
            if (i != this.elements.length-2) {
                copy[j++] = this.elements[i];
            }
        }
        return temp;
    }

    public int getSize(){
        return elements.length;
    }
}
