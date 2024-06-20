import org.example.gamePlay.Validators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnterNickNameTest {

    @Mock
    private Scanner scanner;

    private final Validators validators = new Validators(scanner);

    @Test
    void enterNickTestNotEnoughtLength() {
        when(scanner.nextLine()).thenReturn("a");
        Assertions.assertThrows(IllegalArgumentException.class, () -> validators.enterNickName(scanner));
    }

    @Test
    void enterNickTestNotEnoughtLength1() {
        when(scanner.nextLine()).thenReturn("a");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> validators.enterNickName(scanner));
        String expectedMessage = " Your nickname is too short; it must be at least two characters long ";
        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void enterNickHappyathTest() {
        when(scanner.nextLine()).thenReturn("alex");
        String nick = "alex";
        Assertions.assertEquals(nick, validators.enterNickName(scanner));
    }


}

/*    public String enterNickName(Scanner scanner) {
        System.out.println(" Please enter your nick name : ");
        while (true) {
            String nickname = scanner.nextLine();
            if (nickname.length() > 1) {
                return nickname;
            } else {
                System.out.println(" Your nickname is too short; it must be at least two characters long ");
            }
        }
    }
    */

