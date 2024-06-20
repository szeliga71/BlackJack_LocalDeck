import org.example.mappers.DeckMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAmountOfCardsInDeckTest {

    private final DeckMapper deckMapper = new DeckMapper();

    @Test
    void getAmountOfCardsInDeckNullJsonTest() {
        Assertions.assertThrows(RuntimeException.class, () -> deckMapper.getAmountOfCardsInDeck(null));
    }

    @Test
    void getAmountOfCardsInDeckEmptyJsonTest() {
        Assertions.assertThrows(RuntimeException.class, () -> deckMapper.getAmountOfCardsInDeck(""));
    }

    @Test
    void getAmountOfCardsInDeckInvalidJsonTest() {
        Assertions.assertThrows(RuntimeException.class, () -> deckMapper.getAmountOfCardsInDeck("xxxx,hzfFgg"));
    }

    @Test
    void getAmountOfCardsInDeckHappyPathTest() {
        String responseJson = "{" +
                "\"success\": true," +
                " \"deck_id\": \"3p40paa87x90\"," +
                " \"shuffled\": true," +
                " \"remaining\": 208" +
                "}";
        Assertions.assertEquals(208, deckMapper.getAmountOfCardsInDeck(responseJson));
    }

}

