package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.cards.card_types.ReactionAttackCard;
import dominion_environment.players.Player;

public class Moat extends Card implements ActionCard, ReactionAttackCard{
	public void reactionEffect(Player player) {
		player.hasMoatSafety = true;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.resolve(this);
		player.draw(2);
	}
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	@Override
	public int getCost() {
		return 2;
	}
	@Override
	public String toString() {
		return "Moat";
	}
}
