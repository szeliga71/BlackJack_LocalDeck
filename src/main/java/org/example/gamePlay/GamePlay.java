package org.example.gamePlay;

import org.example.model.Deck;
import org.example.model.House;
import org.example.model.Player;

import java.util.Scanner;

public class GamePlay {

    private final DeckService deckService = new DeckService();
    private final Scanner scanner = new Scanner(System.in);
    private final Validators validators = new Validators(scanner);
    private final Player player;
    private final House house;
    private Deck deck;
    private int wager = 0;
    private boolean wouldYouPlay = true;


    public GamePlay(House house, Player player) {
        this.house = house;
        this.player = player;
        this.deck = startingPointsAndNewDeck();
    }

    public void start() {
        while (wouldYouPlay && player.getPlayerPoints() > 0) {
            if (deck.getCards().size() < 2) {
                deck = deckService.initializeNewDeckAndFillLocalDeck();
            }
            promptForNewGame();
        }
    }

    private Deck startingPointsAndNewDeck() {
        player.setPlayerPoints(100);
        System.out.println("The player receives 100 points!");
        return deckService.initializeNewDeckAndFillLocalDeck();
    }

    private void promptForNewGame() {
        System.out.println(" Welcome to a new game! At this moment, you can end the game (press \"E\") or start betting (press \"P\").");
        String choice = scanner.nextLine().trim().toLowerCase();
        switch (choice) {
            case "p" -> {
                startNewRund();
                SaveGameService.saveDataAfterGame(player, house);
            }
            case "e" -> {
                endGame();
                SaveGameService.saveDataAfterGame(player, house);
            }
            default -> System.out.println("ERROR!!! Please enter the correct letter!");
        }
    }

    private void startNewRund() {
        resetGameRund();
        wager = validators.getWager(player, scanner);
        drawInitialCards();
        boolean answer = true;
        while (answer) {
            if (deck.getCards().size() < 2) {
                deck = deckService.initializeNewDeckAndFillLocalDeck();
            }
            String decision = validators.makeDecisionToPlayOrPass(new Scanner(System.in));
            if (decision.equals("c")) {
                answer = handlePlayerDraw();
            } else if (decision.equals("p")) {
                answer = handlePlayerPass();
            }
        }
    }

    private void resetGameRund() {
        wager = 0;
        house.setScore(0);
        player.setScore(0);
        house.getHand().clear();
        player.getHand().clear();
        player.setPlayerWin(false);
    }

    private void drawInitialCards() {
        playerDrawCard();
        houseDrawCard();
    }

    private void playerDrawCard() {
        player.addCardToHand(deckService.drawCardFromDeck(deck));
        player.setScore(player.countScore(player.getHand()));
        player.showScoreMessage();
    }

    private void houseDrawCard() {
        house.addCardToHand(deckService.drawCardFromDeck(deck));
        house.setScore(house.countScore(house.getHand()));
        house.showScoreMessage();
    }

    private boolean handlePlayerDraw() {
        playerDrawCard();
        if (!(house.getScore() > 16 && house.getScore() < 21)) {
            houseDrawCard();
        }
        return checkGameOutcome();
    }

    private boolean handlePlayerPass() {
        if (house.getScore() >= 17 && house.getScore() < 21 && player.getScore() == house.getScore()) {
            house.setScore(21);
            player.setScore(21);
        } else if (house.getScore() >= 17 && house.getScore() < 21 && player.getScore() < house.getScore()) {
            house.setScore(21);
        } else if (!(house.getScore() > 16 && house.getScore() < 21)) {
            houseDrawCard();
        }
        return checkGameOutcome();
    }

    private boolean checkGameOutcome() {
        boolean result = true;
        if (player.getScore() > 21 && house.getScore() > 21) {
            System.out.println("Both lost ! ");
            result = false;
        } else if (player.getScore() > 21) {
            System.out.println(" House win , playerScore>21");
            result = false;
        } else if (house.getScore() > 21) {
            System.out.println("Player win , houseScore>21");
            player.setPlayerPoints(player.getPlayerPoints() + (wager * 2));
            result = false;
        } else if (player.getScore() == house.getScore() && player.getScore() == 21) {
            if (player.getHand().size() > house.getHand().size()) {
                System.out.println("House win !!!");
            } else if (player.getHand().size() < house.getHand().size()) {
                System.out.println("Player is a Winner !!!");
                player.setPlayerPoints(player.getPlayerPoints() + (wager * 2));
            } else {
                System.out.println(" Draw  Tie ");
                player.setPlayerPoints(player.getPlayerPoints() + (wager));
            }
            result = false;
        } else if (player.getScore() == 21) {
            System.out.println("Player win with Blackjack!");
            player.setPlayerPoints(player.getPlayerPoints() + (wager * 2));
            result = false;
        } else if (house.getScore() == 21) {
            System.out.println("House win with Blackjack!");
            result = false;
        } else if (house.getScore() >= 17 && house.getScore() < player.getScore()) {
            System.out.println("Player win!");
            player.setPlayerPoints(player.getPlayerPoints() + (wager * 2));
            result = false;
        }
        return result;
    }

    private void endGame() {
        System.out.println("  END of Game  ");
        wouldYouPlay = false;
        player.setPlayerPoints(0);
    }
}
