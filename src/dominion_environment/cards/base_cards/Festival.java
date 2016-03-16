package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Festival extends Card implements ActionCard
{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) 
	{
		player.actions+=2;
		player.buys++;
		player.money+=2;
		player.resolve(this);
	}
	@Override
	public int getCost() 
	{
		return 5;
	}
	@Override
	public String toString() {
		return "Festival";
	}
}
