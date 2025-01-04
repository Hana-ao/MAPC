package org.example.tds.td3;
import java.util.*;

import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.ICard.Suit;

//command shift p 
//reset (reintinitalise les bdd de vscode)
public class Hand implements IHand {
    
    /*Hand est une classe qui représente la main d’un joueur. 
    Elle contient un certain nombre de cartes (par exemple, 5 cartes pour le poker). 
    Contrairement à un Deck, qui représente l’ensemble des cartes disponibles pour jouer, 
    la Hand est la sélection de cartes qu’un joueur possède à un moment donné.
*/
    private List<ICard> hand;
    private int nbCartes;
    private String nomJoueur;

    public Hand(List<ICard> hand, int nbCartes, String nomJoueur){
        this.nomJoueur = nomJoueur;
        this.nbCartes = nbCartes;
        this.hand = new ArrayList<>(hand);
    }

    public boolean isEmpty(){
        return hand.size()==0;
    }

    public int size(){
        return hand.size();
    }

    public boolean isFull(){
        return hand.size()==nbCartes;
    }

    public int getNbCartes(){
        return nbCartes;
    }

    public void addCard(ICard card){
        if(!isFull()){
            hand.add(card);
        }
        else{
            System.out.println("Impossible d'ajouter une carte dans la main, qui est pleine");
        }    
    }
    public List<ICard> getHand(){
        return hand;
    }

    public boolean removeCard(ICard card){
        //opérateur ternaire -> variable = (condition) ? valeurSiVrai : valeurSiFaux;
        return hand.remove(card);
        
    }

    public boolean contains(ICard card){
        return hand.contains(card);
        
        
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Hand :\n");

        for(ICard elt : hand){
            sb.append(elt.toString()).append("\n");
        }
        return sb.toString();

    }

    @Override
    public Iterator<ICard> iterator() {
        return new HandIterator();
    }

    //Comparable<ICard>
    @Override
    public int compareTo(IHand o){
        return Integer.compare(this.size(), o.size()); //on compare par rapport au nb de cartes en main

    }
    
    
    class HandIterator implements Iterator<ICard>{
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            //retourne si y'a un élément après
            return currentIndex < hand.size();
        }

        @Override
        public ICard next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            else{
                return hand.get(currentIndex++);
            }
        }

    }

    

    

}
/*
Question sur Comparator : 

"ce que je comprends pas c qu'on renvoie ça :
"Integer.compare(count1, count2)" pourtant dans la signature de la méthode, c'est un Comparator<Hand> qui est return, et pas un int"
-------------------------------------------------------------------------------------------------------------------------------------------------------

C’est une bonne question ! Le point à comprendre ici est que la méthode getComparatorByHearts() retourne un objet Comparator<Hand>, et non un simple int.

Comment cela fonctionne-t-il exactement ?

	•	Expression lambda : La lambda (hand1, hand2) -> { ... } implémente la méthode abstraite compare(T o1, T o2) de l’interface Comparator<T>. 
    Lorsqu’on retourne cette lambda, on retourne en fait un objet Comparator<Hand> dont la méthode compare() a été définie par la logique de la lambda.

Détail :

public static Comparator<Hand> getComparatorByHearts() {
    // Retourne un Comparator qui implémente la méthode compare(Hand hand1, Hand hand2)
    return (hand1, hand2) -> {
        int count1 = (int) hand1.hand.stream().filter(card -> card.getSuit() == Suit.HEARTS).count();
        int count2 = (int) hand2.hand.stream().filter(card -> card.getSuit() == Suit.HEARTS).count();
        return Integer.compare(count1, count2); // Cette ligne retourne un int, mais dans le contexte de la méthode compare() de Comparator.
    };
}

	•	La lambda ne retourne pas directement un Comparator, mais définit la méthode compare() de ce Comparator.
	•	Quand la méthode getComparatorByHearts() est appelée, elle retourne un objet Comparator<Hand> qui peut être utilisé pour trier des objets de type Hand.

Exemple d’utilisation :

Comparator<Hand> comparator = Hand.getComparatorByHearts();
Collections.sort(listOfHands, comparator);

Ici, comparator est l’instance de Comparator<Hand> retournée par getComparatorByHearts(), 
et la méthode compare() sera appelée automatiquement pendant le tri pour comparer deux mains. 


Pour aller plus LOIN :

1.	Interface fonctionnelle : Java utilise le concept d’interface fonctionnelle (une interface avec une seule méthode abstraite) 
pour permettre aux expressions lambda d’être associées à cette méthode. 
Dans le cas de Comparator, la méthode abstraite est compare(T o1, T o2).

2.	Type de retour implicite : Lorsque tu écris une lambda comme (hand1, hand2) -> Integer.compare(count1, count2),
 Java sait que cette lambda doit implémenter la méthode compare() de l’interface Comparator<Hand>,
car c’est le type de retour attendu par la méthode getComparatorByHearts(). Java associe donc automatiquement la lambda à la méthode compare() de l’interface.

3.	Création de l’objet : Lorsque la lambda est retournée par la méthode getComparatorByHearts(), elle est convertie en une instance anonyme de Comparator<Hand>. 
C’est comme si Java créait en coulisse une classe anonyme qui implémente Comparator<Hand> et redéfinit la méthode compare() avec le code de la lambda.

4.	Appel de compare() : Quand tu utilises le Comparator (par exemple, dans Collections.sort()), 
Java appelle la méthode compare() de cette instance anonyme, ce qui exécute le code défini dans la lambda.*/