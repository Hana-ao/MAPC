package org.example.td5.q2.metier.api;

import java.util.Optional;

public abstract class IdGenerator {

   public abstract Optional<Id> generateId();
}