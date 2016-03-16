package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.players.Player;


public class Province extends Card implements VictoryCard
{
	public int getVictoryPoints(Player player) 
	{
		return 6;
	}
	public int getCost() 
	{
		return 8;
	}
	@Override
	public String toString() 
	{
		return "Province";
	}
}
