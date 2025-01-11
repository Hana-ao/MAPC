package org.example.td5.q2.metier.api;

import java.util.List;

import org.example.td5.q2.metier.impl.*; //bonne chose de dépendre d'une classe concrète ?

public interface FileSystemElement { //Component de Composite
    FileId getFid(); //meilleur d'utiliser une classe métier qu'un String ?
    String getName(); //nom du fichier ou du dossier
    UserId getOwner(); //propriétaire du fichier ou du dossier
    List<FileSystemElement> getContents(); //contenu du dossier -> FileSystemElement ou List<FileSystemElement> ?
    int getSize(); //taille du fichier ou du dossier
    FileId getParent(); //dossier parent -> mieux vaut t-il renvoyer FileSystemElement ou FileId ?

}
