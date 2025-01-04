package org.example.cards.metier.api;

import java.util.List;

public interface SortStrategy { //l'interface à implémenter pour être trié
    void sort(List<ICard> cards);
}
