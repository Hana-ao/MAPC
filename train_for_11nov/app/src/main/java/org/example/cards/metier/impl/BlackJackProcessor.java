package org.example.cards.metier.impl;

import org.example.cards.metier.api.CardGameProcessor;

public class BlackJackProcessor extends CardGameProcessor{

    @Override
    protected void playTurn() {
        System.out.println("Jeu de BlackJack en cours...");
    }
    
    
}
