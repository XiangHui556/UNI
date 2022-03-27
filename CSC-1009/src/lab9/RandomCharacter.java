package lab9;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomCharacter {
    static final String CharacterSets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789@/!?";
    Random random = new Random();

    public char getRandomLowerCaseLetter(){
        char randomCharacter = (char) (random.nextInt(26) + 'a');
        return randomCharacter;
    }
    public char getRandomUpperCaseLetter(){
        char randomCharacter = (char) (random.nextInt(26) + 'A');
        return randomCharacter;
    }
    public int getRandomDigitCharacter(){
        int randomInt = random.nextInt(10);
        return randomInt;
    }
    public char getRandomCharacter(){
        int randomInt = random.nextInt(CharacterSets.length());
        char randomChar = CharacterSets.charAt(randomInt);
        return randomChar;
    }
    public static void main(String[] args){
        RandomCharacter random = new RandomCharacter();
        for(int i = 0; i < 15; i++){
            System.out.print(random.getRandomLowerCaseLetter());
        }
        System.out.println();
        for(int i = 0; i < 15; i++){
            System.out.print(random.getRandomUpperCaseLetter());
        }
        System.out.println();
        for(int i = 0; i < 15; i++){
            System.out.print(random.getRandomDigitCharacter());
        }
        System.out.println();
        for(int i = 0; i < 15; i++){
            System.out.print(random.getRandomCharacter());
        }
    }
}
