package org.example.td5.q2.metier.impl;

import java.util.*;

import org.example.td5.q2.metier.api.*;


public class Directory implements FSComposite { //Composite


    private FileId fid;
    private List<FileSystemElement> contents = new ArrayList<FileSystemElement>();
    private String name;
    private UserId owner;
    private FileId parent; //dossier parent -> mieux vaut t-il renvoyer FileSystemElement ou FileId ?

    public Directory(FileId fid, String name, UserId owner, FileId parent) {
        this.fid = fid;
        this.name = name;
        this.owner = owner;
        this.parent = parent;
    }

    @Override
    public int getSize() {
        return contents.stream().mapToInt(FileSystemElement::getSize).sum();
        // int size = 0;
        // for(FileSystemElement fs : contents){
        //     size += fs.getSize();
        // }
        // return size;
    }

    @Override
    public void add(FileSystemElement element) {
        contents.add(element);
    }

    @Override
    public void remove(FileSystemElement element) {
        contents.remove(element);
    }


    @Override
    public FileId getFid() {
        return fid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UserId getOwner() {
        return owner;
    }

    @Override
    public FileId getParent() {
        return parent;
    }

    @Override
    public List<FileSystemElement> getContents() {
        return contents;
    }
}
