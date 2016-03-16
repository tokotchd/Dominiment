package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.players.Player;


public class Estate extends Card implements VictoryCard
{
	public int getVictoryPoints(Player player) 
	{
		return 1;
	}
	public int getCost() 
	{
		return 2;
	}
	@Override
	public String toString() 
	{
		return "Estate";
	}
}
