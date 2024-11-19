package org.example.mesRessourcesPourComprendre;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.metier.api.ICard;

//on parcourt ici de façon classique
// but : montrer la pertinence de Iterator


/*
1.	Encapsulation comme principe de conception :

•	L’encapsulation signifie que l’implémentation interne d’une classe est cachée aux autres classes. 
Seules certaines parties de la classe (méthodes publiques) sont exposées pour interagir avec elle.
•	Cela permet de protéger l’état interne de l’objet et d’assurer qu’il n’est manipulé qu’à travers des méthodes définies et contrôlées.
	
2.	Pourquoi cela va à l’encontre de l’encapsulation dans la méthode classique :

•	Lorsque tu parcours une liste cards dans Deck en accédant directement aux éléments par leur index (cards.get(i)), 
tu exposes indirectement la manière dont les données sont stockées (par exemple, en utilisant une liste List).
•	Bien que tu ne modifies pas directement la liste dans la boucle, le fait de rendre évident le fait que Deck utilise
 une structure de liste pourrait pousser un développeur à chercher à manipuler cette liste directement s’il y avait un accès externe.
	
3.	Ce que l’encapsulation protège réellement :

•	L’encapsulation vise à cacher comment une classe stocke et gère ses données. 
Par exemple, Deck pourrait stocker ses cartes sous forme de tableau, de liste chaînée, ou d’une autre structure de données
 sans que le reste du programme ne le sache ou ne s’en soucie.
•	En exposant des méthodes qui permettent de parcourir Deck sans révéler sa structure interne 
(comme avec un Iterator), tu protèges l’implémentation interne et donnes la flexibilité de changer 
cette implémentation sans impacter les autres parties du code.

Qui est le “méchant” dans la mauvaise encapsulation ?

Le “méchant” n’est pas forcément une personne, mais plutôt le code extérieur (autres classes ou méthodes)
 qui pourrait accéder ou modifier l’état interne de l’objet sans passer par des méthodes contrôlées.

	•	Exemple de mauvaise encapsulation : Si ta classe Deck expose une méthode getCards() qui retourne directement 
    la référence à cards, n’importe quel code extérieur peut modifier cette liste :

    public List<ICard> getCards() {
    return cards;
}Dans ce cas, un autre programme pourrait écrire :

Deck myDeck = new Deck(cards);
myDeck.getCards().clear(); // Efface toutes les cartes sans passer par une méthode contrôlée de Deck

Cela pose un problème car tu perds le contrôle sur l’état de Deck.

Pourquoi l’Iterator aide-t-il à l’encapsulation ?

L’Iterator ne cache pas l’implémentation interne pour la rendre invisible à tout prix, 
mais il fournit un moyen contrôlé et abstrait de parcourir les éléments sans exposer la manière dont ils sont stockés.

	•	Encapsulation et abstraction : Même si un utilisateur externe peut voir que Deck utilise une ArrayList lorsqu’il regarde le code, 
    l’important est que l’utilisateur interagisse avec l’objet uniquement à travers ses méthodes publiques.
     Cela permet de garantir que l’accès aux données reste sous contrôle.

	•	Protection contre les modifications : En fournissant un Iterator, tu permets aux utilisateurs 
    de parcourir Deck sans exposer une méthode qui leur donnerait accès direct à cards (comme getCards()), 
    évitant ainsi qu’ils puissent modifier directement la liste.

Pourquoi cela compte-t-il ?

	•	Flexibilité future : Aujourd’hui, Deck utilise une ArrayList, mais tu pourrais décider de changer 
    l’implémentation pour une autre structure (par exemple, une liste chaînée). Si le code extérieur dépend
     uniquement de l’Iterator pour parcourir Deck, il n’aura pas besoin d’être modifié même si tu changes l’implémentation interne.



 */
public class DeckTest {
 
    
    
    private List<ICard> cards; 

    public DeckTest(List<ICard> cards){
       
        this.cards = new ArrayList<>(cards);
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

        /*
        ou encore:
        for(int i=0; i<cards.size(); i++){
            System.out.println(cards.get(i));
        }
         */
    }
}

