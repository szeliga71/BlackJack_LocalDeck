package org.example.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Card;
import org.example.model.Suit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardMapper {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public List<Card> parseCardsList(String json) {
        try {
            JsonNode rootNode = MAPPER.readTree(json);
            JsonNode nodeArray = rootNode.get("cards");
            if (!nodeArray.isArray()) {
                throw new IllegalArgumentException(" Failed to parse JSON: invalid file format or missing \"cards\" field ");
            } else {
                return extractCardsFromJsonArray(nodeArray);
            }
        } catch (IOException e) {
            throw new RuntimeException(" Failed to parse JSON ", e);
        }
    }

    private List<Card> extractCardsFromJsonArray(JsonNode nodeArray) {
        if (nodeArray.isArray() && !nodeArray.isEmpty()) {
            List<Card> cards = new ArrayList<>();
            for (JsonNode node : nodeArray) {
                int value = valueFromStringToInt(node.get("value").asText());
                Suit suit = Suit.fromStringtoSuit(node.get("suit").asText());
                cards.add(new Card(value, suit));
            }
            return cards;
        } else {
            throw new IllegalArgumentException(" Failed to parse JSON: invalid nodeArray, or nodeArray is empty ");
        }
    }

    private static Map<String, Integer> cardValueSchema() {
        Map<String, Integer> cardValues = new HashMap<>();
        for (int i = 2; i < 11; i++) {
            cardValues.put(Integer.toString(i), i);
        }
        cardValues.put("jack", 11);
        cardValues.put("queen", 12);
        cardValues.put("king", 13);
        cardValues.put("ace", 14);

        return cardValues;
    }

    public int valueFromStringToInt(String valueFromJson) {
        if (valueFromJson == null || valueFromJson.isEmpty()) {
            throw new IllegalArgumentException(" The provided file is empty or null ");
        }
            valueFromJson = valueFromJson.toLowerCase().trim();
            if ("king".equals(valueFromJson) || "queen".equals(valueFromJson) || "jack".equals(valueFromJson) || "ace".equals(valueFromJson)) {
                return cardValueSchema().get(valueFromJson);
            }
            try {
                int numberValue = Integer.parseInt(valueFromJson);
                if (numberValue > 1 && numberValue < 11) {
                    return cardValueSchema().get(valueFromJson.trim());
                }
            } catch (NumberFormatException e) {
                System.out.println(" Failed to parse the value to an integer ");
            }
            throw new IllegalArgumentException("Invalid values describing cards in the provided JSON");

        }

}

