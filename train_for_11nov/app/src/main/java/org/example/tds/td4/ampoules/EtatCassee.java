package org.example.tds.td4.ampoules;

public class EtatCassee implements EtatAmpoule {

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("L'ampoule ne fonctionne plus...");
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("L'ampoule ne fonctionne plus...");
    }
    
}
