
package org.example.cards.metier.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.example.cards.metier.api.ICard;

/*
Enoncé : Encapsulation et Interfaces (MAPC)

L’encapsulation est un concept qui touche à la protection des données, au contrôle d’accès, à la structuration interne des méthodes, 
et à l’abstraction des détails d’implémentation. C’est une pratique qui vise à rendre le code plus sûr, 
plus modulaire, et plus facile à maintenir.

Objectif : Implémente une classe Card qui encapsule les propriétés d’une carte et une interface ICard définissant les méthodes nécessaires.

Tâches :

	•	Crée la classe Card avec des attributs encapsulés.
	•	Implémente une interface ICard avec des méthodes de base pour accéder aux propriétés de la carte.
    -> pour comprendre l'intérêt de ICard, on va d'abord faire sans, puis voir après

1.  Commencer par implémenter Comparable dans ta classe Card pour la rendre triable

 */
public class Card implements ICard, Comparable<Card>{
    

    private final Rank rank;
    private final Suit suit;
    private static final Map<Paire<Rank,Suit>, Card> cards = new HashMap<>();


    protected Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public static Card getCard(Rank rank, Suit suit){
        if(rank == null || suit == null){
            throw new IllegalArgumentException("Les arguments ne peuvent pas être nuls");
        }
        Paire key = new Paire<Rank,Suit>(rank, suit);
        cards.putIfAbsent(key, new Card(rank, suit)); //création à la demande de la carte ; puis on la place dans la partie "valeurs" de la map
        return cards.get(key);


    }
    public Rank getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    @Override
    public String toString(){
        return "<"+rank+" of "+suit+">";
    }



    @Override
    public int compareTo(Card o) {
        //on va comparer nos cartes selon leur rang, hihi
        return rank.compareTo(o.getRank()); //la classe Card est mtn triable de manière naturelle (par rang).
    }

    
    //Utilisons une lambda pour Comparator, interface fonctionnelle (interface qui contient une seule méthode abstraite, qui est la méthode "compare")
    //grâce aux lambdas, on peut implémenter la méthode compare de façon concise et rapide
    //Comparator, c'est juste ce qui définit une autre manière de comparer, via un attribut différent de la manière naturelle ; ici c'est Suit
    public static Comparator<Card> compareBySuit() { 
        return (c1, c2) -> c1.getSuit().compareTo(c2.getSuit());
    }

    
    //DeepCopiable (Copie profonde)
    public Card deepCopy(){
        return new Card(rank, suit);
    }




    /*
    // et pour comparator on a le choix, soit une classe interne static (ne dépend pas de l'instance de la classe qui l'accueille), 
    soit un lambda (utiles pour implémenter des interfaces fonctionnelles (interfaces avec une seule méthode abstraite), comme Comparator)
    
    public static Comparator<Card> compareBySuit() { 

    // compareBySuit est une méthode static (qui a pour rôle ici de créer Comparator)

    return new Comparator<Card>() { 

        // ici c'est une classe anonyme, elle est créée et instanciée sur-le-champ, et on y écrit les méthodes qu'on veut, ici c'est compare
        // une classe anonyme c'est une classe, c'est juste qu'elle nous permet de pas écrire un fichier .java uniquement pour 3 lignes de code

        // L’interface Comparator n’a pas de méthode compareTo(). La méthode correcte à implémenter dans un Comparator est compare(). 

        @Override
        public int compare(Card c1, Card c2) {
            return c1.getSuit().compareTo(c2.getSuit()); // voir CardTest pour les alternatives
        }
    };
}
     */

}
/* Ce qu'on pense de mon code :
•	Responsabilité unique (SRP) : Chaque classe et méthode a une seule responsabilité bien définie.
	•	Ouverture/fermeture (OCP) : La classe Card est ouverte à l’extension (grâce au Comparator) mais fermée à la modification. Tu peux ajouter d’autres Comparator pour des tris différents sans toucher au code existant.
	•	Inversion de dépendance (DIP) : En utilisant l’interface ICard, tu réduis le couplage entre Card et les classes qui l’utilisent. */