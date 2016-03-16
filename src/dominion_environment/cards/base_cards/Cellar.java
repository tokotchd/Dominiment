package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Cellar extends Card implements ActionCard
{
	public void playCard(Player player)
	{
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) 
	{
		player.actions++;
		ArrayList<Card> cardsToDiscard = player.resolve(this);
		for(Card card : cardsToDiscard)
		{
			player.discard(card);
			player.draw(1);
		}
	}
	@Override
	public int getCost() 
	{
		return 2;
	}
	@Override
	public String toString() 
	{
		return "Cellar";
	}
}
