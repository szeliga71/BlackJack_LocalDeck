package org.example.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeckMapper {

    ObjectMapper MAPPER = new ObjectMapper();

    public int getAmountOfCardsInDeck(String responseBody) {
        try {
            JsonNode node = MAPPER.readTree(responseBody);
            return node.get("remaining").asInt();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDeckIdFromResponseBody(String responseBody) {
        try {
            JsonNode node = MAPPER.readTree(responseBody);
            return node.get("deck_id").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
