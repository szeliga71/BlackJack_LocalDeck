import org.example.api.DeckApiHandler;
import org.example.gamePlay.DeckService;
import org.example.mappers.DeckMapper;
import org.example.model.Card;
import org.example.model.Deck;
import org.example.model.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetCardFromDeckTest {


    private List<Card> deck = new ArrayList<Card>();
    private final DeckService deckService = new DeckService();
    private final DeckMapper deckMapper = new DeckMapper();
    private final DeckApiHandler deckApiHandler = new DeckApiHandler();

    public void fillDeck(Deck deck) {
        List<Card> cardsInDeck = new LinkedList<>();
        cardsInDeck.add(new Card(4, Suit.HEARTS));
        cardsInDeck.add(new Card(5, Suit.SPADES));
        cardsInDeck.add(new Card(3, Suit.CLUBS));
        cardsInDeck.add(new Card(2, Suit.DIAMONDS));
        deck.createDeckOfCards(cardsInDeck);
    }

    @Test
    void getCardFromDeckTest() {
        String response = deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(5).body();
        String deckId = deckMapper.getDeckIdFromResponseBody(response);
        Deck deck = new Deck(deckId);
        fillDeck(deck);
        int size = deck.getCards().size() - 1;
        deckService.drawCardFromDeck(deck);
        Assertions.assertEquals(size, deck.getCards().size());
    }

    @Test
    void getCardFromDeckTest2() {
        String response = deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(5).body();
        String deckId = deckMapper.getDeckIdFromResponseBody(response);
        Deck deck = new Deck(deckId);
        fillDeck(deck);
        Card card = deck.getCards().get(0);
        Assertions.assertEquals(card, deckService.drawCardFromDeck(deck));
    }
}

