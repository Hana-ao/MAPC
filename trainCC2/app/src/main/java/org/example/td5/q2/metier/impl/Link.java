package org.example.td5.q2.metier.impl;

import java.util.List;

import org.example.td5.q2.metier.api.FSComposite;
import org.example.td5.q2.metier.api.FileSystemElement;

public class Link implements FSComposite { //Composite

        private UserId owner;
        private FileSystemElement fichierReference; 
        private FileId fid;
        private int size;
        private FileId parent;
        private String name;

        public Link(UserId owner, FileId fid, int size, FileId parent, String name) {
            this.owner = owner;
            this.fid = fid;
            this.size = size;
            this.parent = parent;
            this.name = name;
        }

        @Override
        public UserId getOwner() {
            return owner;
        }

        
        public FileSystemElement getFichierReference() {
            return fichierReference;
        }

        @Override
        public FileId getFid() {
            return fid;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public FileId getParent() {
            return parent;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void add(FileSystemElement element) {
            fichierReference = element; //ajout d'un fichier de référence
        }

        @Override
        public void remove(FileSystemElement element) {
            assert fichierReference != null;
            fichierReference = null; //TODO : voir la pertinence de l'argument element dans la méthode
        }

        @Override
        public List<FileSystemElement> getContents() {
            // TODO Auto-generated method stub
            //TODO : voir la pertinence de la méthode getContents dans le cas d'un lien
            throw new UnsupportedOperationException("Unimplemented method 'getContents'");
        }
    }
    

