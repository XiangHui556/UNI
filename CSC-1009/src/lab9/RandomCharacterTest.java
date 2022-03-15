package lab9;

public class RandomCharacterTest {
    public static void main(String[] args) {
        RandomCharacter random = new RandomCharacter();
        System.out.println(random.getRandomLowerCaseLetter(10));
        System.out.println(random.getRandomUpperCaseLetter(10));
        System.out.println(random.getRandomDigitCharacter(10));
        System.out.println(random.getRandomCharacter(10));
    }
}

