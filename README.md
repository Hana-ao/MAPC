# Jeu de cartes : Architecture et Organisation du Code

## 1. Introduction

Ce projet implémente un système de gestion de jeux de cartes basé sur une architecture modulaire et flexible. Il distingue :
- **Les concepts généraux** : Interfaces, classes abstraites, et contrats.
- **Les implémentations spécifiques** : Classes concrètes et règles métiers.

---

## 2. Organisation en paquetages

| Paquetage               | Description                                                                                     |
|--------------------------|-------------------------------------------------------------------------------------------------|
| **`org.example.api`**    | Contient toutes les interfaces générales et classes abstraites (contrats et squelettes).       |
| **`org.example.impl`**   | Contient toutes les implémentations concrètes des interfaces et des classes abstraites.        |
| **`org.example.clients`**| Contient les classes principales (comme `Main`) qui utilisent les fonctionnalités du projet.   |

---

## 3. Concepts clés utilisés

### **Pattern Template Method**
Définit le squelette général d’un jeu de cartes :
- La classe abstraite `CardGameProcessor` définit les étapes principales.
- `PokerProcessor` et `BlackJackProcessor` personnalisent ces étapes.

### **Pattern Strategy**
Utilisé pour les algorithmes de tri :
- L’interface `SortStrategy` définit le contrat.
- Les classes `SortByRank` et `SortBySuit` réalisent des tris spécifiques.

---

## 4. Exemple de code

### Tri des cartes
```java
CardSorter sorter = new CardSorter(new SortByRank());
sorter.sort(cards);
sorter.setStrategy(new SortBySuit());
sorter.sort(cards);
