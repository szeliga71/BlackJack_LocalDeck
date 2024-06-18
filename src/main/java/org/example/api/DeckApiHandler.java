package org.example.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeckApiHandler {


    private final String sourceGetDeckId = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=";
    private final String getCardsToLocalFromDeckSource = "https://www.deckofcardsapi.com/api/deck/";
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getShuffledDecksIdInResponseFromDeckSource(int amountOfDecks) {
        if (!(amountOfDecks >= 4 && amountOfDecks <= 8)) {
            throw new IllegalArgumentException("Please provide the number of decks from the range of 4 to 8");
        }
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(sourceGetDeckId + amountOfDecks)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error during retrieval :" + response.statusCode());
            }
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> getCardsInResponseFromDeckSource(String deckId, int amountOfCards) {

        if ((amountOfCards <1)||(amountOfCards>416)) {
            throw new IllegalArgumentException("You want to draw invalid amount of cards!");
        }
        if (deckId == null || deckId.isEmpty()) {
            throw new IllegalArgumentException("please provide right deck id ");
        }
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(getCardsToLocalFromDeckSource + deckId + "/draw/?count=" + amountOfCards)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error during retrieval : " + response.statusCode());
            }
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
