package lab6;

import java.util.*;
import java.lang.*;

public class IntLinkedList {

    static final int max = 9999;
    static final int min = 1000;

    public static void printList(Iterator<Integer> itr, int x){
        String output = "";
        switch (x){
            case 0:
                output = "Before";
                break;
            case 1:
                output = "After";
                break;
            default:
                break;
        }
        System.out.print(output + ": <"+itr.next());
        while(itr.hasNext()){
            System.out.print(", "+itr.next());
        }
        System.out.println(">");
    }

    public static void printswapList(Iterator<Integer> itr, int x1, int x2){

        int y = itr.next();
        if(y == x2 || y == x1){
            System.out.print("Before" + ": <"+y+"*");
        }
        else{
            System.out.print("Before" + ": <"+y);
        }

        while(itr.hasNext()){
            int x = itr.next();
            if(x == x2 || x == x1){
                System.out.print(", "+x+"*");
            }
            else{
                System.out.print(", "+x);
            }
        }
        System.out.println(">");
    }

    public static void addAndSort(LinkedList<Integer> list, int value){

        System.out.println("Adding and Sorting " + value);

        Iterator<Integer> itr = list.iterator();
        printList(itr, 0);

        list.add(value);

        Collections.sort(list);

        Iterator<Integer> itr2 = list.iterator();
        printList(itr2, 1);

    }

    public static void swap(LinkedList<Integer> list, int value, int value2){

        int index1 = list.indexOf(value);
        int index2 = list.indexOf(value2);

        if (index1 == -1 || index2 == -1) {
            return;
        }

        System.out.println("Swapping values of " + value + " and " + value2);

        Iterator<Integer> itr = list.iterator();
        printswapList(itr, value, value2);

        list.set(index1, value2);
        list.set(index2, value);

        Iterator<Integer> itr2 = list.iterator();
        printList(itr2, 1);
    }

    public static void genRandom(LinkedList<Integer> list, int amount){
        for(int i = 0; i < amount; i++){
            int x = (int)(Math.random()*(max-min+1)+min);
            list.add(x);
        }
    }

    public static int findValue (LinkedList<Integer> list, int searchVal){

        int index2 = list.indexOf(searchVal);

        if (searchVal == -1) {
            return -1;
        }
        else{
            return index2;
        }
    }

    public static void main(String args[]) {
        LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.add(1);
        ll.add(3);
        ll.add(5);
        ll.add(7);
        ll.add(9);
        ll.add(11);

        Collections.sort(ll);
        addAndSort(ll, 8);
        System.out.println("");
        swap(ll, 8,1);


        System.out.println("");
        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        genRandom(ll2, 500);
        int index = -1;
        while(index == -1){
            int findingNumber = (int)(Math.random()*(max-min+1)+min);
            index = findValue(ll2, findingNumber);
            if(index == -1){
                System.out.println("Index for " + findingNumber + " cannot be found");
            }
            else{
                System.out.println("Index for " + findingNumber + " is " + index);
            }
        }
    }
}

