package org.example.tds.td2;
import java.util.*;

import org.example.cards.metier.impl.Deck;

public class MultiDeck implements DeepCopiable, Iterable<Deck> {
    /*Créer la classe MultiDeck :
	•	Contenant une liste de Deck.
	•	Avec une méthode clone() qui copie le MultiDeck.
	2.	Explorer la copie superficielle :
	•	Commence par une méthode shallowCopy() qui copie seulement la liste des Deck sans dupliquer les cartes elles-mêmes.
	3.	Observer les problèmes potentiels :
	•	Modifie un Deck dans la copie et observe ce qui se passe dans l’original. */
    private List<Deck> decks;

    public MultiDeck(List<Deck> decksA){
        //j'ai un doute, ici le constructeur, on affecte la valeur de decksA, dans decks
        //mais créer une méthode clone, c différent ?
        decks = new ArrayList<>(decksA); //copie défensive (voir en bas la diff avec copie superficielle)
    }

  
    public void printMultiDeck(){
        for(Deck d : decks){
            System.out.println(d.toString());
        }
    }

    public List<Deck> getDecks(){
        return new ArrayList<>(decks); /*La méthode getDecks() telle que définie renvoie une nouvelle liste, mais les éléments à l’intérieur de cette liste (c’est-à-dire les objets Deck) sont les mêmes références que dans la liste decks originale. Cela signifie que même si la liste elle-même est une nouvelle instance, les références des Deck à l’intérieur ne changent pas. */
    }

    public void addDeck(Deck d){
        decks.add(d);
    }

    public MultiDeck shallowCopy(){ //copie superficielle
        //return new ArrayList<>(deckACopier); //ici, on duplique la liste deckACopier en crééant une nouvelle liste, mais ses éléments internes ne sont que copiés, pas dupliqués. on copie seulement les références
        return new MultiDeck(this.decks); //ok, j'avais pas compris qu'on voulait cloner l'instance actuelle (ce qui est logique en soi)

    /*	•	Mutabilité : Quand on dit qu’une structure est mutable, cela signifie qu’elle peut être modifiée après sa création (comme ajouter ou modifier des éléments dans une liste). 
    Si tu fais une simple affectation (this.decks = decksA), this.decks et decksA pointeront vers la même liste, donc toute modification de l’une affectera l’autre. */
    }

    public MultiDeck deepCopy(){
   
        List<Deck> newListDecks = new ArrayList<>();
        for(Deck elt : decks){
            newListDecks.add(elt.deepCopy());
        }
        return new MultiDeck(newListDecks);
    }

    @Override
    public Iterator<Deck> iterator() {
        return new MultiDeckIterator();
    }

    class MultiDeckIterator implements Iterator<Deck>{
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            //est ce qu'il y'a un élément après la position courante ?
            return currentIndex < decks.size();
        }

        @Override
        public Deck next() {
            //retourne l'élement courant et incrémente
            if(!hasNext()){
                throw new NoSuchElementException(); //L’exception NoSuchElementException est levée pour signaler que l’itération a dépassé la fin de la collection, ce qui empêche l’appel de next() de retourner un élément invalide ou de provoquer une erreur.
            }
            else{
                return decks.get(currentIndex++);
            }
            
        }

        

    }
}
/*Diffe entre copie superficielle et copie défensive
1. Copie superficielle :

	•	Définition : Une copie superficielle crée un nouvel objet, mais les références aux objets internes pointent toujours vers les mêmes instances que celles de l’objet original. Cela signifie que si l’objet contient des listes ou d’autres objets complexes, ceux-ci ne sont pas copiés, seulement la référence l’est.
	•	Conséquence : Si tu modifies un élément à l’intérieur de la copie, cela affecte l’original, et vice versa.
	•	Exemple :

public MultiDeck shallowCopy() {
    return new MultiDeck(this.decks); // Nouvelle liste, mêmes Decks à l'intérieur.
}



2. Copie défensive :

	•	Définition : Une copie défensive est une forme de copie qui crée un nouvel objet pour protéger l’original contre des modifications imprévues. Elle est souvent utilisée pour garantir l’immuabilité et la sécurité de l’objet. Cependant, une copie défensive peut être soit superficielle, soit profonde.
	•	Sécurité : La copie défensive est considérée “safe” car elle crée une séparation entre l’objet original et la copie, empêchant ainsi l’utilisateur de modifier la version originale via la copie.
	•	Copie défensive superficielle : Elle ne protège que la structure externe, pas les objets internes. Par exemple, new ArrayList<>(originalList) est une copie défensive, mais elle reste superficielle si les objets internes ne sont pas copiés.
	•	Copie défensive profonde : Elle duplique l’objet ainsi que tous ses sous-objets, garantissant que les modifications dans la copie n’affectent pas l’original.

En résumé :

	•	Copie superficielle : Copie uniquement la structure extérieure, partage les mêmes références pour les objets internes. Elle n’est pas forcément “safe” si l’on veut que l’original reste inchangé lors de modifications dans la copie.
	•	Copie défensive : Une technique pour protéger l’objet original contre des modifications, mais elle peut être superficielle ou profonde selon le niveau de protection souhaité.

Conclusion : La copie défensive peut être superficielle ou profonde. Lorsqu’on dit qu’elle est “safe”, cela signifie qu’elle protège l’objet original contre des modifications imprévues de l’extérieur. Toutefois, pour qu’elle soit “safe” dans le contexte de la copie d’objets imbriqués, elle doit être profonde. */
