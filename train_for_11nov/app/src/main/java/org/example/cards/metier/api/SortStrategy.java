package org.example.cards.metier.api;

import java.util.List;

public interface SortStrategy { //l'interface à implémenter 
    void sort(List<ICard> cards);
}
