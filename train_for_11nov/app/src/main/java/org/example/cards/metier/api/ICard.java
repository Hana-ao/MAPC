package org.example.cards.metier.api;



import org.example.tds.td2.DeepCopiable;

public interface ICard extends DeepCopiable<ICard>{

    //ici on utilise des énumérations : afin d'éviter la Primitive Obsession, un antipattern où des types primitifs sont utilisés à la place de types plus significatifs.
    public enum Rank{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    public enum Suit{
        CLUBS, DIAMONDS,SPADES, HEARTS}

    Rank getRank();
    Suit getSuit();

    
}
