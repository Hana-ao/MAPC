package org.example.cards.metier.impl;
import java.util.*;


public class CardStack implements Iterable<Card>
{
private final List<Card> aCards = new ArrayList<>();

public void push(Card pCard)
{ assert pCard != null && !aCards.contains(pCard);
aCards.add(pCard);
}

public Card pop()
{ assert !isEmpty();
return aCards.remove(aCards.size()-1);
}

public Card peek()
{ assert !isEmpty();
return aCards.get(aCards.size()-1);
}

public void clear() { aCards.clear(); }
public boolean isEmpty() { return aCards.size() == 0; }
public Iterator<Card> iterator() { return aCards.iterator(); }
}
