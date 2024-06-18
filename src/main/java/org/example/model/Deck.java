package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final String deckId;
    private int remainingCards = 0;
    private List<Card> cards;


    public Deck(String deckId) {
        this.deckId = deckId;
        this.cards = new ArrayList<>();
    }

    public String getDeckId() {
        return deckId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void createDeckOfCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public void setRemainingCards(int remainingCards) {
        this.remainingCards = remainingCards;
    }
}
