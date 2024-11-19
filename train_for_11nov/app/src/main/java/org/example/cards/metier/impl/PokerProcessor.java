package org.example.cards.metier.impl;

import org.example.cards.metier.api.CardGameProcessor;

public class PokerProcessor extends CardGameProcessor {
//sous classe : pattern Template
    @Override
    protected void playTurn() {
        System.out.println("Jeu de Poker en cours..."); //La sous classe implémente la méthode abstraite
    }
    
}
