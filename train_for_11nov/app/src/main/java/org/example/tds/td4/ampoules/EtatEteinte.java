package org.example.tds.td4.ampoules;

public class EtatEteinte implements EtatAmpoule {
    private int usages; //static ?

    public EtatEteinte(){}
    public EtatEteinte(int usages){
        this.usages = usages;
    }
    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
       
        if(usages < 3){
            usages++;
            System.out.println("L'ampoule s'allume...");
            return new EtatAllumee(usages);
        }
        else{
            System.out.println("L'ampoule se casse");
            return new EtatCassee();
        }
        
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        
        throw new ActionIllegale("L'ampoule est déjà éteinte");
    }
    
}
