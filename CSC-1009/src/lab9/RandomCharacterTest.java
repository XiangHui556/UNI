package lab9;
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
    @DisplayName("Simple 10 Int should work")
    void testWithInt10() {
        assertNotNull(random.getRandomLowerCaseLetter(10), "Regular int of 10 should work");
        assertNotNull(random.getRandomUpperCaseLetter(10), "Regular int of 10 should work");
        assertNotNull(random.getRandomDigitCharacter(10), "Regular int of 10 should work");
        assertNotNull(random.getRandomCharacter(Integer.parseInt("10")), "parseInt of 10 should work");
    }

    @Test
    @DisplayName("Simple Combination")
    void testAllMethodCombine() {
        assertNotNull((random.getRandomCharacter(5) + random.getRandomDigitCharacter(5) + random.getRandomLowerCaseLetter(5) + random.getRandomUpperCaseLetter(5)), "Combine should work");
    }

    @Test
    @DisplayName("Wrong Parameter")
    void testStringParameter() {
        try{/*
            assertNotNull(random.getRandomLowerCaseLetter("10"), "Should Give Error");
            assertNotNull(random.getRandomUpperCaseLetter("10"), "Should Give Error");
            assertNotNull(random.getRandomDigitCharacter("10"), "Should Give Error");
            assertNotNull(random.getRandomCharacter("10"), "Should Give Error");*/
            //Complier don't allow
        }
        catch (AssertionError e){
            System.out.println(e.getMessage());
        }
    }

}

