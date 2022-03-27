package lab9;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;

import java.security.InvalidParameterException;

public class RandomCharacterTest {

    RandomCharacter random;

    @BeforeEach
    void setUp() {
        random = new RandomCharacter();
    }

    @Test
    @DisplayName("Simple Check if random char is within range")
    void testOnce() {
        int upperLetter = (int) random.getRandomUpperCaseLetter();
        int lowerLetter = (int) random.getRandomLowerCaseLetter();
        int charLetter = (int) random.getRandomCharacter();
        int num = random.getRandomDigitCharacter();
        int upperCaseAAsciiCode = 65;
        int upperCaseZAsciiCode = 90;
        int lowerCaseAAsciiCode = 97;
        int lowerCaseZAsciiCode = 122;
        Assertions.assertTrue(upperLetter >= upperCaseAAsciiCode && upperLetter <= upperCaseZAsciiCode, "Fail");
        Assertions.assertTrue(lowerLetter >= lowerCaseAAsciiCode && lowerLetter <= lowerCaseZAsciiCode, "Fail");
        Assertions.assertTrue(charLetter >= 0 && charLetter <= 127, "Fail");
        Assertions.assertTrue(num >= 0 && num <= 9, "Fail");
        Assertions.assertTrue(num == 1 || num == 2 || num == 3 || num == 5 || num == 7, "Not Prime " + num);
    }

    @Test
    @DisplayName("Test 10 times ")
    void test10Times() {
        int upperLetter = (int) random.getRandomUpperCaseLetter();
        int lowerLetter = (int) random.getRandomLowerCaseLetter();
        int charLetter = (int) random.getRandomCharacter();
        int num = random.getRandomDigitCharacter();
        int upperCaseAAsciiCode = 65;
        int upperCaseZAsciiCode = 90;
        int lowerCaseAAsciiCode = 97;
        int lowerCaseZAsciiCode = 122;
        Assertions.assertTrue(upperLetter >= upperCaseAAsciiCode && upperLetter <= upperCaseZAsciiCode, "Fail");
        Assertions.assertTrue(lowerLetter >= lowerCaseAAsciiCode && lowerLetter <= lowerCaseZAsciiCode, "Fail");
        Assertions.assertTrue(charLetter >= 0 && charLetter <= 127, "Fail");
        Assertions.assertTrue(num >= 0 && num <= 9, "Fail");
    }

}

