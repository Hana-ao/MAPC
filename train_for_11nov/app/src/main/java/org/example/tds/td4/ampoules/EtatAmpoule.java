package org.example.tds.td4.ampoules;

public interface EtatAmpoule {
    
    public EtatAmpoule allumer() throws ActionIllegale;
    public EtatAmpoule eteindre() throws ActionIllegale;
}
