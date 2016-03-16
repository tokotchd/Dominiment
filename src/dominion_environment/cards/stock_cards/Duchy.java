package dominion_environment.cards.stock_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.players.Player;


public class Duchy extends Card implements VictoryCard
{
	public int getVictoryPoints(Player player) 
	{
		return 3;
	}
	public int getCost() 
	{
		return 5;
	}
	@Override
	public String toString() 
	{
		return "Duchy";
	}
}
