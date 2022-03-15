package lab9;
import java.util.Random;

public class RandomCharacter {
    static final String CharacterSets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789@/!?";
    Random random = new Random();

    public String getRandomLowerCaseLetter(int amount){
        String output = "";
        for(int i = 0; i < amount; i++){
            char randomCharacter = (char) (random.nextInt(26) + 'a');
            output += randomCharacter;
        }
        return output;
    }
    public String getRandomUpperCaseLetter(int amount){
        String output = "";
        for(int i = 0; i < amount; i++){
            char randomCharacter = (char) (random.nextInt(26) + 'A');
            output += randomCharacter;
        }
        return output;
    }
    public String getRandomDigitCharacter(int amount){
        String output = "";
        for(int i = 0; i < amount; i++){
            int randomInt = random.nextInt(10);
            output += randomInt;
        }
        return output;
    }
    public String getRandomCharacter(int amount){
        String output = "";
        for(int i = 0; i < amount; i++){
            int randomInt = random.nextInt(CharacterSets.length());
            char randomChar = CharacterSets.charAt(randomInt);
            output += randomChar;
        }
        return output;
    }
}
