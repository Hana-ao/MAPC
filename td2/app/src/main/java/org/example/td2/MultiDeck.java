package main.java.org.example.td2;

import java.util.*;

public class MultiDeck {
    //combine un certain nb de jeux de cartes
    //que peut on faire avec un multiDeck ? tirer une carte

    private List<Deck>  decks = new ArrayList<>();

    // public MultiDeck(???){
    //     decks = new ArrayList<Deck>();
    // } qu'est ce que je vais faire, quel type utiliser dans multideck

    public MultiDeck(int nbDecks){
        decks = new ArrayList<>();
        for(int i=1 ; i <= nbDecks ; i++){
            decks.add(new Deck());
        }
    }


    Optional<Card> draw(){
        //comment tirer une carte dans un multi deck ?
        //on regarde si le premier deck est vide, alors on prend dans le deuxième, sinon on prend dabord dans le premier deck
        //assert(!isEmpty()); //si le multideck est vide
        //il y a au moins 1 carte qlq part
        //dans un des decks
        Optional<Deck> premierDeckPasVide = decks.stream()
                                                    .filter((Deck d) -> !d.isEmpty())
                                                    .findFirst();
        // Deck d = premierDeckPasVide.get(); ce n'est pas recommandé
        if(premierDeckPasVide.isPresent()){
            Deck d = premierDeckPasVide.get();
            return d.draw();
        }else{
            return Optional.empty();
        }
        //un maitre mot : on délègue
    }

    public MultiDeck(MultiDeck multiDeckACopier){
        // this.decks = new ArrayList<>(multiDeck.decks);
        this.decks = new ArrayList<>();
        //je dois connaitre les decks du multidecks que je dois parcourir, mais on les connait pas. 
        for(Deck deck : multiDeckACopier.decks){
            this.decks.add(new Deck(deck));
        }
    }


    // Optional<Card> draw(){
    //     //comment tirer une carte dans un multi deck ?
    //     //on regarde si le premier deck est vide, alors on prend dans le deuxième, sinon on prend dabord dans le premier deck
    //     assert(!isEmpty()); //si le multideck est vide
    //     //il y a au moins 1 carte qlq part
    //     //dans un des decks
    //     Optional<Deck> premierDeckPasVide = decks.stream()
    //                                                 .filter((Deck d) -> !d.isEmpty())
    //                                                 .findFirst();
    //     // Deck d = premierDeckPasVide.get(); ce n'est pas recommandé
    //     if(premierDeckPasVide.isPresent()){
    //         Deck d = premierDeckPasVide.get();
    //         return Optional.of(d.draw()); //transforme la carte en boite mais pas besoin de réemboiter car elle est emboitée dans deck
    //     }else{
    //         return Optional.empty();
    //     }
    // }
    boolean isEmpty(){
        return decks.stream()
                    .allMatch(Deck::isEmpty); //allMatch dit 'est ce que tous les decks vérifient isEmpty'
    }
    
    // int size(){
    //     return decks.stream().map(Deck::size).reduce(0, (a,b)->a+b); //comprendre, c'est le principe du patron composite
    // }
}
