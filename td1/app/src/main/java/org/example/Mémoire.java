package org.example;
import java.util.*;

public class Mémoire implements Document{
    private String titre;
    protected List<Chapitre> chapitres;

    public Mémoire(String titre, List<Chapitre> chapitres){
        this.titre = titre;
        this.chapitres = chapitres;
    }

    public String titre(){
        return titre;
    }

    public int taille(){
        int t = 0;
        int ti = 0;
        for(Chapitre ci : chapitres){
            ti = ci.taille();
            t+=ti;
        }
        return t;
    }
}
