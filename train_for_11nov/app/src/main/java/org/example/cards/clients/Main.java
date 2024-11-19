package org.example.cards.clients;

import java.util.*;

import org.example.cards.metier.api.CardGameProcessor;
import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.ICard.Rank;
import org.example.cards.metier.api.ICard.Suit;
import org.example.cards.metier.impl.BlackJackProcessor;
import org.example.cards.metier.impl.Card;
import org.example.cards.metier.impl.CardSorter;
import org.example.cards.metier.impl.Deck;
import org.example.cards.metier.impl.Hand;
import org.example.cards.metier.impl.Joker;
import org.example.cards.metier.impl.PokerProcessor;
import org.example.cards.metier.impl.SortByRank;
import org.example.tds.td2.MultiDeck;

public class Main {
    public static void main(String[] args) {

        Card aceCard = new Card(Rank.ACE, Suit.SPADES);
        Card kingCard = new Card(Rank.KING, Suit.CLUBS);
        Card queenCard = new Card(Rank.QUEEN, Suit.HEARTS);

        Joker jocker = new Joker(); //Polymorphisme : JokerCard peut être utilisée là où Card est attendue grâce à l’héritage.
        List<ICard> cards = new ArrayList<ICard>();
        cards.add(queenCard);
        cards.add(kingCard);
        cards.add(aceCard);
        cards.add(jocker);


        Deck myDeck = new Deck(cards);
        myDeck.addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
        myDeck.printDeck();

        //------------------------------------
        //testons Iterator
        List<ICard> newListOfCards = Arrays.asList(
            new Card(Rank.ACE, Suit.SPADES), 
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Joker());

        Deck newDeck = new Deck(newListOfCards);
        
        System.out.println("Parcours du Deck avec une boucle for-each");
        for(ICard elt : newDeck){ //on itére sur le deck !!
            System.out.println(elt.toString());
        }

        System.out.println("Implémentation du tri avec Comparable");
        //Maintenant que Card implémente Comparable, on va trier la liste de cartes simplement en utilisant Collections.sort() ou List.sort() :
        List<Card> listSort = Arrays.asList(
            new Card(Rank.ACE, Suit.SPADES), 
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.KING, Suit.CLUBS));
        
        // Méthode une avec sort() de la classe List, que l'on appelle sur l'instance listSort : 
        listSort.sort(null); //null signifie que l'ordre naturel (Comparable) est utilisé

        //Méthode avec Collections.sort()
        //Collections.sort(listSort); permet de trier de manière simple et standardisée
        for(Card elt : listSort){
            System.out.println(elt.toString());
        }


        
        System.out.println("Implémentation du tri avec Comparator (avec Suit)");
        listSort.sort(Card.compareBySuit());
        for(Card elt : listSort){
            System.out.println(elt.toString());
        }

        System.out.println("Test de SortStrategy ainsi que CardSorter -> Pattern Strategy !");
        List<ICard> listSortStrategy = Arrays.asList(
            new Card(Rank.KING, Suit.CLUBS), 
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.ACE, Suit.CLUBS));
        CardSorter c = new CardSorter(new SortByRank());

        c.sort(listSortStrategy);

        for(ICard elt : listSortStrategy){
            System.out.println(elt.toString());
        }

        //Test de deepCopy pour CARD
        System.out.println("Test de deepCopy pour CARD");
        Card carteACopier = new Card(Rank.ACE, Suit.CLUBS);
        Card copieuseDeep = carteACopier.deepCopy(); //donc on doit tester si la copie est bien profonde ; càd regarder si les deux objets ont des réferences diff
        if(carteACopier != copieuseDeep){
            System.out.println("Félicitations, les deux références sont bien différentes");
        }else{
            System.out.println("Oups, l'objet copié référence l'objet original");
        }

        //Test de deepCopy pour DECK
        System.out.println("Test de deepCopy pour DECK");
        //On va créer une copie profonde de newDeck
        Deck deckCopieurDeep = newDeck.deepCopy();
        newDeck.addCard(new Joker());
        for(ICard elt : deckCopieurDeep){
            //System.out.println("Classe de la carte" + elt.getClass().getName());
            System.out.println(elt.toString());
        }

        //Test de deepCopy pour MultiDECK
        System.out.println("Test de deepCopy pour MutliDECK");
        List<Deck> listDecks = new ArrayList<>();
        listDecks.add(newDeck);
        listDecks.add(deckCopieurDeep);
        MultiDeck multiDeck = new MultiDeck(listDecks);

        MultiDeck multiDeckCopieurDeep = multiDeck.deepCopy();
        
        multiDeck.addDeck(new Deck(new ArrayList<>(Arrays.asList(new Card(Rank.TEN, Suit.HEARTS)))));
        System.out.println("On vérifie que notre nouveau multi deck ne contient que 2 decks");
        multiDeckCopieurDeep.printMultiDeck();
        System.out.println("Et que l'ancien en contient 3");
        multiDeck.printMultiDeck();
        if(multiDeck.getDecks().get(0) != multiDeckCopieurDeep.getDecks().get(0)){
            System.out.println("Les deux références sont différentes, good job ! ");
        }

        //TEST DE HAND ET COMPARATOR
        //TEST pour compareByNumberOfJokers()
        Hand hand1 = new Hand(
    Arrays.asList(
        new Card(Rank.ACE, Suit.HEARTS),
        new Card(Rank.TWO, Suit.CLUBS),
        new Joker(),
        new Card(Rank.JACK, Suit.SPADES)
    ), 
    5, 
    "Joueur 1"
);

Hand hand2 = new Hand(
    Arrays.asList(
        new Card(Rank.THREE, Suit.HEARTS),
        new Card(Rank.FIVE, Suit.DIAMONDS),
        new Card(Rank.KING, Suit.HEARTS),
        new Joker(),
        new Joker()
    ), 
    5, 
    "Joueur 2"
);

Comparator<Hand> comparatorJokers = Hand.compareByNumberOfJokers();
int result = comparatorJokers.compare(hand1, hand2);

if(result > 0){System.out.println("Hand1 a plus de Jokers que Hand2");}
else if(result < 0){System.out.println("Hand2 a plus de Jokers que Hand1");}
else{System.out.println("Hand1 et Hand2 sont égaux");}

//Maintenant on va essayer de trier en fonction du nb de jokers (le premier a le plus de jokers)
List<Hand> listHands = Arrays.asList(hand1,hand2);
//Collections.sort(listHands, comparatorJokers.reversed());//pour trier par ordre décroissant
//listHands.sort(null); //tri par ordre naturel

Collections.sort(listHands,Hand.compareByDiamonds().reversed());
for(Hand elt : listHands){
    System.out.println(elt.toString());
}

CardGameProcessor pokerProcessor = new PokerProcessor();
CardGameProcessor blackJackProcessor = new BlackJackProcessor();
pokerProcessor.playGame();
blackJackProcessor.playGame();





    }
}
