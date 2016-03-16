package game_state;

import dominion_environment.cards.Card;

public class GameStateCardStack 
{
	Card thisCard;
	int numLeft;
	public GameStateCardStack(Card card, int numLeft)
	{
		thisCard = card;
		this.numLeft = numLeft;
	}
	public String toString()
	{
		return thisCard.toString() + " x" + numLeft;
	}
}
