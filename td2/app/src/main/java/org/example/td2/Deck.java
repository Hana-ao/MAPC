package main.java.org.example.td2;

import java.util.*;
public class Deck implements Iterable<Card>{
    private List<Card> cards = ArrayList();

    public Deck(){
        for (Card.Suit suit : Card.Suit.values()){
            for(Card.Rank rank : Card.Rank.values()){
                cards.add(new Card(suit, rank));
            }
        }
    }
    public Optional<Card> draw(){
        //assert(!isEmpty());
        if(isEmpty()){
            return Optional.empty();
        }
        return Optional.of(cards.remove(0));
        //si le deck est vide, on peut pas tirer de cartes
    }

    public Deck (Deck d){//constructeur par recopie
        //this.cards = d.cards //fuite de données, interdit!
        this.cards = new ArrayList<>(d.cards);
    }
    public boolean isEmpty(){
        return size()==0;
    }


    private int size(){ // private car personne n'a besoin de savoir cb de cartes comtient le deck
        return cards.size(); 
    }

    public Card cardAt(int index){
        //récupère la ième carte
        assert(index>=0 && index < cards.size());
        if(index>=0 && index < cards.size()){
            return Optional.of(cards.get(index));
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Card> Iterator(){
        //on peut aussi inverser l'ordre des cartes
        return cards.iterator();//cards est une liste, donc elle possède une méthode iterator
    }
}
