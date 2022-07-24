import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    void testMain_ShouldCompletedByTimeOut() throws Exception {
        Main.main(new String[0]);
    }
}
