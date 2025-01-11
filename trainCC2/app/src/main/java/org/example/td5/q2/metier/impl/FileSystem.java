package org.example.td5.q2.metier.impl;

import java.util.Optional;

import org.example.td5.q2.metier.api.Id;

public class FileSystem {
    //initialisation
    private static FileSystem instance = null;

    private FileSystem() {
    }

    public static synchronized FileSystem getInstance() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }
    public Directory initDirectory(){
        //initialisation du répertoire
        Optional<Id> optionalFid = FileIdGenerator.getInstance().generateId();
        if (!optionalFid.isPresent()) {
            throw new RuntimeException("Failed to generate FileId");
        }
        FileId fid = (FileId) optionalFid.get();

      
        Directory d = new Directory(fid, "ROOT", new UserId("0"), new FileId(0));
        return d;
    }
    
    
}
