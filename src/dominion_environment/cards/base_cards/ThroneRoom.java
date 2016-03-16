package dominion_environment.cards.base_cards;

import game_state.MisuseException;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class ThroneRoom extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		Card card = player.resolve(this);
		if(!(card instanceof ActionCard))
		{
			throw new MisuseException();
		}
		ActionCard aCard = (ActionCard)card;
		aCard.playCard(player);
		player.actions++;
		aCard.resolveCard(player, otherPlayers);
		aCard.resolveCard(player, otherPlayers);
	}
	@Override
	public int getCost() {
		return 4;
	}
	@Override
	public String toString() {
		return "Throne Room";
	}
}
