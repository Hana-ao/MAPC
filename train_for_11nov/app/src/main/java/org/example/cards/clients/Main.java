package org.example.cards.clients;

import java.util.*;

import org.example.cards.metier.api.CardGameProcessor;
import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.IHand;
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

        // Utilisation de Card.getCard
        Card aceCard = Card.getCard(Rank.ACE, Suit.SPADES);
        Card kingCard = Card.getCard(Rank.KING, Suit.CLUBS);
        Card queenCard = Card.getCard(Rank.QUEEN, Suit.HEARTS);

        Joker joker = new Joker(); // Polymorphisme avec Joker
        List<ICard> cards = new ArrayList<>();
        cards.add(queenCard);
        cards.add(kingCard);
        cards.add(aceCard);
        cards.add(joker);

        Deck myDeck = new Deck(cards);
        myDeck.addCard(Card.getCard(Rank.FOUR, Suit.DIAMONDS));
        myDeck.printDeck();

        //------------------------------------
        // Testons Iterator
        List<ICard> newListOfCards = Arrays.asList(
            Card.getCard(Rank.ACE, Suit.SPADES),
            Card.getCard(Rank.QUEEN, Suit.HEARTS),
            new Joker()
        );

        Deck newDeck = new Deck(newListOfCards);

        System.out.println("Parcours du Deck avec une boucle for-each");
        for (ICard elt : newDeck) { // On itère sur le deck
            System.out.println(elt.toString());
        }

        System.out.println("Implémentation du tri avec Comparable");
        List<Card> listSort = Arrays.asList(
            Card.getCard(Rank.ACE, Suit.SPADES),
            Card.getCard(Rank.QUEEN, Suit.HEARTS),
            Card.getCard(Rank.JACK, Suit.HEARTS),
            Card.getCard(Rank.KING, Suit.CLUBS)
        );

        listSort.sort(null); // Tri naturel
        for (Card elt : listSort) {
            System.out.println(elt.toString());
        }

        System.out.println("Implémentation du tri avec Comparator (avec Suit)");
        listSort.sort(Card.compareBySuit());
        for (Card elt : listSort) {
            System.out.println(elt.toString());
        }

        System.out.println("Test de SortStrategy ainsi que CardSorter -> Pattern Strategy !");
        List<ICard> listSortStrategy = Arrays.asList(
            Card.getCard(Rank.KING, Suit.CLUBS),
            Card.getCard(Rank.QUEEN, Suit.HEARTS),
            Card.getCard(Rank.JACK, Suit.HEARTS),
            Card.getCard(Rank.ACE, Suit.CLUBS)
        );
        CardSorter c = new CardSorter(new SortByRank());
        c.sort(listSortStrategy);

        for (ICard elt : listSortStrategy) {
            System.out.println(elt.toString());
        }

        // Test de deepCopy pour CARD
        System.out.println("Test de deepCopy pour CARD");
        Card carteACopier = Card.getCard(Rank.ACE, Suit.CLUBS);
        Card copieuseDeep = carteACopier.deepCopy();
        if (carteACopier != copieuseDeep) {
            System.out.println("Félicitations, les deux références sont bien différentes");
        } else {
            System.out.println("Oups, l'objet copié référence l'objet original");
        }

        // Test de deepCopy pour DECK
        System.out.println("Test de deepCopy pour DECK");
        Deck deckCopieurDeep = newDeck.deepCopy();
        newDeck.addCard(new Joker());
        for (ICard elt : deckCopieurDeep) {
            System.out.println(elt.toString());
        }

        // Test de deepCopy pour MultiDECK
        System.out.println("Test de deepCopy pour MutliDECK");
        List<Deck> listDecks = new ArrayList<>();
        listDecks.add(newDeck);
        listDecks.add(deckCopieurDeep);
        MultiDeck multiDeck = new MultiDeck(listDecks);

        MultiDeck multiDeckCopieurDeep = multiDeck.deepCopy();

        multiDeck.addDeck(new Deck(new ArrayList<>(Arrays.asList(Card.getCard(Rank.TEN, Suit.HEARTS)))));
        System.out.println("On vérifie que notre nouveau multi deck ne contient que 2 decks");
        multiDeckCopieurDeep.printMultiDeck();
        System.out.println("Et que l'ancien en contient 3");
        multiDeck.printMultiDeck();
        if (multiDeck.getDecks().get(0) != multiDeckCopieurDeep.getDecks().get(0)) {
            System.out.println("Les deux références sont différentes, good job !");
        }

        // TEST DE HAND ET COMPARATOR
        Hand hand1 = new Hand(
            Arrays.asList(
                Card.getCard(Rank.ACE, Suit.HEARTS),
                Card.getCard(Rank.TWO, Suit.CLUBS),
                new Joker(),
                Card.getCard(Rank.JACK, Suit.SPADES)
            ),
            5,
            "Joueur 1"
        );

        Hand hand2 = new Hand(
            Arrays.asList(
                Card.getCard(Rank.THREE, Suit.HEARTS),
                Card.getCard(Rank.FIVE, Suit.DIAMONDS),
                Card.getCard(Rank.KING, Suit.HEARTS),
                new Joker(),
                new Joker()
            ),
            5,
            "Joueur 2"
        );

        Comparator<IHand> comparatorJokers = IHand.compareByNumberOfJokers();
        int result = comparatorJokers.compare(hand1, hand2);

        if (result > 0) {
            System.out.println("Hand1 a plus de Jokers que Hand2");
        } else if (result < 0) {
            System.out.println("Hand2 a plus de Jokers que Hand1");
        } else {
            System.out.println("Hand1 et Hand2 sont égaux");
        }

        List<Hand> listHands = Arrays.asList(hand1, hand2);
        Collections.sort(listHands, IHand.compareByDiamonds().reversed());
        for (Hand elt : listHands) {
            System.out.println(elt.toString());
        }

        CardGameProcessor pokerProcessor = new PokerProcessor();
        CardGameProcessor blackJackProcessor = new BlackJackProcessor();
        pokerProcessor.playGame();
        blackJackProcessor.playGame();
    }
}