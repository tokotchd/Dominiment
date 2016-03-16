package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.TreasureCard;


public class Copper extends Card implements TreasureCard
{
	public int getWorth() 
	{
		return 1;
	}
	public int getCost() 
	{
		return 0;
	}
	@Override
	public String toString() 
	{
		return "Copper";
	}
}
