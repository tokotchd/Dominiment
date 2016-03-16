package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Woodcutter extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.buys++;
		player.money+=2;
		player.resolve(this);
	}
	@Override
	public int getCost() {
		return 3;
	}
	@Override
	public String toString() {
		return "Woodcutter";
	}
}
