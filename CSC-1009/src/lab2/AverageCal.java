package lab2;
import java.util.*;

public class AverageCal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        String input = sc.nextLine();
        List<String> split = new ArrayList<String>();
        split = Arrays.asList(input.split(" "));
        ArrayList<Float> values = new ArrayList<Float>();
        int splitLen = split.size();
        for(int i=0;i < splitLen; i++) {
            values.add(Float.parseFloat(split.get(i)));
        }
        int arrayLength = values.size();
        while(arrayLength < 3){
            input = sc.nextLine();
            split = Arrays.asList(input.split(" "));
            splitLen = split.size();
            for(int i=0;i < splitLen; i++) {
                values.add(Float.parseFloat(split.get(i)));
            }
            arrayLength = values.size();
        }
        float mean = 0;
        String output = "";
        for(int i = 0; i < arrayLength; i++){
            mean = mean + values.get(i);
            output = output + " " + values.get(i);
        }
        mean = mean/arrayLength;
        System.out.println("The Average of" + output + " is " + mean);
    }
}
