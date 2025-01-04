package org.example.tds.td4.ampoules;

public class App {
    public static void main(String[] args) {
        Ampoule a = new Ampoule();
        try {
            a.eteindre();
            
        } catch (ActionIllegale e) {
            System.out.println(e.getMessage());

        }
        // cas qui casse
        a = new Ampoule();
        try {
            a.allumer(); // 0 -> 1
            a.eteindre(); // 1 -> 1
            a.allumer(); // 1 -> 2
            a.eteindre(); // 2 -> 2
            a.allumer(); // 2 -> 3
            a.eteindre(); // 3 -> 3
            a.allumer(); // casse
            a.eteindre();
        } catch (ActionIllegale e) {
            System.out.println(e.getMessage());
        }
    }
}
