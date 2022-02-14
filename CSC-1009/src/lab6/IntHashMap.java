package lab6;
import java.util.*;

public class IntHashMap {
    static final int max = 9999;
    static final int min = 1000;

    public static void printList(HashMap<Integer, Integer> itr, int x){
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
        output = output + ": <";
        for (Integer name: itr.keySet()) {
            String value = itr.get(name).toString();
            output = output + (value + ", ");
        }
        String output2 = output.substring(0, output.length() - 2);
        output2 = output2 + ">";
        System.out.println(output2);
    }

    public static void addAndSort(HashMap<Integer, Integer> itr, int value){
        printList(itr, 0);
        itr.put(8, 8);
        printList(itr, 1);
    }

    public static void genRandom(HashMap<Integer, Integer> itr, int amount){
        for(int i = 0; i < amount; i++){
            int x = (int)(Math.random()*(max-min+1)+min);
            itr.put(i, x);
        }
    }

    public static int findValue (HashMap<Integer, Integer> itr, int searchVal){
        int result = -1;
        if (itr.containsValue(searchVal)) {
            for (Map.Entry<Integer, Integer> entry : itr.entrySet()) {
                if (Objects.equals(entry.getValue(), searchVal)) {
                    return entry.getKey();
                }
                // we can't compare like this, null will throws exception
              /*(if (entry.getValue().equals(value)) {
                  result.add(entry.getKey());
              }*/
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        map.put(1, 1);
        map.put(3, 3);
        map.put(5, 5);
        map.put(7, 7);
        map.put(11, 11);
        map.put(9, 9);
        addAndSort(map, 8);

        genRandom(map2, 500);

        int index = -1;
        while(index == -1){
            int findingNumber = (int)(Math.random()*(max-min+1)+min);
            index = findValue(map2, findingNumber);
            if(index == -1){
                System.out.println("Key for " + findingNumber + " cannot be found");
            }
            else{
               System.out.println("Key for " + findingNumber + " is " + index);
            }
        }
    }
}
