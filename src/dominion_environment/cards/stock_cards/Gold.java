package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.TreasureCard;


public class Gold extends Card implements TreasureCard
{
	public int getWorth() 
	{
		return 3;
	}
	public int getCost()
	{
		return 6;
	}
	@Override
	public String toString() 
	{
		return "Gold";
	}
}
