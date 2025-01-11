package org.example;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.ICard.Rank;
import org.example.cards.metier.api.ICard.Suit;
import org.example.cards.metier.impl.Card;
import org.example.cards.metier.impl.Hand;

class HandTest {

    @Test
    void testHandClone() throws CloneNotSupportedException {
        // Préparation
        List<ICard> originalCards = new ArrayList<>();
        ICard card1 = new Card(Rank.ACE, Suit.HEARTS);
        ICard card2 = new Card(Rank.KING, Suit.SPADES);
        originalCards.add(card1);
        originalCards.add(card2);

        Hand originalHand = new Hand(originalCards, 5, "Player1");

        // Clonage
        Hand clonedHand = originalHand.clone();

        // Vérifications
        assertNotSame(originalHand, clonedHand, "L'objet cloné doit être une nouvelle instance.");
        assertNotSame(originalHand.getHand(), clonedHand.getHand(), "Les listes doivent être différentes.");
        assertEquals(originalHand.getHand(), clonedHand.getHand(), "Les contenus des listes doivent être identiques.");
        
        // Modifie le clone et vérifie l'indépendance
        clonedHand.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
        assertNotEquals(originalHand.size(), clonedHand.size(), "Les modifications du clone ne doivent pas affecter l'original.");
    }
}