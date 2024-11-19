package org.example.cards.metier.impl;

import java.util.*;

import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.SortStrategy;

public class SortBySuit implements SortStrategy {

    @Override
    public void sort(List<ICard> cards) {
        Collections.sort(cards, (c1, c2) -> c1.getSuit().compareTo(c2.getSuit()));
    }
    
}
