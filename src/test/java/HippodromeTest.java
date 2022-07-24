import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith({MockitoExtension.class})
public class HippodromeTest {

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenParamIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenParamIsNull() {
        String expected = "Horses cannot be null.";
        String actual = null;
        try {
            new Hippodrome(null);
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldIllegalArgumentException_WhenParamIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );
    }

    @Test
    void testConstructor_ShouldExceptionMassage_WhenParamIsEmpty() {
        String expected = "Horses cannot be empty.";
        String actual = null;
        try {
            new Hippodrome(Collections.emptyList());
        } catch (Exception exception) {
            actual = exception.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    void testGetHorses_ShouldReturn_ListParamOfConstructor() {
        List<Horse> expected = new ArrayList<>();
        for (int horseNumber = 1; horseNumber <= 30; horseNumber++) {
            expected.add(new Horse("Name" + horseNumber, horseNumber, horseNumber));
        }
        Hippodrome hippodrome = new Hippodrome(new ArrayList<>(expected));
        assertEquals(expected, hippodrome.getHorses());
    }

    @Test
    void testMove_ShouldCallMove_ForAllHorses () {
        Horse mockHorse = Mockito.mock(Horse.class);
        List<Horse> horses = new ArrayList<>();
        for (int horseNumber = 1; horseNumber <= 50; horseNumber++) {
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(mockHorse, times(50)).move();
    }

    @Test
    void testGetWinner_ShouldReturn_HorseWithHighestValueOfDistance() {
        Horse expected = new Horse("Winner", 1, 100);

        List<Horse> horses = new ArrayList<>();
        for (int horseNumber = 1; horseNumber <= 99; horseNumber++) {
            if (horseNumber == 50) {
                horses.add(expected);
            }
            horses.add(new Horse("Name" + horseNumber, horseNumber, horseNumber));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(expected, hippodrome.getWinner());
    }
}
