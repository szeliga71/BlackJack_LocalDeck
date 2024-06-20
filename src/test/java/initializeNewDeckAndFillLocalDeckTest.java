import org.example.api.DeckApiHandler;
import org.example.gamePlay.DeckService;
import org.example.gamePlay.Validators;
import org.example.model.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class initializeNewDeckAndFillLocalDeckTest {

@Mock
    private HttpResponse<String>response;
@Mock
    private DeckApiHandler deckApiHandler;
@Mock
Validators validators;

DeckService deckService=new DeckService();

@Mock
Deck deck;

@Test
    void initializeNewDeckAndFillLocalDeckHappyPath() {
    String responseGetDeckJson="{\n" +
            "    \"success\": true,\n" +
            "    \"deck_id\": \"3p40paa87x90\",\n" +
            "    \"shuffled\": true,\n" +
            "    \"remaining\": 62\n" +
            "}";

    String responseGetCardsJson = "{\n" +
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
    when(deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(anyInt())).thenReturn(response);
    when(response.body()).thenReturn(responseGetDeckJson);
    when(deckApiHandler.getCardsInResponseFromDeckSource(anyString(),anyInt())).thenReturn(response);

    when(response.body()).thenReturn(responseGetCardsJson);
    when(deck.getDeckId()).thenReturn("3p40paa87x90");
    when(deckApiHandler.getCardsInResponseFromDeckSource("3p40paa87x90",62).body()).thenReturn(responseGetCardsJson);

    Deck testDeck=new Deck("3p40paa87x90");
    Assertions.assertEquals(testDeck.getDeckId(),deckService.initializeNewDeckAndFillLocalDeck().getDeckId());
}
}

/*
   public Deck initializeNewDeckAndFillLocalDeck() {
        HttpResponse<String> responseDeckId = deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(validators.enterAmountOfDeck(scanner));
        Deck deck = new Deck(deckMapper.getDeckIdFromResponseBody(responseDeckId.body()));
        deck.setRemainingCards(deckMapper.getAmountOfCardsInDeck(responseDeckId.body()));
        HttpResponse<String> responseWithCards = deckApiHandler.getCardsInResponseFromDeckSource(deck.getDeckId(), deck.getRemainingCards());
        deck.createDeckOfCards(addCardsToDeck(responseWithCards.body()));
        return deck;
    }
 */
