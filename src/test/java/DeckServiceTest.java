import org.example.api.DeckApiHandler;
import org.example.gamePlay.DeckService;
import org.example.mappers.DeckMapper;
import org.example.model.Card;
import org.example.model.Deck;
import org.example.model.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DeckServiceTest {

    
    List<Card>deck=new ArrayList<Card>();
    DeckService deckService=new DeckService();
    DeckMapper deckMapper=new DeckMapper();
    DeckApiHandler deckApiHandler=new DeckApiHandler();

    public void fillDeck(Deck deck) {
        List<Card>cardsInDeck=new ArrayList<Card>();
        cardsInDeck.add(new Card(5, Suit.SPADES));
        cardsInDeck.add(new Card(4, Suit.HEARTS));
        cardsInDeck.add(new Card(3, Suit.CLUBS));
        cardsInDeck.add(new Card(2, Suit.DIAMONDS));
        deck.createDeckOfCards(cardsInDeck);
    }


    @Test
            void getCardFromDeck() {
        String response=deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(5).body();
        String deckId=deckMapper.getDeckIdFromResponseBody(response);
        Deck deck =new Deck(deckId);
        fillDeck(deck);
        int size=deck.getCards().size()-1;
        deckService.getCardFromDeck(deck);
        Assertions.assertEquals(size,deck.getCards().size());
    }

}
