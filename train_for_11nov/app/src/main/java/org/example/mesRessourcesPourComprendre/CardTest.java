package org.example.mesRessourcesPourComprendre;

public class CardTest {
//     Pas de souci, je vais te montrer un exemple simple et clair pour bien comprendre comment créer et utiliser un Comparator.
    
//     Exemple : Comparer des objets Person
    
//     Imaginons que tu aies une classe Person avec des attributs name et age. Tu veux pouvoir trier une liste de Person par age et par name.
    
//     1. Classe Person :
    
//     public class Person {
//         private String name;
//         private int age;
    
//         public Person(String name, int age) {
//             this.name = name;
//             this.age = age;
//         }
    
//         public String getName() {
//             return name;
//         }
    
//         public int getAge() {
//             return age;
//         }
    
//         @Override
//         public String toString() {
//             return name + " (" + age + " years)";
//         }
//     }
    
//     2. Création de Comparator pour trier par age :
    
//     Tu peux créer un Comparator en tant que classe interne, classe externe, ou même lambda si tu veux rester concis.
    
//     a. Classe externe (fichier séparé) :
    
//     import java.util.Comparator;
    
//     public class AgeComparator implements Comparator<Person> {
//         @Override
//         public int compare(Person p1, Person p2) {
//             return Integer.compare(p1.getAge(), p2.getAge());
//         }
//     }
    
//     b. Classe interne statique dans Person :
    
//     public static Comparator<Person> compareByAge() {
//         return new Comparator<Person>() {
//             @Override
//             public int compare(Person p1, Person p2) {
//                 return Integer.compare(p1.getAge(), p2.getAge());
//             }
//         };
//     }
    
//     c. Utilisation d’une lambda pour un Comparator simple :
    
//     Comparator<Person> ageComparator = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
    
//     3. Utilisation du Comparator :
    
//     Imaginons que tu as une liste de Person que tu veux trier par age :
    
//     import java.util.*;
    
//     public class Main {
//         public static void main(String[] args) {
//             List<Person> people = Arrays.asList(
//                 new Person("Alice", 30),
//                 new Person("Bob", 25),
//                 new Person("Charlie", 35)
//             );
    
//             // Utilisation de Comparator
//             Collections.sort(people, new AgeComparator()); // Utilisation de la classe externe
//             // Ou
//             people.sort(Person.compareByAge()); // Utilisation de la méthode interne
//             // Ou
//             people.sort(ageComparator); // Utilisation de la lambda
    
//             // Afficher la liste triée
//             for (Person person : people) {
//                 System.out.println(person);
//             }
//         }
//     }
    
//     Résultat :
    
//     La liste sera triée par age, et le Comparator permet de spécifier exactement comment cette comparaison est effectuée.
    
//     En résumé :
    
//         •	Un Comparator est un objet qui définit comment comparer deux objets de type T de manière externe.
//         •	Il peut être défini de plusieurs façons : classe externe, classe interne, ou lambda.
//         •	Utilise Collections.sort(list, comparator) ou list.sort(comparator) pour trier en utilisant un Comparator.
    
//     Cet exemple te montre comment écrire et utiliser un Comparator de manière claire et flexible pour trier des objets selon différents critères.
// 
}
