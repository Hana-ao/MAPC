package org.example.cards.metier.impl;

public class Paire<T,U> {
    public T fst;
    public U snd;
    public Paire(T fst, U snd) {
    this.fst = fst;
    this.snd = snd;
    }

    public T getFst() {
        return fst;
    }

    public U getSnd() {
        return snd;
    }
    
    @Override public String toString() {
    return String.format("(%s,%s)",fst.toString(),snd.toString());
    }
    }
