package org.example.cards.metier.impl;

import java.util.Collections;
import java.util.List;

import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.SortStrategy;

public class SortByRank implements SortStrategy{

    @Override
    public void sort(List<ICard> cards) {
        Collections.sort(cards, (c1, c2) -> c1.getRank().compareTo(c2.getRank())); //on trie cards en fonction du rang, le comparator est le deuxième argument
    }
    
}
