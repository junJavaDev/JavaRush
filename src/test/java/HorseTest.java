import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenFirstParamIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenFirstParamIsNull() {
        String expected = "Name cannot be null.";
        String actual = null;
        try {
            new Horse(null, 1, 1);
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\r", "\n", "\f", "\s"})
    void testConstructor_ShouldIllegalArgumentException_WhenFirstParamIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\r", "\n", "\f", "\s"})
    void testConstructor_ShouldExceptionMassage_WhenFirstParamIsBlank(String name) {
        String expected = "Name cannot be blank.";
        String actual = null;
        try {
            new Horse(name, 1, 1);
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenSecondParamIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", -1, 1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenSecondParamIsNegative() {
        String expected = "Speed cannot be negative.";
        String actual = null;
        try {
            new Horse("Name", -1, 1);
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenThirdParamIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", 1, -1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenThirdParamIsNegative() {
        String expected = "Distance cannot be negative.";
        String actual = null;
        try {
            new Horse("Name", 1, -1);
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    void testGetName_ShouldReturn_FirstParamOfConstructor() {
        String expected = "Name";
        Horse horse = new Horse(expected, 1, 1);
        assertEquals(expected, horse.getName());
    }

    @Test
    void testGetSpeed_ShouldReturn_SecondParamOfConstructor() {
        double expected = 13;
        Horse horse = new Horse("Name", expected, 1);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    void testGetDistance_ShouldReturn_ThirdParamOfConstructor() {
        double expected = 13;
        Horse horse = new Horse("Name", 1, expected);
        assertEquals(expected, horse.getDistance());
    }

    @Test
    void testGetDistance_ShouldReturnZero_WhenConstructorHasTwoParams() {
        double expected = 0;
        Horse horse = new Horse("Name", 1);
        assertEquals(expected, horse.getDistance());
    }
}
