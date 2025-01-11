package org.example.cards.metier.api;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.example.cards.metier.api.ICard.Suit;
import org.example.cards.metier.impl.Joker;

public interface IHand extends Iterable<ICard>, Comparable<IHand> {

    boolean isEmpty();

    public int size();

    boolean isFull();

    int getNbCartes();

    void addCard(ICard card);
    public List<ICard> getHand();
    

    boolean removeCard(ICard card);

    boolean contains(ICard card);

    @Override
    String toString();

    @Override
    Iterator<ICard> iterator();

    public static Comparator<IHand> compareByNumberOfJokers() {
        return (hand1, hand2) -> {
            int nbJokers1 = (int) hand1.getHand().stream().filter(card -> card instanceof Joker).count();
            int nbJokers2 = (int) hand2.getHand().stream().filter(card -> card instanceof Joker).count();
            return Integer.compare(nbJokers1, nbJokers2);
        };
    }
    
    public static Comparator<IHand> compareByDiamonds() {
        return (hand1, hand2) -> {
            int nbDiam1 = (int) hand1.getHand().stream()
                .filter(card -> card.getSuit() == ICard.Suit.DIAMONDS)
                .count();
            int nbDiam2 = (int) hand2.getHand().stream()
                .filter(card -> card.getSuit() == ICard.Suit.DIAMONDS)
                .count();
            return Integer.compare(nbDiam1, nbDiam2);
        };
    }

    
}
