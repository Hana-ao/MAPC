package org.example;

public class Chapitre implements Document{
    private final String titre;
    private final String[] pages;

    public Chapitre(String titre, String[] pages){
        this.titre = titre;
        this.pages = pages;
    }

    public String titre(){
        return titre;
    }
    public int taille(){
        int t = 0;
        for(String i : pages){ //ou utiliser pages.length;
            t++;
        }
        return t;
    }
}
