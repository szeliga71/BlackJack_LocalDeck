import com.fasterxml.jackson.databind.JsonNode;
import org.example.mappers.CardMapper;
import org.example.model.Card;
import org.example.model.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParseCardsListTest {

    CardMapper cardMapper = new CardMapper();

    @Test
    void parseCardsListNullArgumentTest() {
        Assertions.assertThrows(RuntimeException.class, () -> cardMapper.parseCardsList(null));
    }

    @Test
    void parseCardsListEmptyArgumentTest() {
        Assertions.assertThrows(RuntimeException.class, () -> cardMapper.parseCardsList(""));
    }

    @Test
    void parseCardsListIllegalArgumentTest() {
        Assertions.assertThrows(RuntimeException.class, () -> cardMapper.parseCardsList("dejsh"));
    }

    @Test
    void parseCardsListIllegalArgumentTest1() {
        String responseJson = "{\n" +
                "\"success\": true,\n" +
                "\"deck_id\": \"kxozasf3edqu\",\n" +
                "\"cards\": \"card\",\n" +
                "\"remaining\": 20\n" +
                "}";
        Assertions.assertThrows(IllegalArgumentException.class, () -> cardMapper.parseCardsList(responseJson));
    }

    @Test
    void parseCardsListHappyPathTest() {

        Card[] cardsTest = new Card[2];
        cardsTest[0] = new Card(6, Suit.HEARTS);
        cardsTest[1] = new Card(5, Suit.SPADES);


        String responseJson = "{\n" +
                "\"success\": true,\n" +
                "\"deck_id\": \"3p40paa87x90\",\n" +
                "\"cards\": [\n" +
                "{\n" +
                "\"code\": \"6H\",\n" +
                "\"image\": \"https://deckofcardsapi.com/static/img/6H.png\",\n" +
                "\"images\": {\n" +
                "\"svg\": \"https://deckofcardsapi.com/static/img/6H.svg\",\n" +
                "\"png\": \"https://deckofcardsapi.com/static/img/6H.png\"\n" +
                "},\n" +
                "\"value\": \"6\",\n" +
                "\"suit\": \"HEARTS\"\n" +
                "},\n" +
                "{\n" +
                "\"code\": \"5S\",\n" +
                "\"image\": \"https://deckofcardsapi.com/static/img/5S.png\",\n" +
                "\"images\": {\n" +
                "\"svg\": \"https://deckofcardsapi.com/static/img/5S.svg\",\n" +
                "\"png\": \"https://deckofcardsapi.com/static/img/5S.png\"\n" +
                "},\n" +
                "\"value\": \"5\",\n" +
                "\"suit\": \"SPADES\"\n" +
                "}\n" +
                "],\n" +
                "\"remaining\": 60\n" +
                "}";

        Assertions.assertArrayEquals(cardsTest, cardMapper.parseCardsList(responseJson).toArray());
    }
}
