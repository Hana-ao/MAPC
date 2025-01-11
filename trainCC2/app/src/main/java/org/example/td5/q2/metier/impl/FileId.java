package org.example.td5.q2.metier.impl;

import org.example.td5.q2.metier.api.Id;

public class FileId implements Id<Integer> {
    private int id;
    FileId(int id){
        this.id = id;
    }
    @Override
    public Integer id() {
        return id;
    }
    
}
