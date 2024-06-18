package org.example.model;

public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES;

    public static Suit fromStringtoSuit(String suit) {
        return switch (suit.toLowerCase()) {
            case "hearts" -> HEARTS;
            case "diamonds" -> DIAMONDS;
            case "clubs" -> CLUBS;
            case "spades" -> SPADES;
            default -> throw new IllegalArgumentException("Cannot parse " + suit);
        };
    }
}
