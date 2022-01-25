package lab4;

import java.util.Scanner;

public class StackOfIntegers {
    private int[] elements;
    private int size;

    public StackOfIntegers() {
        this.elements = new int[16];
        this.size = -1;
    }
    public StackOfIntegers(int size) {
        this.elements = new int[size];
        this.size = -1;
    }

    public boolean empty(){
        if(this.size < 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int peek(){
        return(this.elements[this.size]);
    }

    public void push(int value){
        this.size += 1;
        this.elements[this.size] = value;
    }

    public int pop(){
        if(this.size < 0){
            return 0;
        }
        else{
            int temp = this.elements[this.size];
            this.size -= 1;
            return temp;
        }
    }

    public int getSize(){
        return this.size;
    }
}
