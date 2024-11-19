package org.example.cards.metier.impl;

import org.example.cards.metier.api.ICard;

/*
Ouvert/fermé (OCP) : Ajouter une SpecialCard sans modifier le code existant démontre que ton code est ouvert à l’extension mais fermé à la modification, ce qui est l’un des principes de SOLID.
Responsabilité unique (SRP) : Si SpecialCard introduit des comportements ou des propriétés spécifiques (comme des pouvoirs spéciaux ou des règles de jeu uniques), cela permet de séparer ces responsabilités et de rendre le code plus clair.
 */
public class Joker extends Card implements ICard {
    

    public Joker(){
        super(null, null); //// Le Joker n'a pas de rang ni de couleur spécifique
   
    }

    @Override
    public String toString(){
        return "<JOKER>";
    }

    @Override
    public Joker deepCopy() {
        return new Joker(); /*Le polymorphisme permet de choisir la bonne version de la méthode à exécuter au moment de l’exécution. Si une méthode n’est pas redéfinie dans une sous-classe, la version héritée de la classe parente est utilisée, 
        ce qui peut causer des incohérences lorsque l’objet doit rester de son type spécifique (ici, Joker). */
}

   
    
}
