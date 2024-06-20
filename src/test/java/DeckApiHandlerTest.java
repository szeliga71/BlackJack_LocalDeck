import org.example.api.DeckApiHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;

@ExtendWith(MockitoExtension.class)
public class DeckApiHandlerTest {

    private final DeckApiHandler handler = new DeckApiHandler();


    @Test
    void getDeckHappyPath() {
        Assertions.assertNotNull(handler.getShuffledDecksIdInResponseFromDeckSource(5));
    }

    @Test
    void getDecksHappyPath1() {
        Assertions.assertEquals(200, handler.getShuffledDecksIdInResponseFromDeckSource(6).statusCode());
    }

    @Test
    void getDecksTooManyNumbersOfDeck() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getShuffledDecksIdInResponseFromDeckSource(10).statusCode());
    }

    @Test
    void getDecksArgumentZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getShuffledDecksIdInResponseFromDeckSource(0));
    }

    @Test
    void getDecksInvalidArgumentNegativeValue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getShuffledDecksIdInResponseFromDeckSource(-5));
    }

    @Test
    void getDecksInvalidArgumentTooBigValue() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getShuffledDecksIdInResponseFromDeckSource(100));
    }

    @Test
    void drawCardsIncorrectFirstArgument() {

        Assertions.assertThrows(RuntimeException.class, () -> handler.getCardsInResponseFromDeckSource("xxxx", 2));
    }

    @Test
    void drawCardsIncorrectBothArguments() {

        Assertions.assertThrows(RuntimeException.class, () -> handler.getCardsInResponseFromDeckSource("xxxx", 600).statusCode());
    }

    @Test
    void drawCardsIncorrectBothArguments1() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getCardsInResponseFromDeckSource("xxxx", -25));
    }

    @Test
    void drawCardsIncorrecFirstArgument1() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getCardsInResponseFromDeckSource("", 1));
    }

    @Test
    void drawCardsIncorrectFirstArgument2() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.getCardsInResponseFromDeckSource(null, 2));
    }
}
