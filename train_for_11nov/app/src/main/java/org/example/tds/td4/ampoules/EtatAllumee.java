package org.example.tds.td4.ampoules;

public class EtatAllumee implements EtatAmpoule {
    
    private int usages;

    public EtatAllumee(int usages) {
        this.usages = usages;
    }

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("ampoule déjà allumée");
    }
    
    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        System.out.println("l'ampoule s'éteint");
        return new EtatEteinte(usages);
    }
    
}

