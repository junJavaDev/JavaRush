import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;

public class HorseTest {

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenNameParamIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenNameParamIsNull() {
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
    void testConstructor_ShouldIllegalArgumentException_WhenNameParamIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\r", "\n", "\f", "\s"})
    void testConstructor_ShouldExceptionMassage_WhenNameParamIsBlank(String name) {
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
    void testConstructor_ShouldIllegalArgumentException_WhenSpeedParamIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", -1, 1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenSpeedParamIsNegative() {
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
    void testConstructor_ShouldIllegalArgumentException_WhenDistanceParamIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", 1, -1)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenDistanceParamIsNegative() {
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
    void testGetName_ShouldReturn_NameParamOfConstructor() {
        String expected = "Name";
        Horse horse = new Horse(expected, 1, 1);
        assertEquals(expected, horse.getName());
    }

    @Test
    void testGetSpeed_ShouldReturn_SpeedParamOfConstructor() {
        double expected = 13;
        Horse horse = new Horse("Name", expected, 1);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    void testGetDistance_ShouldReturn_DistanceParamOfConstructor() {
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

    @Test
    void testMove_ShouldCallGetRandomDouble_WithParams02And09() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 1);
            horse.move();
            horseMockedStatic.verify(
                    () -> Horse.getRandomDouble(0.2, 0.9),
                    times(1)
            );
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 0.2, 2.2",
            "10, 20, 0.5, 25",
            "0.1, 0.5, 0.9, 0.59"
    })
    void testMove_ShouldAssignValidValueToDistance(double speed, double distance, double randomDouble, double expected) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic
                    .when(() -> Horse.getRandomDouble(anyDouble(), anyDouble()))
                    .thenReturn(randomDouble);
            Horse horse = new Horse("Name", speed, distance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
