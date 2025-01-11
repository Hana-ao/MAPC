package org.example.td5.q2.metier.impl;

import java.util.Optional;

import org.example.td5.q2.metier.api.Id;
import org.example.td5.q2.metier.api.IdGenerator;

public class FileIdGenerator extends IdGenerator {
    public static int nbMaxId = 10;
    public static int nbCourant = 0;

    private static FileIdGenerator instance;

    private FileIdGenerator() {}

    public static synchronized FileIdGenerator getInstance() {
        if (instance == null) {
            instance = new FileIdGenerator();
        }
        return instance;
    }
    
    public synchronized Optional<Id> generateId(){

        if(nbCourant < nbMaxId){// 3. le nombre de fichiers (fid differents) est fixé
            nbCourant++;
            return Optional.of(new FileId(nbCourant));
        }
        else{
            return Optional.empty();
        }
    }
    
}
