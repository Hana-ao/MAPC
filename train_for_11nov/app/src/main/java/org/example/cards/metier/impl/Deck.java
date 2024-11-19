package org.example.cards.metier.impl;

import java.util.*;

import org.example.cards.metier.api.ICard;
import org.example.tds.td2.DeepCopiable;

/*
 * Crée une classe Deck :
	•	Implémente une classe Deck qui contient une List<Card>.
	•	Ajoute des méthodes simples pour gérer ce deck, comme addCard(Card card) et printDeck() pour afficher le contenu du deck.

    Problèmes: 
    Analyse des Problèmes Potentiels sans Interface

	-   Si Deck dépend directement de Card, il est couplé à une classe concrète, ce qui rend difficile l’introduction de nouvelles variantes de cartes sans modifier Deck.
	•	Cela viole le principe de l’inversion de dépendance (D de SOLID) : les modules de haut niveau devraient dépendre d’abstractions et non de détails.
Solution : ICard

Objectif : Implémente la collection Deck et sa méthode iterator(). Familiarise-toi avec l’implémentation de Iterator.
	•	Astuce : Teste ton Iterator avec une boucle for-each pour voir comment il fonctionne.

Résumé des rôles :

	•Deck :
	•	Doit être parcourable, donc il implémente Iterable<ICard>. Cela signifie qu’il doit fournir une méthode iterator() qui retourne un Iterator pour parcourir ses éléments.
	
    •DeckIterator :
	•	Est l’itérateur qui réalise le parcours, donc il implémente Iterator<ICard>. Il doit fournir les méthodes hasNext(), next(), et éventuellement remove().

 */
public class Deck implements Iterable<ICard>, DeepCopiable<Deck>{
    private List<ICard> cards; //List et pas ArrayList pour l'encapsulation
  
    public Deck(List<ICard> cards){
        /*
         * Différence entre this.cards = new ArrayList<>(cards); et this.cards = cards;

	1.	this.cards = cards; :
	    •	Cette assignation affecte simplement la référence passée en paramètre à l’attribut cards de la classe Deck. Cela signifie que la List originale et this.cards pointent vers la même liste en mémoire.
	    •	Conséquence : Si l’appelant (le code extérieur) modifie la liste originale après l’appel du constructeur, ces modifications se refléteront dans l’objet Deck. Cela peut créer un problème si tu veux que Deck garde le contrôle de la liste.
	
    2.	this.cards = new ArrayList<>(cards); (copie défensive) :
	    •	Cette ligne crée une nouvelle ArrayList qui contient les mêmes éléments que la liste passée en paramètre, mais qui est indépendante de l’originale.
	    •	Conséquence : Si l’appelant modifie la liste originale après avoir passé la liste au constructeur, ces modifications ne seront pas répercutées dans Deck, car Deck a sa propre copie de la liste.
         */
        this.cards = new ArrayList<>(cards); //copie défensive, pour protéger l'intégrité de cards
    }
   
    public void addCard(ICard card){
        //Comment garantir que la méthode addCard ne permette pas l’ajout de cartes nulles ?
        if(card!=null){
            cards.add(card);
        }
        else{
            System.out.println("Impossible d'ajouter des cartes nulles !!");}
        //comme cela on garantit la responsabilité unique de la méthode : elle ajoute des cartes, elle ne gère pas les erreurs liées aux valeurs nulles
        
    }

    public void removeCard(ICard card){
        cards.remove(card); //on pourrait aussi enlever une carte selon son index
    }
    
    public void printDeck(){
        for(ICard element : cards){
            System.out.println(element.toString());
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder("Deck : \n");
        
        for(ICard elt : cards){
            sb.append(elt.toString()).append("\n");

        }
        return sb.toString();
    }

    
    @Override
    public Iterator<ICard> iterator() {
        //ici, on renvoie une instance de DeckIterator
        return new DeckIterator();
    }

    //classe interne DeckIterator
    class DeckIterator implements Iterator<ICard>{
        /*	•	L’Iterator est utile pour parcourir des collections de manière standardisée et pour encapsuler la logique de parcours.
	•	Implémenter Iterable<T> dans une classe permet de la rendre compatible avec des structures de parcours telles que les boucles for-each. */

        //dans DeckIterator, tu centralises et encapsules la manière dont Deck est parcourue

        private int currentIndex = 0;//pour suivre la position actuelle dans la liste.

        @Override
        public boolean hasNext() {
            return currentIndex < cards.size();
        }

        @Override
        public ICard next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            else{ //La méthode next() doit d’abord retourner l’élément courant, puis incrémenter currentIndex.

                
                return cards.get(currentIndex++);
                
            }
        }
    }

        //DeepCopiable (Copie profonde)
        public Deck deepCopy(){
            
            List<ICard> newList = new ArrayList<>();
            for(ICard c : cards){
                newList.add(c.deepCopy()); //on appelle deepCopy de manière polymorphique
            }
            
            return new Deck(newList);
        }




/*Comment créer plusieurs Iterator :

Pour avoir un Iterator classique et un autre qui parcourt les éléments de deux en deux, 
tu peux créer plusieurs classes internes dans Deck ou des classes séparées qui implémentent Iterator. */

    
    /*C’est exactement ça, Hana ! Et il est tout à fait normal que certaines notions prennent du temps pour être pleinement comprises. Tu as déjà une bonne compréhension de l’utilité de l’Iterator et de sa pertinence.

Points à retenir sur la pertinence de l’Iterator :

	•	Standardisation : Implémenter Iterable et créer un Iterator permet à ta classe d’être parcourue de manière standard, en utilisant des boucles for-each sans que le code externe n’ait à connaître les détails internes.
	•	Sécurité et encapsulation : L’Iterator protège la structure interne de ta collection, car il permet de la parcourir sans exposer directement la liste ou la collection. Cela réduit le risque d’altérations non souhaitées.
	•	Cohérence et flexibilité : Avec un Iterator, tu peux facilement modifier la logique de parcours (par exemple, pour sauter certains éléments ou parcourir dans un ordre différent) sans modifier la classe externe qui utilise la collection.

Ce que tu peux faire pour approfondir :

	•	Pratique continue : Implémente des Iterator dans différents contextes ou explore comment les collections Java standard utilisent ce concept.
	•	Réflexion : Compare comment le code avec et sans Iterator se comporte et quelles sont les différences en termes de lisibilité et de maintenance.

Avec le temps et l’expérience, tu verras comment ce concept s’applique de manière plus naturelle dans tes projets. Pour l’instant, tu as déjà fait un excellent travail en comprenant les bases et en l’implémentant correctement. */

}
