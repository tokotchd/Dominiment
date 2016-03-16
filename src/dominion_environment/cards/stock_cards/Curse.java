package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.CurseCard;


public class Curse extends Card implements CurseCard
{
	public int getVictoryPoints() 
	{
		return -1;
	}
	public int getCost() 
	{
		return 0;
	}
	@Override
	public String toString() 
	{
		return "Curse";
	}
}
