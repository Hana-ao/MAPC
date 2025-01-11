package org.example.td5.q2.metier.impl;

import java.util.Optional;

import org.example.td5.q2.metier.api.Id;
import org.example.td5.q2.metier.api.IdGenerator;

public class UserIdGenerator extends IdGenerator {

	private static int counter = 0;
    private static UserIdGenerator instance;

    private UserIdGenerator() {}

    public static synchronized UserIdGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdGenerator();
        }
        return instance;
    }

	@Override
	public synchronized Optional<Id> generateId() {
        // if(counter == 0){
        //     return Optional.of(new UserId("ROOT"));
        // }
        // else{
            try{
                counter++;
                return Optional.of(new UserId("USER-" + System.currentTimeMillis() + "-" + counter));
            }
            catch(Exception e){
                return Optional.empty();
            }
        }
        
		
	
	
}
