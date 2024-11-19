package org.example.mesRessourcesPourComprendre;
import java.util.*;

import org.example.cards.metier.api.ICard.Rank;
import org.example.cards.metier.api.ICard.Suit;
import org.example.cards.metier.impl.Card;


public class CardSorterV1 {


    //classe qui permet de trier les cartes selon différentes façons
    //elle ne respecte pas les principes de single responsability, n'est ce pas ?

    public static void sortByRank(List<Card> cards){
            //on utilise Collections.sort(maListe, unCertainOrdreQuiCompareParRang)
            //Collections.sort(cards); //sortByRank utilise Collections.sort(cards). Cela suppose que la classe Card implémente l’interface Comparable et a une méthode compareTo()
            Collections.sort(cards,(c1,c2) -> c1.getRank().compareTo(c2.getRank()));
        }
    public static void sortBySuit(List<Card> cards){
            Collections.sort(cards, (c1, c2) -> c1.getSuit().compareTo(c2.getSuit())); //ça fait appel à compareTo qui elle compare en fonction de l'ordre du rang dans l'énum : ACE < king
    }

    //condition ? valeurSiVrai : valeurSiFaux;
    public static void sortByRankAndSuit(List<Card> cards){
            Collections.sort(cards, (c1, c2) -> {
                int compareRank = c1.getRank().compareTo(c2.getRank());
                return (compareRank != 0 ? compareRank : c1.getSuit().compareTo(c2.getSuit()));
            }
                ); /*	compareRank != 0 signifie que les deux cartes comparées ont des rangs différents. Dans ce cas, on retourne compareRank, ce qui permet de trier les cartes en fonction de leur rang.
                •	c1.getSuit().compareTo(c2.getSuit()) est exécuté uniquement si compareRank == 0, c’est-à-dire si les cartes ont le même rang. Dans ce cas, on utilise la comparaison par suit (couleur) pour départager les cartes. */
            }
    
    //les problèmes sans pattern strategy
    /*Sujet à erreurs ? comme le tri avec comparator; 
    ouvert à la modification (alors qu'elle devrait pas), si on veut ajouter une nouvelle manière de trier ; des erreurs non existantes avant peuvent arriver? 
    - Dur de tester : couplage fort entre le code de tri et la classe
    - Couplage: degré de dépendance entre une classe et une autre
    */
    //du coup le pattern strategy consiste à transformer CardSorter en une classe qu'on peut pas instancier, mais dont tlm dépend ? comme un héritage? et les sous classes font un tri spécifique?
    
    
    public static void main(String[] args){ //En Java, la méthode main doit se trouver dans une classe publique.
        List<Card> myListOfCards = Arrays.asList(
            new Card(Rank.QUEEN, Suit.HEARTS), 
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.KING, Suit.CLUBS)) ;
        System.out.println("Affichage de la liste avant sort() ");

        for(Card elt : myListOfCards){
            System.out.println(elt);
        }

        sortByRank(myListOfCards);
        System.out.println("Affichage de la liste après sort() ");

        for(Card elt : myListOfCards){
            System.out.println(elt);
        }


} }
