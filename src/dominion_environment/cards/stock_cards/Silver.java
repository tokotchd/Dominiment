package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.TreasureCard;


public class Silver extends Card implements TreasureCard
{
	public int getWorth() 
	{
		return 2;
	}
	public int getCost() 
	{
		return 3;
	}
	@Override
	public String toString() 
	{
		return "Silver";
	}
}
