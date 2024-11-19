package org.example.cards.metier.api;

public abstract class CardGameProcessor {
/*Pourquoi le patron Template ? 
 * 1. Approche sans le Pattern Template

Lorsque tu implémentes un jeu sans utiliser le pattern Template, tu risques de dupliquer le code commun à plusieurs jeux.
 Par exemple, si tu as des classes séparées pour chaque jeu de cartes (BlackJack, Poker, etc.), chacune pourrait contenir 
 du code identique pour les étapes de préparation (setupGame) et de fin de partie (endGame). Cela rend le code difficile à maintenir,
  car toute modification de la logique partagée doit être répétée dans chaque classe.

Problèmes rencontrés :
	•	Duplication de code : Les méthodes communes à plusieurs classes doivent être copiées et collées, ce qui augmente le risque d’incohérence et de bugs.
	•	Difficile à maintenir : Modifier le comportement commun nécessite de changer le code dans chaque classe, augmentant le risque d’erreur.
	•	Peu flexible : Ajouter un nouvel algorithme de jeu nécessite plus de travail, car il faut tout réécrire, même les parties communes.


Le pattern Template peut être inutile ou trop complexe dans des situations où :
	•	Les étapes de l’algorithme ne sont pas communes : Si chaque type de jeu a un déroulement complètement différent, 
    le pattern Template pourrait ne pas apporter de valeur ajoutée.
	•	Un faible nombre de variantes : Si tu n’as qu’une ou deux classes qui partagent des étapes similaires, l’utilisation de ce patron peut être excessive.
 */

    //Test du patron Template : on crée une classe abstraite qui définit la structure de l'algo

    //ici on centralise la logique commune dans CardGameProcessor,
    //et on laisse la responsabilité aux sous classes de redéfinir playTurn elles mêmes

    public void playGame(){ //méthode template qui appelle à la fois des étapes fixes, et d'autres personnalisées
        setupGame(); //prépare le jeu
        playTurn();
        endGame();
    }


    public void setupGame(){ //encapsule la logique commune
        System.out.println("Le jeu se prépare..."); //méthodes communes à toutes les sous classes
    }

    protected abstract void playTurn(); //méthode à implémenter par les sous classes

    public void endGame(){ //encapsule la logique commune
        System.out.println("Partie de jeu terminée !");
    }


    
}
