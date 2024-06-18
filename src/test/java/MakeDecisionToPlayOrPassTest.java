import org.example.gamePlay.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Scanner;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MakeDecisionToPlayOrPassTest {

    @Mock
    private Scanner scanner;

    private final Validators validators = new Validators(scanner);

    @Test
    void makeDecisionHappyPathTest() {
        when(scanner.nextLine()).thenReturn("p");
        String decision = validators.makeDecisionToPlayOrPass(scanner);
        String expectedString = "p";
        Assertions.assertEquals(decision, expectedString);
    }

    @Test
    void makeDecisionHappyPathTest1() {
        when(scanner.nextLine()).thenReturn("c");
        String decision = validators.makeDecisionToPlayOrPass(scanner);
        String expectedString = "c";
        Assertions.assertEquals(decision, expectedString);
    }

    @Test
    void makeDecisionTwoTimeWrongLetterTest() {
        when(scanner.nextLine()).thenReturn("x", "g", "p");
        String decision = validators.makeDecisionToPlayOrPass(scanner);
        String expectedString = "p";
        Assertions.assertEquals(decision, expectedString);
    }
}
