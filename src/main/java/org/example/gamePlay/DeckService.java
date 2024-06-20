package org.example.gamePlay;

import org.example.api.DeckApiHandler;
import org.example.mappers.CardMapper;
import org.example.mappers.DeckMapper;
import org.example.model.Card;
import org.example.model.Deck;


import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class DeckService {

    private final Scanner scanner = new Scanner(System.in);
    private final CardMapper cardMapper = new CardMapper();
    private final DeckApiHandler deckApiHandler = new DeckApiHandler();
    private final Validators validators = new Validators(scanner);
    private final DeckMapper deckMapper = new DeckMapper();

    public Card drawCardFromDeck(Deck deck) {
        List<Card> cards = deck.getCards();
        Card card = cards.remove(0);
        return card;
    }

    private List<Card> parseCardsFromJsonToDeckList(String responseWithCardsBody) {
        return cardMapper.parseCardsList(responseWithCardsBody);
    }

    public Deck initializeNewDeckAndFillLocalDeck() {
        HttpResponse<String> responseDeckId = deckApiHandler.getShuffledDecksIdInResponseFromDeckSource(validators.enterAmountOfDeck(scanner));
        Deck deck = new Deck(deckMapper.getDeckIdFromResponseBody(responseDeckId.body()));
        deck.setRemainingCards(deckMapper.getAmountOfCardsInDeck(responseDeckId.body()));
        HttpResponse<String> responseWithCards = deckApiHandler.getCardsInResponseFromDeckSource(deck.getDeckId(), deck.getRemainingCards());
        deck.createDeckOfCards(parseCardsFromJsonToDeckList(responseWithCards.body()));
        return deck;
    }
}
