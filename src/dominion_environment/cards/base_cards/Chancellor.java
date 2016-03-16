package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Chancellor extends Card implements ActionCard
{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.money += 2;
		boolean shouldDiscard = player.resolve(this);
		if(shouldDiscard)
		{
			player.discard.addAll(player.drawDeck);
			player.drawDeck.clear();
		}
	}
	@Override
	public int getCost() {
		return 3;
	}
	@Override
	public String toString() {
		return "Chancellor";
	}
}
