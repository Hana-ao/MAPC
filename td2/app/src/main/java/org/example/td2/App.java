package main.java.org.example.td2;

public class App {
    
    public static void main(String[] args){
        Deck d = new Deck();
        for(Card c : d){
            System.out.println(c);
        }
        System.out.println(d);
    }
}
