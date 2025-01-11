

**Jeu de cartes : Architecture et Organisation du Code**

1. Introduction

Ce projet implémente un système de gestion de jeux de cartes basé sur une architecture modulaire et flexible. Il distingue :
	•	Les concepts généraux (interfaces, classes abstraites, et contrats) décrits dans les paquetages API.
	•	Les implémentations spécifiques (classes concrètes et règles métiers) organisées dans des paquetages distincts.

En respectant des principes comme la séparation des responsabilités, cette structure permet de :
	•	Faciliter la maintenance et l’ajout de nouvelles fonctionnalités.
	•	Rendre le code plus lisible et évolutif.

2. Organisation en paquetages

Paquetage |	Description
------------------------
org.example.api	| Contient toutes les interfaces générales et classes abstraites (contrats et squelettes).
org.example.impl | Contient toutes les implémentations concrètes des interfaces et des classes abstraites.
org.example.clients | Contient les classes principales (comme Main) qui utilisent les fonctionnalités du projet.

3. Détails par paquetage

3.1. org.example.api : Les concepts généraux

Ce paquetage regroupe les interfaces et classes abstraites qui définissent les bases générales du projet :
	•	Interfaces :

	•	ICard : Définit les opérations sur une carte.
	•	IHand : Définit les opérations sur une main.
	•	IDeck : Définit les opérations sur un deck.
	•	SortStrategy : Définit un contrat pour les algorithmes de tri.
	- CardStack encapsule une logique spécifique à la manipulation d’un tas de cartes 
(comme push, pop, peek), ce qui permet de mieux contrôler les opérations permises 
et d’assurer l’intégrité des données. 
	
	•	Classe abstraite :
	
	•	CardGameProcessor : Utilise le Pattern Template Method pour définir le squelette général d’un jeu de cartes.

3.2. org.example.impl : Les implémentations concrètes

Ce paquetage contient toutes les implémentations des interfaces et classes abstraites :
	•	Classes métier générales :
	•	Card : Implémentation d’une carte.
	•	Deck : Implémentation d’un jeu de cartes.
	•	Hand : Implémentation d’une main de joueur.
	•	MultiDeck : Implémentation pour un ensemble de decks.

	•	Stratégies de tri (Pattern Strategy) :
	•	SortByRank : Trie les cartes par rang.
	•	SortBySuit : Trie les cartes par couleur.
	•	CardSorter : Contexte pour appliquer dynamiquement une stratégie de tri.

	•	Implémentations de jeux (Pattern Template Method) :
	•	PokerProcessor : Implémentation des règles du jeu de poker.
	•	BlackJackProcessor : Implémentation des règles du jeu de blackjack.

3.3. org.example.clients : Les classes principales

Ce paquetage contient des classes clients qui consomment les fonctionnalités exposées par les API et leurs implémentations :
	•	Main : Une classe qui montre l’utilisation des différentes fonctionnalités du projet, comme :
	•	La gestion des decks et mains.
	•	L’utilisation des stratégies de tri.
	•	Le lancement de jeux comme le poker ou le blackjack.

4. Concepts clés utilisés

4.1. Pattern Template Method

Le patron Template Method est utilisé pour définir le squelette des jeux de cartes :
	•	La classe abstraite CardGameProcessor définit les étapes générales (setupGame, takeTurn, etc.).
	•	Les classes PokerProcessor et BlackJackProcessor implémentent ces étapes selon les règles spécifiques.

4.2. Pattern Strategy

Le patron Strategy est utilisé pour les algorithmes de tri :
	•	L’interface SortStrategy définit un contrat général pour les tris.
	•	Les classes SortByRank et SortBySuit réalisent des tris spécifiques.
	•	CardSorter agit comme un contexte pour appliquer dynamiquement une stratégie.

5. Exemple d’utilisation


	Tri des cartes avec une stratégie

	import org.example.impl.CardSorter;
	import org.example.impl.Card;
	import org.example.impl.SortByRank;
	import org.example.impl.SortBySuit;

	import java.util.Arrays;
	import java.util.List;

	public class Main {
		public static void main(String[] args) {
			List<Card> cards = Arrays.asList(
				new Card(Card.Rank.ACE, Card.Suit.SPADES),
				new Card(Card.Rank.KING, Card.Suit.HEARTS),
				new Card(Card.Rank.QUEEN, Card.Suit.CLUBS)
			);

			CardSorter sorter = new CardSorter(new SortByRank());
			sorter.sort(cards); // Trie par rang
			System.out.println("Trié par rang : " + cards);

			sorter.setStrategy(new SortBySuit());
			sorter.sort(cards); // Trie par couleur
			System.out.println("Trié par couleur : " + cards);
		}
	}

	Lancer un jeu de cartes

	import org.example.api.CardGameProcessor;
	import org.example.impl.PokerProcessor;
	import org.example.impl.BlackJackProcessor;

	public class Main {
		public static void main(String[] args) {
			CardGameProcessor poker = new PokerProcessor();
			poker.playGame(); // Lancer un jeu de poker

			CardGameProcessor blackjack = new BlackJackProcessor();
			blackjack.playGame(); // Lancer un jeu de blackjack
		}
	}

6. Pourquoi cette architecture ?

	1.	Modularité :
	•	La séparation des API et implémentations permet d’ajouter facilement de nouvelles fonctionnalités.
	•	Ex : Ajouter un nouveau jeu de cartes ou une nouvelle stratégie de tri.

	2.	Lisibilité :
	•	Les responsabilités sont bien séparées : le code métier est distinct des classes spécifiques à un jeu ou à un tri.

	3.	Flexibilité :
	•	Grâce aux interfaces (SortStrategy) et classes abstraites (CardGameProcessor), tu peux adapter le comportement sans modifier le code existant.

