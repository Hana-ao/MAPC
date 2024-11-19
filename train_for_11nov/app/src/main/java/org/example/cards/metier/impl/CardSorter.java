package org.example.cards.metier.impl;

import java.util.*;
/*Le Pattern Strategy te permet de séparer les algorithmes de tri de la classe principale en les externalisant dans des classes spécifiques, 
ce qui rend le code plus flexible et modulaire. Tu crées des stratégies de tri sous forme de classes qui implémentent une interface commune.

Le Pattern Strategy n’est pas limité à la logique de tri ; 
il peut être utilisé dans de nombreux contextes où il est nécessaire de choisir dynamiquement entre différents algorithmes ou comportements. 

Le Pattern Strategy est utile pour découpler le comportement d’une classe et faciliter l’extension.
 Il te permet de passer d’une approche rigide et complexe à un design modulaire et maintenable. */

import org.example.cards.metier.api.ICard;
import org.example.cards.metier.api.SortStrategy;

public class CardSorter {
    //on va utiliser une stratégie via cette classe

    private SortStrategy strategy; //attribut de type interface ; bénéfique car cela permet de ne pas dépendre d'une implémentation concrète mais d'une abstraction
    //principe d'inversion de dépendances (D de SOLID)

    //on va injecter la stratégie que l'on veut via le constructeur
    public CardSorter(SortStrategy strategy){
        this.strategy = strategy; //L’injection : bonne pratique, car elle permet de définir le comportement de CardSorter lors de sa création.
    }

    //méthode qui déclenche le tri en utilisant la stratégie choisie, mais le code spécifique du tri est implémenté dans les classes concrètes de la stratégie.
    public void sort(List<ICard> cards){
        strategy.sort(cards); //on appelle l'implémentation exacte de sort(), codée différemment en fonction de la stratégie
    }
    
    public void setStrategy(SortStrategy newStrat){
        strategy = newStrat;
    }
    public SortStrategy getStrategy(){
        return strategy;
    }

}
/* A quoi sert CardSorter ?
C’est une très bonne question ! On pourrait se demander pourquoi on a besoin de CardSorter si on a déjà des implémentations concrètes de SortStrategy. Voici l’intérêt de CardSorter :

1. Centraliser l’utilisation des stratégies :

CardSorter agit comme un contexte qui centralise l’utilisation des différentes stratégies de tri. Au lieu de devoir manipuler directement des instances de SortStrategy dans tout ton code, tu peux passer par CardSorter pour gérer le tri de manière uniforme.

2. Abstraction et flexibilité :

En utilisant CardSorter, tu crées un point central dans ton application où tu peux changer la stratégie de tri sans modifier le reste du code. Cela respecte le principe de l’ouverture/fermeture (OCP) de SOLID : ta classe CardSorter est ouverte à l’extension (en ajoutant de nouvelles stratégies) mais fermée à la modification (pas besoin de la modifier pour utiliser une autre stratégie).

3. Facilité de changement de stratégie :

Avec CardSorter, tu peux facilement changer la stratégie à utiliser grâce à une méthode comme setStrategy(). Cela te permet de modifier le comportement de tri à la volée sans toucher au code des implémentations concrètes. Cela est particulièrement utile lorsque tu as besoin de trier une collection de différentes manières dans la même application.

4. Cohérence du code :

CardSorter offre un moyen cohérent d’appeler la méthode de tri, ce qui peut rendre ton code plus lisible et maintenable. Plutôt que de devoir se souvenir de quelle stratégie utiliser et comment l’appeler, tu sais que CardSorter est l’outil que tu utilises pour tous les tris.

Exemple de contexte où CardSorter est utile :

Imaginons que tu as une application où, à différents moments, tu dois trier des cartes par rang, par couleur ou selon d’autres critères :

	•	Sans CardSorter, tu devrais gérer l’instanciation et l’appel de chaque SortStrategy manuellement chaque fois que tu veux trier.
	•	Avec CardSorter, tu peux initialiser un objet, changer la stratégie et appeler sort() sans avoir à modifier le reste de ton code.

Exemple concret :

CardSorter cardSorter = new CardSorter(new SortByRank());
cardSorter.sort(cards); // Trie par rang

// Change de stratégie à la volée
cardSorter.setStrategy(new SortBySuit());
cardSorter.sort(cards); // Trie par couleur

Conclusion :

CardSorter est utile pour centraliser l’utilisation des stratégies de tri, rendre le code plus flexible, et permettre des changements dynamiques de comportement tout en gardant le reste du code propre et maintenable.
 */