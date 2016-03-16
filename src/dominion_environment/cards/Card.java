package dominion_environment.cards;

import java.util.ArrayList;

public abstract class Card 
{
	public abstract int getCost();
	public abstract String toString();
	public static int findCardTypeInArray(Card card, ArrayList<Card> arrayList)
	{
		for(int counter = 0; counter < arrayList.size(); counter++)
		{
			if(arrayList.get(counter).isA(card))
			{
				return counter;
			}
		}
		return -1;
	}
	public boolean isA(Card card)
	{
		if(this.getClass().equals(card.getClass()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
