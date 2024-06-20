package org.example.model;

public class Card {

    private final int value;
    private final Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suit=" + suit +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;

        return getValue() == card.getValue() && suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = getValue();
        result = 31 * result + suit.hashCode();
        return result;
    }
}