/*

D’accord, voyons cela avec un exemple concret. Imaginons que nous ayons une classe Deck contenant des objets Card, et que nous voulions parcourir cette collection de cartes de deux manières différentes : avec une méthode classique et avec un Iterator. Ensuite, nous analyserons les problèmes de l’approche classique et comment l’Iterator les résout.

Exemple de méthode classique

Voici comment tu pourrais implémenter une méthode classique pour parcourir un Deck :

public class Deck {
    private List<ICard> cards;

    public Deck(List<ICard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void printDeck() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i));
        }
    }

    public void removeCard(int index) {
        if (index >= 0 && index < cards.size()) {
            cards.remove(index);
        }
    }
}

Problèmes potentiels de l’approche classique :

	1.	Accès direct aux éléments : L’approche expose la structure interne de la liste cards via des indices. Si d’autres parties du code utilisent la même logique, elles pourraient accéder à des indices incorrects ou dépasser les limites de la liste, entraînant des erreurs comme IndexOutOfBoundsException.
	2.	Répétition de la logique : Si tu veux parcourir Deck ailleurs dans le programme, tu devras répéter la même logique de boucle, ce qui augmente la redondance.
	3.	Couplage : L’accès direct aux éléments par des indices rend le code dépendant de l’implémentation interne de Deck (ici, une liste). Si la structure de stockage change, tu devras modifier tous les endroits où la boucle est utilisée.

Exemple avec Iterator

Implémentons maintenant un Iterator pour parcourir Deck :

public class Deck implements Iterable<ICard> {
    private List<ICard> cards;

    public Deck(List<ICard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public Iterator<ICard> iterator() {
        return new DeckIterator();
    }

    private class DeckIterator implements Iterator<ICard> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < cards.size();
        }

        @Override
        public ICard next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return cards.get(currentIndex++);
        }
    }
}

Utilisation de l’Iterator :

Grâce à l’implémentation de l’Iterator, tu peux parcourir Deck comme ceci :

Deck myDeck = new Deck(cards);
for (ICard card : myDeck) {
    System.out.println(card);
}

Analyse comparative :

	1.	Encapsulation et abstraction :
	•	Classique : La méthode printDeck() accède directement aux éléments de cards, ce qui expose la structure interne.
	•	Iterator : La boucle for-each utilise l’Iterator pour accéder aux éléments sans révéler comment Deck stocke ses cartes. Cela respecte l’encapsulation.
	2.	Réutilisabilité :
	•	Classique : Si tu veux utiliser la même logique de parcours ailleurs, tu devras copier la boucle for ou créer des méthodes supplémentaires.
	•	Iterator : Une fois que l’Iterator est implémenté, toute classe peut parcourir Deck de manière standardisée sans devoir réécrire la logique.
	3.	Modifications internes :
	•	Classique : Si tu changes la structure de cards (par exemple, en passant à une autre collection), tu devras modifier tous les endroits où la boucle est utilisée.
	•	Iterator : Avec l’Iterator, seule l’implémentation de iterator() et DeckIterator doit être modifiée, ce qui simplifie la maintenance et réduit le couplage.

Cas problématique avec la méthode classique :

Imaginons que tu ajoutes une logique pour sauter un élément sur deux dans ta méthode printDeck() :

public void printDeckSkipOne() {
    for (int i = 0; i < cards.size(); i += 2) {
        System.out.println(cards.get(i));
    }
}

Cette logique fonctionne, mais si elle doit être utilisée ailleurs, tu devras la copier. Si cards passe d’une liste à un tableau ou une autre structure, toutes les boucles for devront être mises à jour.

Comment l’Iterator règle le problème :

En définissant cette logique dans DeckIterator, tu centralises et encapsules la manière dont Deck est parcourue. Si tu changes l’implémentation interne de Deck, le code qui utilise le parcours reste inchangé. Tu peux même créer des Iterator spécialisés pour parcourir Deck selon des critères spécifiques sans altérer la logique de l’objet externe.

Conclusion :

L’Iterator demande plus d’effort à implémenter au début, mais il offre des avantages en termes de maintenabilité, encapsulation, et flexibilité. Si le code doit évoluer ou si des parcours personnalisés sont nécessaires, l’Iterator facilite grandement la tâche.
 */
