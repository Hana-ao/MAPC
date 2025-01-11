package org.example.td5.q2.metier.impl;

import org.example.td5.q2.metier.api.Id;

public class UserId implements Id<String> {
    private String id;
    UserId(String id) {
        this.id = id;
    }
    @Override
    public String id() {
        return id;
    }
    
}
