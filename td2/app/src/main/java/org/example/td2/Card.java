package main.java.org.example.td2;

public class Card {
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Suit{
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank){
        // ce if n'est pas DRY : if(suit != null || rank != null ){

        assert(suit != null);
        assert(rank != null);
        this.suit = suit;
        this.rank = rank;
    }
    public Suit suit(){
        return suit;
    }
    public Rank rank(){
        return rank;
    }

    @Override
    public String toString(){
        return rank() + "of" + suit();
    }
    //penser à l'IA une fois java compris
}
