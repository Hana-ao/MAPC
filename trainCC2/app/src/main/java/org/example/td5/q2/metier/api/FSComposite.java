package org.example.td5.q2.metier.api;

public interface FSComposite extends FileSystemElement{
    void add(FileSystemElement element); //pour ajouter dynamiquement un élément
    void remove(FileSystemElement element); //pour supprimer dynamiquement un élément
    
}
