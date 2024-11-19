package main.java.org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards); // Mélanger les cartes pour simuler un vrai deck
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No more cards in the deck.");
        }
        return cards.remove(cards.size() - 1);  // Tirer la carte du dessus
    }

    public List<Card> getCards() {
        // Retourne une copie immuable de la liste
        return Collections.unmodifiableList(cards);
    }
}
